package handler

import (
	"MatchWave"
	"github.com/gin-gonic/gin"
	"net/http"
	"strings"
)

func (h *Handler) signUp(c *gin.Context) { // регистрация
	var input MatchWave.User

	if err := c.BindJSON(&input); err != nil {
		NewErrorResponse(c, http.StatusBadRequest, err.Error())
		return
	}

	id, err := h.services.Authorization.CreateUser(input)
	if err != nil {
		NewErrorResponse(c, http.StatusInternalServerError, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"id": id,
	})
}

type signInInput struct {
	Email    string `json:"email" binding:"required"`
	Password string `json:"password" binding:"required"`
}

func (h *Handler) signIn(c *gin.Context) { // аутентификация (авторизация)
	var input signInInput

	if err := c.BindJSON(&input); err != nil {
		NewErrorResponse(c, http.StatusBadRequest, err.Error())
		return
	}

	if h.services.Authorization.IsTokenBlacklisted(input.Email) {
		NewErrorResponse(c, http.StatusUnauthorized, "Previous token was invalidated. Please log in again.")
		return
	}

	token, err := h.services.Authorization.GenerateToken(input.Email, input.Password)
	if err != nil {
		NewErrorResponse(c, http.StatusInternalServerError, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"token": token,
	})
}

type verifyInput struct {
	VerificationCode string `json:"verification_code" binding:"required"`
}

func (h *Handler) verifyEmail(c *gin.Context) {
	var input verifyInput
	if err := c.BindJSON(&input); err != nil {
		NewErrorResponse(c, http.StatusBadRequest, err.Error())
		return
	}

	err := h.services.Authorization.VerifyUser(input.VerificationCode)
	if err != nil {
		NewErrorResponse(c, http.StatusInternalServerError, err.Error())
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"message": "Email successfully verified.",
	})
}

func (h *Handler) logout(c *gin.Context) {
	header := c.GetHeader(authorizationHeader)
	if header == "" {
		NewErrorResponse(c, http.StatusUnauthorized, "No authorization header")
		return
	}

	headerParts := strings.Split(header, " ")
	if len(headerParts) != 2 {
		NewErrorResponse(c, http.StatusUnauthorized, "Invalid authorization header")
		return
	}

	token := headerParts[1]

	// Аннулируем токен, добавляя его в черный список
	err := h.services.Authorization.InvalidateToken(token)
	if err != nil {
		NewErrorResponse(c, http.StatusInternalServerError, "Failed to logout")
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"message": "Successfully logged out",
	})
}
