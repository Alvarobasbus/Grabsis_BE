package com.Grabsis.services.impl;

import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.entity.UsuarioEntity;
import com.Grabsis.models.Turno;
import com.Grabsis.repositories.TurnoRepository;
import com.Grabsis.services.TurnoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    private final ModelMapper modelMapper;


    //Crear turno

    @Override
    public Turno crear(Turno turno) {
        TurnoEntity creado = modelMapper.map(turno, TurnoEntity.class);
        creado.setIsDeleted(false);
        TurnoEntity created=null;
        Turno response=null;
        if(creado != null){
            try{
                created= turnoRepository.save(creado);
            } catch (DataIntegrityViolationException ex){
                throw new IllegalArgumentException("Error al crear el turno");
            }
            response= modelMapper.map(created, Turno.class);

        }
        return response;
    }

    @Override
    public void isDeleted(Long id) {
        TurnoEntity turno= turnoRepository.findByIdTurno(id);

        if(turno.getIsDeleted()==false && turno.isPagado()==false){
            try{
                turno.delete();
                turnoRepository.save(turno);
            }catch (Exception e){
                throw new EntityNotFoundException("No se pudo borrar el turno ");
            }
        }else{
            throw new IllegalArgumentException("No se puede borrar el turno ya que el mismo esta completado ");
        }

    }

    @Override
    public Turno BuscarporId(Long Id) {
        return null;
    }

    //Busqueda de turno por fecha
    @Override
    public List<Turno> obtenerPorFecha(LocalDate fecha) {
        List<TurnoEntity> listTurnos= turnoRepository.findByFecha(fecha);

        if(CollectionUtils.isEmpty(listTurnos)){
            throw new IllegalArgumentException("No se encontraron turnos registrados para la fecha: " + fecha);
        }

        return listTurnos.stream()
                .map(entity -> modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());
    }

   /* @Override
    public List<Turno> obtenerEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        List<TurnoEntity> listTurnos= turnoRepository.findByFechaBetweenAndFecha(fecha1,fecha2);

        if(CollectionUtils.isEmpty(listTurnos)){
            throw new IllegalArgumentException("No se encontraron turnos registrados entre las fechas: " + fecha1 + " y " + fecha2 );
        }
        return listTurnos.stream()
                .map(entity -> modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());

    }

*/


}
