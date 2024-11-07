package MatchWave

import "time"

type Verification struct {
	VerificationCode          string    `json:"-" db:"verification_code"`
	VerificationCodeExpiresAt time.Time `json:"-" db:"verification_code_expires_at"`
	IsVerified                bool      `json:"is_verified" db:"is_verified"`
}
