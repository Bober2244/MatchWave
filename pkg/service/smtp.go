package service

import (
	"errors"
	"fmt"
	"gopkg.in/gomail.v2"
	"strconv"
)

const (
	From = "sergeevnicolas20@gmail.com" // Заменить на имя пользователя
	Pwd  = "rqrx wvjq nanc eolr"        // Заменить на реальный пароль(пароль приложения почты а не пароль от аккаунта)
)

// надо будет менять smtp, port в зависимости от почты
func makeMailer() (*gomail.Dialer, error) {
	smtp := "smtp.gmail.com"         // Заменить на реальный SMTP адрес
	port, err := strconv.Atoi("587") // Заменить на реальный порт
	if err != nil {
		fmt.Println("error parsing smtp port.", err)
		return nil, err
	}
	if port == 0 || smtp == "" || From == "" || Pwd == "" {
		return nil, errors.New("invalid mailer parameters")
	}

	return gomail.NewDialer(smtp, port, From, Pwd), nil
}

func SendConfirmationEmail(recipient string, code int) error {
	mailer, err := makeMailer()
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
