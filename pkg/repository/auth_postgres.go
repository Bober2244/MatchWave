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
	query := fmt.Sprintf("INSERT INTO %s (email, name, password_hash, date_of_birth, verification_code, verification_code_expires_at, is_verified) values ($1, $2, $3, $4, $5, $6, $7) RETURNING id", usersTable)
	row := r.db.QueryRow(query, user.Email, user.Name, user.Password, user.DateOfBirth, user.VerificationCode, user.VerificationCodeExpiresAt, user.IsVerified)
	if err := row.Scan(&id); err != nil {
		return 0, err
	}
	return id, nil
}

func (r *AuthPostgres) GetUser(email, password string) (MatchWave.User, error) {
	var user MatchWave.User
	query := fmt.Sprintf("SELECT id, is_verified FROM %s WHERE email=$1 AND password_hash=$2", usersTable)
	err := r.db.Get(&user, query, email, password)
	return user, err
}

func (r *AuthPostgres) ExistsUserByEmail(email string) (bool, error) {
	var exists bool
	query := fmt.Sprintf("SELECT EXISTS(SELECT 1 FROM %s WHERE email=$1)", usersTable)
	err := r.db.Get(&exists, query, email)
	if err != nil {
		return false, err
	}
	return exists, nil
}

func (r *AuthPostgres) GetUserByVerificationCode(code string) (MatchWave.User, error) {
	var user MatchWave.User
	query := fmt.Sprintf("SELECT id, email, name, password_hash, date_of_birth, verification_code, verification_code_expires_at, is_verified FROM %s WHERE verification_code=$1", usersTable)
	err := r.db.Get(&user, query, code)
	return user, err
}

func (r *AuthPostgres) UpdateUserVerificationStatus(userId int, isVerified bool) error {
	query := fmt.Sprintf("UPDATE %s SET is_verified=$1 WHERE id=$2", usersTable)
	_, err := r.db.Exec(query, isVerified, userId)
	return err
}

func (r *AuthPostgres) ClearVerificationCode(userId int) error {
	query := fmt.Sprintf("UPDATE %s SET verification_code=NULL WHERE id=$1", usersTable)
	_, err := r.db.Exec(query, userId)
	return err
}
