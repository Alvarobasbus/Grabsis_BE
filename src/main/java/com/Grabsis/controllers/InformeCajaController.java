package com.Grabsis.controllers;


import com.Grabsis.models.InformeCaja;
import com.Grabsis.models.Turno;
import com.Grabsis.services.DepositoService;
import com.Grabsis.services.EgresoService;
import com.Grabsis.services.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/informeCaja")
@RequiredArgsConstructor
public class InformeCajaController {

    private final DepositoService depositoService;
    private final EgresoService egresoService;
    private final OrdenService ordenService;


    @GetMapping("/porFechas")
    public ResponseEntity<InformeCaja> informePorfechas(@RequestParam(required = true) LocalDate desde,
                                                        @RequestParam(required = true) LocalDate hasta){

        InformeCaja informeCaja=new InformeCaja();
        informeCaja.setEfectivo(ordenService.efectivoTotal(desde, hasta));
        informeCaja.setCredito(ordenService.creditoTotal(desde, hasta));
        informeCaja.setDebito(ordenService.debitoTotal(desde, hasta));
        informeCaja.setTransferencia(ordenService.transferenciaTotal(desde, hasta));
        informeCaja.setMercadoPago(ordenService.mercadoPagoTotal(desde, hasta));
        informeCaja.setDeposito(depositoService.depositoTotal(desde, hasta));
        informeCaja.setEgreso(egresoService.egresoTotal(desde, hasta));

        Double total= (informeCaja.getDebito()+ informeCaja.getCredito() + informeCaja.getMercadoPago() + informeCaja.getEfectivo() + informeCaja.getTransferencia());
        Double saldoCaja= (informeCaja.getEfectivo() - informeCaja.getDeposito() - informeCaja.getEgreso());
        informeCaja.setSaldoTotal(total);
        informeCaja.setSaldoCaja(saldoCaja);


        return ResponseEntity.ok(informeCaja);
    }


}
