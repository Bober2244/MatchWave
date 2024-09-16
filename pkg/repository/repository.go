package repository

import (
	"MatchWave"
	"github.com/jmoiron/sqlx"
)

type Authorization interface {
	CreateUser(user MatchWave.User) (int, error)
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
