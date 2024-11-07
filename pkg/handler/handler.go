package handler

import (
	"MatchWave/pkg/service"
	"github.com/gin-gonic/gin"
)

type Handler struct {
	services *service.Service
}

func NewHandler(services *service.Service) *Handler {
	return &Handler{services: services}
}

func (h *Handler) InitRoutes() *gin.Engine {
	router := gin.New()

	auth := router.Group("/auth")
	{
		auth.POST("/sign-up", h.signUp)
		auth.POST("/sign-in", h.signIn)
		auth.POST("verify-email", h.verifyEmail)
		auth.POST("logout", h.logout)
	}
	ping := router.Group("/ping")
	ping.Use(h.userIdentity)
	{
		ping.GET("", func(c *gin.Context) {
			c.JSON(200, gin.H{"message": "pong"})
		})
	}

	return router
}
