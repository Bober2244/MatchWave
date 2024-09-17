package service

import (
	"MatchWave"
	"MatchWave/pkg/repository"
	"crypto/sha1"
	"errors"
	"fmt"
	"github.com/golang-jwt/jwt"
	"github.com/joho/godotenv"
	"github.com/sirupsen/logrus"
	"math/rand"
	"os"
	"time"
)

const (
	tokenTTL = time.Hour * 12
	MINN     = 100
	MAXX     = 999
)

type tokenClaims struct {
	jwt.StandardClaims
	UserId int `json:"user_id"`
}

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

	confirmationCode := generateConfirmationCode()

	err = SendConfirmationEmail(user.Email, confirmationCode)
	if err != nil {
		return 0, fmt.Errorf("error sending confirmation email")
	}

	user.VerificationCode = fmt.Sprintf("%d", confirmationCode)
	user.VerificationCodeExpiresAt = time.Now().Add(5 * time.Minute) // Время на верификацию кодом с почты
	user.IsVerified = false
	user.Password = generatePasswordHash(user.Password)
	userId, err := s.repo.CreateUser(user)
	if err != nil {
		return 0, err
	}

	return userId, nil
}

func (s *AuthService) VerifyUser(code string) error {
	user, err := s.repo.GetUserByVerificationCode(code)
	if err != nil {
		return fmt.Errorf("verification code is invalid: %w", err)
	}

	if time.Now().After(user.VerificationCodeExpiresAt) {
		return errors.New("verification code has expired")
	}

	err = s.repo.UpdateUserVerificationStatus(user.Id, true)
	if err != nil {
		return fmt.Errorf("could not update verification status: %w", err)
	}

	err = s.repo.ClearVerificationCode(user.Id)
	if err != nil {
		return fmt.Errorf("could not clear verification code: %w", err)
	}

	return nil
}

func (s *AuthService) GenerateToken(email, password string) (string, error) {
	user, err := s.repo.GetUser(email, generatePasswordHash(password))
	if err != nil {
		return "", err
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, &tokenClaims{
		jwt.StandardClaims{
			ExpiresAt: time.Now().Add(tokenTTL).Unix(),
			IssuedAt:  time.Now().Unix(),
		},
		user.Id,
	})
	return token.SignedString([]byte(os.Getenv("SIGNING_KEY")))
}

func (s *AuthService) ParseToken(accessToken string) (int, error) {
	token, err := jwt.ParseWithClaims(accessToken, &tokenClaims{}, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, errors.New("invalid signing method")
		}
		return []byte(os.Getenv("SIGNING_KEY")), nil
	})
	if err != nil {
		return 0, err
	}
	claims, ok := token.Claims.(*tokenClaims)
	if !ok {
		return 0, errors.New("token claims is invalid of type *tokenClaims")
	}
	return claims.UserId, nil
}

func generatePasswordHash(password string) string {
	hash := sha1.New()
	hash.Write([]byte(password))

	if err := godotenv.Load(); err != nil {
		logrus.Fatalf("Error loading .env file: %s", err.Error())
	}

	return fmt.Sprintf("%x", hash.Sum([]byte(os.Getenv("SALT"))))
}

func generateConfirmationCode() int {
	return rand.Intn(MAXX-MINN) + MINN
}
