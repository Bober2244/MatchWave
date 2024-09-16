package handler

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func (h *Handler) createCouple(c *gin.Context) {
	id, _ := c.Get(userCtx)
	c.JSON(http.StatusOK, gin.H{
		"id": id,
	})
}

func (h *Handler) getAllCouples(c *gin.Context) {}

func (h *Handler) getCoupleById(c *gin.Context) {}

func (h *Handler) updateCouple(c *gin.Context) {}

func (h *Handler) deleteCouple(c *gin.Context) {}
