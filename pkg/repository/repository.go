package repository

import (
	"MatchWave"
	"github.com/jmoiron/sqlx"
)

type Authorization interface {
	CreateUser(user MatchWave.User) (int, error)
	GetUser(email, password string) (MatchWave.User, error)
	GetUserByVerificationCode(code string) (MatchWave.User, error)
	UpdateUserVerificationStatus(userId int, isVerified bool) error
	ClearVerificationCode(userId int) error
}

type Couples interface {
}

type Person interface{}

type Repository struct {
	Authorization
	Couples
	Person
}

func NewRepository(db *sqlx.DB) *Repository {
	return &Repository{
		Authorization: NewAuthPostgres(db),
	}
}
