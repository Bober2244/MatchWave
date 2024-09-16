package service

import (
	"MatchWave"
	"MatchWave/pkg/repository"
)

type Authorization interface {
	CreateUser(user MatchWave.User) (int, error)
}

type Couples interface {
}

type Person interface{}

type Service struct {
	Authorization
	Couples
	Person
}

func NewService(repos *repository.Repository) *Service {
	return &Service{
		Authorization: NewAuthService(repos.Authorization),
	}
}
