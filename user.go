package MatchWave

import "time"

type User struct {
	Id                        int       `json:"id" db:"id"`
	Email                     string    `json:"email" binding:"required" db:"email"`
	Password                  string    `json:"password" binding:"required" db:"password_hash"`
	VerificationCode          string    `json:"-" db:"verification_code"`
	VerificationCodeExpiresAt time.Time `json:"-" db:"verification_code_expires_at"`
	IsVerified                bool      `json:"is_verified" db:"is_verified"`
}
