package com.smo.cliente.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.smo.cliente.application.validator.IValidatorCliente;
import com.smo.cliente.domain.Cliente;
import com.smo.cliente.infrastructure.ClienteRepository;

import com.smo.cliente.infrastructure.client.ImagenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    IValidatorCliente iValidatorCliente;

    @Autowired
    ImagenClient imagenClient;

    @Override
    public ArrayList<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guarCliente(Cliente cliente) throws IOException {
        iValidatorCliente.validartorCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public ArrayList<Cliente> obtenerPorNumDocTip(String clinumdoc, String clitipdoc) {
        return clienteRepository.findByCliNumDocAndCliTipDoc(clinumdoc,clitipdoc);
    }

    @Override
    public ArrayList<Cliente> obtenerPorEdadMayor(Integer cliedad) {
        return clienteRepository.findByCliEdaGreaterThanEqual(cliedad);
    }

    @Override
    public Boolean eliminarCliente(Long id) {
        try {
            Optional<Cliente> cliente = obtenerPorId(id);
            System.out.println(cliente.get().getCliNumDoc());
            imagenClient.eliminarCliImg(cliente.get().getCliNumDoc());
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Cliente> obtenerPorNumDoc(String clinumdoc) {
        return clienteRepository.findByCliNumDoc(clinumdoc);
    }
}
