package MatchWave

type User struct {
	Id          int    `json:"id" db:"id"`
	Email       string `json:"email" binding:"required"`
	Name        string `json:"username" binding:"required"`
	Password    string `json:"password" binding:"required"`
	DateOfBirth string `json:"date_of_birth" binding:"required"`
}
