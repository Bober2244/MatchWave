package service

import (
	"errors"
	"fmt"
	"gopkg.in/gomail.v2"
)

const (
	From = "sergeevnicolas20@gmail.com" // Заменить на имя пользователя
	Pwd  = "rqrx wvjq nanc eolr"        // Заменить на реальный пароль(пароль приложения почты а не пароль от аккаунта)
	Port = 587                          // Возможно нужно добавить еще порты
)

func makeMailer(smtpAddress string) (*gomail.Dialer, error) {
	var err error
	var smtp string
	switch smtpAddress {
	case "gmail":
		smtp = "smtp.gmail.com"
	case "outlook":
		smtp = "smtp.office365.com"
	case "yahoo":
		smtp = "smtp.mail.yahoo.com"
	case "yandex":
		smtp = "smtp.yandex.ru"
	case "mail":
		smtp = "smtp.mail.ru"
	case "icloud":
		smtp = "smtp.mail.me.com"
	case "zoho":
		smtp = "smtp.zoho.com"
	case "protonmail":
		smtp = "smtp.protonmail.com"
	case "rambler":
		smtp = "smtp.rambler.ru"
	case "aol":
		smtp = "smtp.aol.com"
	case "gmx":
		smtp = "mail.gmx.com"
	case "fastmail":
		smtp = "smtp.fastmail.com"
	}

	if err != nil {
		fmt.Println("error parsing smtp port.", err)
		return nil, err
	}
	if Port == 0 || smtp == "" || From == "" || Pwd == "" {
		return nil, errors.New("invalid mailer parameters")
	}

	return gomail.NewDialer(smtp, Port, From, Pwd), nil
}

func SendConfirmationEmail(smtpAddress, recipient string, code int) error {
	mailer, err := makeMailer(smtpAddress)
	if err != nil {
		return fmt.Errorf("can't create mailer: %v", err)
	}

	// Заменить на реального получателя "sarutunan082@gmail.com"
	if recipient == "" {
		return fmt.Errorf("empty recipient")
	}

	m := gomail.NewMessage()
	m.SetHeader("From", From) // Заменить на реальное имя отправителя
	m.SetHeader("To", recipient)
	m.SetHeader("Subject", "Верификация аккаунт") // Заменить на реальную тему
	m.SetBody("text/plain",
		fmt.Sprintf("Вам отправлен код:\n"+
			" %d \n"+
			"Введите его в приложении для авторизации", code)) // Заменить на реальный текст сообщения

	err = mailer.DialAndSend(m)
	if err != nil {
		return fmt.Errorf("can't SendConfirmationEmail email: %v", err)
	}

	return nil
}
