package com.fya.fyaback.controller;

import com.fya.fyaback.model.Credito;
import com.fya.fyaback.repository.CreditoRepository;
import com.fya.fyaback.service.EmailService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8101", "http://localhost:4200",
        "http://localhost" })
public class CreditoController {

    private final CreditoRepository creditoRepository;
    private final EmailService emailService;

    // Constructor para inyectar ambos servicios correctamente
    public CreditoController(CreditoRepository creditoRepository, EmailService emailService) {
        this.creditoRepository = creditoRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Credito> obtenerCreditos() {
        return creditoRepository.findAll();
    }

    @PostMapping
    public Credito registrarCredito(@Valid @RequestBody Credito credito) {
        Credito nuevoCredito = creditoRepository.save(credito);

        emailService.enviarCorreoRegistro(
                nuevoCredito.getNombreCliente(),
                nuevoCredito.getValorCredito(),
                nuevoCredito.getComercial());

        return nuevoCredito;
    }
}