package com.smo.cliente.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.smo.cliente.domain.Cliente;

import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
    
    public ArrayList<Cliente> obtenerClientes();
    public Cliente guarCliente(Cliente cliente) throws IOException;
    public Optional<Cliente> obtenerPorId(Long id);
    public ArrayList<Cliente> obtenerPorNumDocTip(String clinumdoc, String clitipdoc);
    public ArrayList<Cliente> obtenerPorEdadMayor(Integer cliedad);
    public Boolean eliminarCliente(Long id);
    public ArrayList<Cliente> obtenerPorNumDoc(String clinumdoc);
    
}
