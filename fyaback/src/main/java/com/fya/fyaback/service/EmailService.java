package com.fya.fyaback.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarCorreoRegistro(String nombreCliente, Double valorCredito, String comercial) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaRegistro = LocalDateTime.now().format(formatter);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("fyasocialcapital@gmail.com");
            message.setSubject("¡Crédito Registrado Exitosamente - Fya!");
            message.setText("Se ha registrado un nuevo crédito en el sistema con los siguientes detalles:\n\n" +
                    "• Nombre del cliente: " + nombreCliente + "\n" +
                    "• Valor del crédito: $" + valorCredito + "\n" +
                    "• Nombre del comercial: " + comercial + "\n" +
                    "• Fecha de registro: " + fechaRegistro + "\n\n" +
                    "Atentamente,\nSistema Fya");

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}