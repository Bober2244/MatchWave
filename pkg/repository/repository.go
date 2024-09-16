package repository

import "github.com/jmoiron/sqlx"

type Authorization interface {
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
	return &Repository{}
}
