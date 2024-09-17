package MatchWave

import "time"

type User struct {
	Id                        int       `json:"id" db:"id"`
	Email                     string    `json:"email" binding:"required" db:"email"`
	Name                      string    `json:"username" binding:"required" db:"name"`
	Password                  string    `json:"password" binding:"required" db:"password_hash"`
	DateOfBirth               string    `json:"date_of_birth" binding:"required" db:"date_of_birth"`
	VerificationCode          string    `json:"-" db:"verification_code"`
	VerificationCodeExpiresAt time.Time `json:"-" db:"verification_code_expires_at"`
	IsVerified                bool      `json:"is_verified" db:"is_verified"`
}
