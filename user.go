package MatchWave

import "time"

type User struct {
	Id          int       `json:"id"`
	Email       string    `json:"email"`
	Name        string    `json:"username"`
	Password    string    `json:"password"`
	DateOfBirth time.Time `json:"date_of_birth"`
}
