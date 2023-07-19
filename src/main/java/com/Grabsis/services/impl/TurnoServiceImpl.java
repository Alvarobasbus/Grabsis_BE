package com.Grabsis.services.impl;

import com.Grabsis.entity.EgresoEntity;
import com.Grabsis.entity.TurnoEntity;
import com.Grabsis.entity.UsuarioEntity;
import com.Grabsis.models.Egreso;
import com.Grabsis.models.GetTurnoDTO;
import com.Grabsis.models.InformeCristalesDTO;
import com.Grabsis.models.Turno;
import com.Grabsis.repositories.TurnoRepository;
import com.Grabsis.services.TurnoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
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
    public List<Turno> listadoporFechas(LocalDate fecha1, LocalDate fecha2) {
        List<TurnoEntity> lista= turnoRepository.listadoporFechas(fecha1,fecha2);

        List<Turno> listaTurnos = lista.stream()
                .map(entity ->modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());

        return listaTurnos;

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
       TurnoEntity opTurno = turnoRepository.findByIdTurno(Id);


            Turno turno = modelMapper.map(opTurno, Turno.class);

            return turno;


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

    @Override
    public List<Turno> listadoporPatente(String patente) {
        List<TurnoEntity> lista= turnoRepository.listadoporPatente(patente);

        List<Turno> listaTurnos = lista.stream()
                .map(entity ->modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());

        return listaTurnos;
    }

    @Override
    public List<Turno> listadoporUsuario(Long documento) {
        List<TurnoEntity> lista= turnoRepository.listadoporUsuario(documento);

        List<Turno> listaTurnos = lista.stream()
                .map(entity ->modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());

        return listaTurnos;
    }

    @Override
    public GetTurnoDTO cantidadDia(LocalDate fecha1, LocalDate fecha2) {
        GetTurnoDTO cantidad = turnoRepository.cantidadDia(fecha1,fecha2);

        return cantidad;
    }

    @Override
    public GetTurnoDTO cancelados(LocalDate fecha1, LocalDate fecha2) {
        GetTurnoDTO cantidad = turnoRepository.cancelados(fecha1,fecha2);

        return cantidad;
    }

    @Override
    public GetTurnoDTO pagados(LocalDate fecha1, LocalDate fecha2) {
        GetTurnoDTO cantidad = turnoRepository.pagados(fecha1,fecha2);

        return cantidad;
    }

    @Override
    public GetTurnoDTO registrados(LocalDate fecha1, LocalDate fecha2) {
        GetTurnoDTO cantidad = turnoRepository.registrados(fecha1,fecha2);

        return cantidad;
    }

    @Override
    public List<Turno> listadoporFormulario(String formulario) {
        List<TurnoEntity> lista= turnoRepository.listadoporFormulario(formulario);

        List<Turno> listaTurnos = lista.stream()
                .map(entity ->modelMapper.map(entity, Turno.class))
                .collect(Collectors.toList());

        return listaTurnos;
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
