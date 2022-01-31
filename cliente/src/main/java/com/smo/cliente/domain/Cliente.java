package com.smo.cliente.domain;

import javax.persistence.*;

import com.smo.cliente.domain.imagen.ImagenModel;
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

    @Transient
    private ImagenModel imagenModel;

}
