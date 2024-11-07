package MatchWave

type User struct {
	Id           int          `json:"id" db:"id"`
	Email        string       `json:"email" binding:"required" db:"email"`
	Password     string       `json:"password" binding:"required" db:"password_hash"`
	Verification Verification `json:"verification"`
}
