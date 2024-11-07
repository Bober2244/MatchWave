package service

import (
	"MatchWave"
	"MatchWave/pkg/repository"
)

type Authorization interface {
	CreateUser(user MatchWave.User) (int, error)
	GenerateToken(email, password string) (string, error)
	ParseToken(token string) (int, error)
	VerifyUser(code string) error
	InvalidateToken(token string) error
	IsTokenBlacklisted(token string) bool
}

type Service struct {
	Authorization
}

func NewService(repos *repository.Repository) *Service {
	return &Service{
		Authorization: NewAuthService(repos.Authorization),
	}
}
