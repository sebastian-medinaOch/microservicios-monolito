package com.smo.imagen.domain.cliente;

import lombok.Data;

@Data
public class ClienteModel {

    private Long cliId;
    private String cliNom;
    private String cliApe;

    private String cliTipDoc;

    private String cliNumDoc;
    private Integer cliEda;
    private String cliCiu;

}
