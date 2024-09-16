package repository

import (
	"MatchWave"
	"fmt"
	"github.com/jmoiron/sqlx"
)

type AuthPostgres struct {
	db *sqlx.DB
}

func NewAuthPostgres(db *sqlx.DB) *AuthPostgres {
	return &AuthPostgres{db: db}
}

func (r *AuthPostgres) CreateUser(user MatchWave.User) (int, error) {
	var id int
	query := fmt.Sprintf("INSERT INTO %s (email, name, password_hash, date_of_birth) values ($1, $2, $3, $4) RETURNING id", usersTable)
	row := r.db.QueryRow(query, user.Email, user.Name, user.Password, user.DateOfBirth)
	if err := row.Scan(&id); err != nil {
		return 0, err
	}
	return id, nil
}
