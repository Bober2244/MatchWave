package handler

import (
	"github.com/gin-gonic/gin"
	"net/http"
	"strings"
)

const (
	authorizationHeader = "Authorization"
	userCtx             = "userId"
)

func (h *Handler) userIdentity(c *gin.Context) {
	header := c.GetHeader(authorizationHeader)
	if header == "" {
		NewErrorResponse(c, http.StatusUnauthorized, "Authorization header is missing")
		return
	}

	headerParts := strings.Split(header, " ")
	if len(headerParts) != 2 {
		NewErrorResponse(c, http.StatusUnauthorized, "Invalid authorization header format")
		return
	}

	token := headerParts[1]

	if h.services.Authorization.IsTokenBlacklisted(token) {
		NewErrorResponse(c, http.StatusUnauthorized, "Token has been invalidated")
		return
	}

	userId, err := h.services.Authorization.ParseToken(token)
	if err != nil {
		NewErrorResponse(c, http.StatusUnauthorized, err.Error())
		return
	}
	c.Set(userCtx, userId)
}
