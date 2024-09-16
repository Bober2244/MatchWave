package handler

import (
	"MatchWave"
	"github.com/gin-gonic/gin"
	"net/http"
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
func (h *Handler) signIn(c *gin.Context) { // аутентификация(авторизация)

}
