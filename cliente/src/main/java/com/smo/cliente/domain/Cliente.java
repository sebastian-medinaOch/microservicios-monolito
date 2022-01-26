package com.smo.cliente.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long cliId;
    private String cliNom;
    private String cliApe;

    private String cliTipDoc;

    private String cliNumDoc;
    private Integer cliEda;
    private String cliCiu;

}
