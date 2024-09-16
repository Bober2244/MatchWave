package service

import (
	"MatchWave"
	"MatchWave/pkg/repository"
	"crypto/sha1"
	"fmt"
	"time"
)

const salt = "daskljdsadas"

type AuthService struct {
	repo repository.Authorization
}

func NewAuthService(repo repository.Authorization) *AuthService {
	return &AuthService{repo: repo}
}

func (s *AuthService) CreateUser(user MatchWave.User) (int, error) {
	_, err := time.Parse("2006-01-02", user.DateOfBirth)
	if err != nil {
		return 0, fmt.Errorf("invalid date format. Use YYYY-MM-DD")
	}

	user.Password = generatePasswordHash(user.Password)
	return s.repo.CreateUser(user)
}

func generatePasswordHash(password string) string {
	hash := sha1.New()
	hash.Write([]byte(password))

	return fmt.Sprintf("%x", hash.Sum([]byte(salt)))
}
