package com.Grabsis.services.impl;

import com.Grabsis.entity.DepositoEntity;
import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.models.Deposito;
import com.Grabsis.models.Egreso;
import com.Grabsis.repositories.DepositoRepository;
import com.Grabsis.services.DepositoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    private final ModelMapper modelMapper;


    @Override
    public void borrarPorId(Long id) {
        depositoRepository.deleteById(id);

    }

    @Override
    public List<Deposito> obtenerTodos() {
        List<DepositoEntity> lista = depositoRepository.findAll();


        List<Deposito> listaDepositos = lista.stream()
                .map(entity ->modelMapper.map(entity, Deposito.class))
                .collect(Collectors.toList());

        return listaDepositos;
    }

    @Override
    public Deposito obtenerPorId(Long id) {
        Optional<DepositoEntity> opDeposito = depositoRepository.findById(id);

        if(opDeposito.isPresent()) {
            Deposito deposito = modelMapper.map(opDeposito.get(), Deposito.class);

            return deposito;
        }
        throw new EntityNotFoundException("No existe el deposito con ese Id: " + id);
    }

    @Override
    public void isDeleted(Long id) {
        DepositoEntity deposito= depositoRepository.findByIdDeposito(id);

        try{
            deposito.delete();
            depositoRepository.save(deposito);
        }catch (Exception e){
            throw new EntityNotFoundException("No se pudo borrar el deposito ");
        }
    }

    @Override
    public Deposito crear(Deposito deposito) {
        DepositoEntity creado= modelMapper.map(deposito, DepositoEntity.class);
        creado.setIsDeleted(false);
        DepositoEntity created= null;
        Deposito response= null;

        if(creado !=null){
            try{
                created= depositoRepository.save(creado);
            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al generar el deposito");
            }
            response= modelMapper.map(created, Deposito.class);

        }
        return response;
    }


    @Override
    public List<Deposito> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<DepositoEntity> lista= depositoRepository.listadoporFechas(fecha1,fecha2);

        List<Deposito> listaDepositos = lista.stream()
                .map(entity ->modelMapper.map(entity, Deposito.class))
                .collect(Collectors.toList());

        return listaDepositos;

    }

    @Override
    public Double depositoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double depositoTotal=depositoRepository.depositoTotal(fecha1, fecha2);

        if(depositoTotal==null){
            depositoTotal= Double.valueOf(0);
        }
        return depositoTotal;
    }
}

