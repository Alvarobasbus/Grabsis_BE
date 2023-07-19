package com.Grabsis.services.impl;

import com.Grabsis.entity.MetodoPagoEntity;
import com.Grabsis.entity.OrdenEntity;
import com.Grabsis.models.Detalle;
import com.Grabsis.models.MetodoPago;
import com.Grabsis.models.Orden;
import com.Grabsis.repositories.MetodoPagoRepository;
import com.Grabsis.repositories.OrdenRepository;
import com.Grabsis.services.MetodoPagoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<MetodoPago> obtenerTodos() {
        List<MetodoPagoEntity> lista = metodoPagoRepository.findAll();


        List<MetodoPago> listaMetodoPago = lista.stream()
                .map(entity ->modelMapper.map(entity, MetodoPago.class))
                .collect(Collectors.toList());

        return listaMetodoPago;
    }

    @Override
    public MetodoPago obtenerPorId(Long id) {
        MetodoPagoEntity metodoPago= metodoPagoRepository.findByIdMetodo(id);


        if(metodoPago != null){
            MetodoPago response = modelMapper.map(metodoPago, MetodoPago.class);

            return  response;
        }
        throw new EntityNotFoundException("No existe Metodo de Pago con el id: " + id);
    }

    @Override
    public MetodoPago crear(MetodoPago metodo) {
        MetodoPagoEntity creado= modelMapper.map(metodo, MetodoPagoEntity.class);
        creado.setIsDeleted(false);
        MetodoPagoEntity created= null;
        MetodoPago response= null;

        if(creado !=null){
            try{
                created= metodoPagoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al intentar crear un metodo de pago");
            }
            response= modelMapper.map(created, MetodoPago.class);

        }
        return response;
    }
}
