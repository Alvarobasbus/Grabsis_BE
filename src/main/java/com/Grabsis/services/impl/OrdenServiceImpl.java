package com.Grabsis.services.impl;

import com.Grabsis.entity.DetalleEntity;
import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.entity.OrdenEntity;
import com.Grabsis.models.Egreso;
import com.Grabsis.models.Orden;
import com.Grabsis.repositories.DetalleRepository;
import com.Grabsis.repositories.OrdenRepository;
import com.Grabsis.services.OrdenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Orden> obtenerTodos() {
        List<OrdenEntity> lista = ordenRepository.findAll();


        List<Orden> listaOrden = lista.stream()
                .map(entity ->modelMapper.map(entity, Orden.class))
                .collect(Collectors.toList());

        return listaOrden;
    }

    @Override
    public Orden obtenerPorId(Long id) {
        OrdenEntity orden= ordenRepository.findByIdOrden(id);


        if(orden.getIsDeleted()==true){
            throw new EntityNotFoundException("La orden de trabajo fue eliminada ");
        }


        if(orden != null){
            Orden response = modelMapper.map(orden, Orden.class);

            return  response;
        }
        throw new EntityNotFoundException("No existe orden de trabajo con el id: " + id);
    }


    @Override
    public void isDeleted(Long id) {
        OrdenEntity orden= ordenRepository.findByIdOrden(id);

        if(orden.getIsDeleted()==false){
            try{
                orden.delete();
                ordenRepository.save(orden);
            }catch (Exception e){
                throw new EntityNotFoundException("No se pudo borrar la orden ");
            }
        }else{
            throw new IllegalArgumentException("No se puede borrar la orden ya que la misma ya esta eliminada ");
        }

    }

    @Override
    public Orden crear(Orden orden) {
        OrdenEntity creado= modelMapper.map(orden, OrdenEntity.class);
        creado.setIsDeleted(false);
        LocalDate date = LocalDate.now();
        if(creado.getFecha()==null){
            creado.setFecha(date);
        }
        //creado.setFecha(date);
        OrdenEntity created=null;
        Orden response= null;


        if(creado !=null){
            try{
                created= ordenRepository.save(creado);

            }catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al intentar crear una orden de trabajo");
            }
            response= modelMapper.map(created, Orden.class);

        }
        return response;
    }

    @Override
    public List<Orden> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<OrdenEntity> lista= ordenRepository.listadoporFechas(fecha1,fecha2);

        List<Orden> listaOrdenes = lista.stream()
                .map(entity ->modelMapper.map(entity, Orden.class))
                .collect(Collectors.toList());

        return listaOrdenes;
    }

    @Override
    public Double efectivoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double efecitoTotal= ordenRepository.efectivoTotal(fecha1, fecha2);

        if(efecitoTotal==null){
            efecitoTotal= Double.valueOf(0);
        }

        return efecitoTotal;
    }

    @Override
    public Double debitoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double debitoTotal= ordenRepository.debitoTotal(fecha1, fecha2);

        if(debitoTotal==null){
            debitoTotal= Double.valueOf(0);
        }

        return debitoTotal;
    }

    @Override
    public Double creditoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double creditoTotal= ordenRepository.creditoTotal(fecha1, fecha2);

        if(creditoTotal==null){
            creditoTotal= Double.valueOf(0);
        }

        return creditoTotal;
    }

    @Override
    public Double transferenciaTotal(LocalDate fecha1, LocalDate fecha2) {
        Double transferenciaTotal= ordenRepository.transferenciaTotal(fecha1, fecha2);

        if(transferenciaTotal==null){
            transferenciaTotal= Double.valueOf(0);
        }

        return transferenciaTotal;
    }

    @Override
    public Double mercadoPagoTotal(LocalDate fecha1, LocalDate fecha2) {
        Double mercadoPagoTotal= ordenRepository.mercadoPagoTotal(fecha1, fecha2);

        if(mercadoPagoTotal==null){
            mercadoPagoTotal= Double.valueOf(0);
        }

        return mercadoPagoTotal;
    }
}
