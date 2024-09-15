package handler

import "github.com/gin-gonic/gin"

type Handler struct {
}

func (h *Handler) InitRoutes() *gin.Engine {
	router := gin.New()

	auth := router.Group("/auth")
	{
		auth.POST("/sigh-up", h.signUp)
		auth.POST("/sigh-in", h.signIn)
	}
	api := router.Group("/api")
	{
		couples := api.Group("/couples")
		{
			couples.POST("/", h.createCouple)
			couples.GET("/", h.getAllCouples)
			couples.GET("/:id", h.getCoupleById)
			couples.PUT("/:id", h.updateCouple)
			couples.DELETE("/:id", h.deleteCouple)

			person := couples.Group(":id/person")
			{
				person.POST("/", h.createPerson)
				person.GET("/", h.getAllPeople)
				person.GET("/:person_id", h.getPersonById)
				person.PUT("/:person_id", h.updatePerson)
				person.DELETE("/:person_id", h.deletePerson)
			}
		}
	}
	return router
}
