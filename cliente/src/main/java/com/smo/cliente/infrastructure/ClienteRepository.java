package com.smo.cliente.infrastructure;

import java.util.ArrayList;

import com.smo.cliente.domain.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    public ArrayList<Cliente> findAll();
    public ArrayList<Cliente> findByCliNumDocAndCliTipDoc(String clinumdoc, String clitipdoc);
    public ArrayList<Cliente> findByCliEdaGreaterThanEqual(Integer cliedad);
    public ArrayList<Cliente> findByCliNumDoc(String clinumdoc);
}
