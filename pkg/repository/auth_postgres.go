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
	query := fmt.Sprintf("INSERT INTO %s (email, password_hash) VALUES ($1, $2) RETURNING id", usersTable)
	row := r.db.QueryRow(query, user.Email, user.Password)
	if err := row.Scan(&id); err != nil {
		return 0, err
	}

	verificationQuery := fmt.Sprintf("INSERT INTO %s (user_id, verification_code, verification_code_expires_at, is_verified) VALUES ($1, $2, $3, $4)", verificationsTable)
	_, err := r.db.Exec(verificationQuery, id, user.Verification.VerificationCode, user.Verification.VerificationCodeExpiresAt, user.Verification.IsVerified)
	if err != nil {
		return 0, err
	}

	return id, nil
}

func (r *AuthPostgres) GetUser(email, password string) (MatchWave.User, error) {
	var user MatchWave.User
	query := fmt.Sprintf("SELECT id, email FROM %s WHERE email=$1 AND password_hash=$2", usersTable)
	err := r.db.Get(&user, query, email, password)
	if err != nil {
		return user, err
	}

	var verification MatchWave.Verification
	verificationQuery := fmt.Sprintf("SELECT verification_code_expires_at, is_verified FROM %s WHERE user_id=$1", verificationsTable)
	err = r.db.Get(&verification, verificationQuery, user.Id)
	if err != nil {
		return user, err
	}

	user.Verification = verification
	return user, nil
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
	query := fmt.Sprintf("SELECT u.id, u.email, u.password_hash FROM %s u INNER JOIN %s v ON u.id = v.user_id WHERE v.verification_code=$1", usersTable, verificationsTable)
	err := r.db.Get(&user, query, code)
	if err != nil {
		return user, err
	}

	// Fetch verification details
	var verification MatchWave.Verification
	verificationQuery := fmt.Sprintf("SELECT verification_code, verification_code_expires_at, is_verified FROM %s WHERE user_id=$1", verificationsTable)
	err = r.db.Get(&verification, verificationQuery, user.Id)
	if err != nil {
		return user, err
	}

	user.Verification = verification
	return user, nil
}

func (r *AuthPostgres) UpdateUserVerificationStatus(userId int, isVerified bool) error {
	query := fmt.Sprintf("UPDATE %s SET is_verified=$1 WHERE user_id=$2", verificationsTable)
	_, err := r.db.Exec(query, isVerified, userId)
	return err
}

func (r *AuthPostgres) ClearVerificationCode(userId int) error {
	query := fmt.Sprintf("UPDATE %s SET verification_code=NULL WHERE user_id=$1", verificationsTable)
	_, err := r.db.Exec(query, userId)
	return err
}
