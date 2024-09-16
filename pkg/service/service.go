package service

import "MatchWave/pkg/repository"

type Authorization interface {
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
	return &Service{}
}
