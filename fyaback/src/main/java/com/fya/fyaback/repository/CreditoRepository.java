package com.fya.fyaback.repository;

import com.fya.fyaback.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
}