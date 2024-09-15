package main

import (
	"MatchWave"
	"MatchWave/pkg/handler"
	"log"
)

func main() {
	handlers := new(handler.Handler)
	srv := new(MatchWave.Server)
	if err := srv.Run("8000", handlers.InitRoutes()); err != nil {
		log.Fatalf("error occured while running server: %s", err.Error())
	}
}
