package com.fya.fyaback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "creditos")
@Data
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombreCliente;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "^[0-9]{6,10}$", message = "La cédula debe tener entre 6 y 10 dígitos")
    private String cedula;

    @NotNull(message = "El valor del crédito es obligatorio")
    @Min(value = 100000, message = "El valor mínimo del crédito es 100,000")
    private Double valorCredito;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.1", message = "La tasa mínima es 0.1%")
    @DecimalMax(value = "100.0", message = "La tasa máxima es 100%")
    private Double tasaInteres;

    @NotNull(message = "El plazo es obligatorio")
    @Min(value = 1, message = "El plazo mínimo es 1 mes")
    @Max(value = 360, message = "El plazo máximo es 360 meses")
    private Integer plazoMeses;

    @NotBlank(message = "El comercial es obligatorio")
    private String comercial;
}