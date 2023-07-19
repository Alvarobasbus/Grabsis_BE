package com.Grabsis.controllers;

import com.Grabsis.models.Detalle;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.merchantorder.MerchantOrderCreateRequest;
import com.mercadopago.client.merchantorder.MerchantOrderItemRequest;
import com.mercadopago.client.merchantorder.MerchantOrderPayerRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.payment.Payment;
import java.math.BigDecimal;

import com.Grabsis.models.Orden;
import com.Grabsis.models.Turno;
import com.Grabsis.services.DetalleService;
import com.Grabsis.services.OrdenService;
import com.Grabsis.services.TurnoService;
import com.mercadopago.MercadoPagoConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService  ordenService;
    private final DetalleService detalleService;

    private final TurnoService turnoService;

    private Orden or;
    private Orden ordenguardad;
    private Detalle detalle;
    private Orden obtenida;

    @GetMapping()
    public List<Orden> obtenerTodas(){
        List<Orden> lista= ordenService.obtenerTodos();
        return lista;
    }
    @PostMapping("/actualizar")
    public Orden actualizarOrden(@RequestBody Orden orden) {
    Orden o = ordenService.obtenerPorId(orden.getIdOrden());
    o.setTotal(orden.getTotal());
        return ordenService.crear(o);
    }

    @PostMapping("/borrado")
    public void borrarOrden(@RequestBody Orden orden) {
            ordenService.isDeleted(orden.getIdOrden());

        for (int i = 0; i < orden.getDetalle().size(); i++) {
            detalleService.isDeleted(orden.getDetalle().get(i).getIdDetalle());

        }

    }


    @PostMapping("/mercadoPago")
    public MerchantOrder mercadoPago(@RequestBody Orden orden) throws MPException, MPApiException {
        //Payment payment= new Payment();
        MerchantOrder order=new MerchantOrder();

        //MERCADO PAGO
        MercadoPagoConfig.setAccessToken("TEST-307131450014926-061818-ada0599d65ae0742c3480f7f7c7125d9-63825421");


                //anterior TEST-5107845188994161-011501-5d32fc764a5bd068612febfa522658a4-63825421

        ///crear orden
        MerchantOrderClient client = new MerchantOrderClient();

        List<MerchantOrderItemRequest> items = new ArrayList<>();


        for (int i = 0; i < orden.getDetalle().size(); i++) {

            String des= orden.getDetalle().get(i).getServicio().getDescripcion();
            double precio = orden.getDetalle().get(i).getServicio().getPrecio();

            MerchantOrderItemRequest item =
                    MerchantOrderItemRequest.builder()
                            .id("item id " +i)
                            .categoryId("item category")
                            .currencyId("BRL")
                            .description(orden.getDetalle().get(i).getServicio().getDescripcion())
                            .pictureUrl("item picture")
                            .quantity(1)
                            .unitPrice(new BigDecimal(orden.getDetalle().get(i).getServicio().getPrecio()))
                            .title(orden.getDetalle().get(i).getServicio().getDescripcion())
                            .build();

            items.add(item);



        }


        MerchantOrderCreateRequest createRequest =
                MerchantOrderCreateRequest.builder()
                        .externalReference("default")
                        .preferenceId("Preference identification")
                        .payer(MerchantOrderPayerRequest.builder().id(123L).nickname("JOHN").build())
                        .siteId("MLA")
                        .items(items)
                        .applicationId("10000000000000000")
                        .build();


        try {
            order= client.create(createRequest);
            //System.out.println(payment);
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }


        return order;




        // crear items

        /*
        PaymentClient client = new PaymentClient();

        List<PaymentItemRequest> items = new ArrayList<>();

        for (int i = 0; i < orden.getDetalle().size(); i++) {

            String des= orden.getDetalle().get(i).getServicio().getDescripcion();
            double precio = orden.getDetalle().get(i).getServicio().getPrecio();

            PaymentItemRequest item =
                    PaymentItemRequest.builder()
                            .id("PR000" + i)
                            .title("Point Mini")
                            .description(des)
                            .pictureUrl(
                                    "https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium@2x.png")
                            .categoryId("electronics")
                            .quantity(1)
                            .unitPrice(new BigDecimal(precio))
                            .build();

            items.add(item);

        }





        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .additionalInfo(
                                PaymentAdditionalInfoRequest.builder()
                                        .items(items)
                                        .payer(
                                                PaymentAdditionalInfoPayerRequest.builder()
                                                        .firstName(orden.getTurno().getUsuario().getApellido())
                                                        .lastName(orden.getTurno().getUsuario().getNombre())
                                                        .phone(
                                                                PhoneRequest.builder().areaCode("11").number("987654321").build())
                                                        .build())
                                        .shipments(
                                                PaymentShipmentsRequest.builder()
                                                        .receiverAddress(
                                                                PaymentReceiverAddressRequest.builder()
                                                                        .zipCode("12312-123")
                                                                        .stateName("Rio de Janeiro")
                                                                        .cityName("Buzios")
                                                                        .streetName("Av das Nacoes Unidas")
                                                                        .streetNumber("3003")
                                                                        .build())
                                                        .build())
                                        .build())
                        .description("Payment for product")
                        .externalReference("MP0001")
                        .installments(1)
                        .order(PaymentOrderRequest.builder().type("mercadolibre").build())
                        .payer(PaymentPayerRequest.builder().entityType("individual").type("customer").build())
                        .paymentMethodId("visa")
                        .transactionAmount(new BigDecimal(orden.getTotal()))
                        .build();


        try {
            payment = client.create(createRequest);
            System.out.println(payment);
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }



/*
        Orden ordenguardada= ordenService.crear(orden);
        Turno turno=ordenguardada.getTurno();
        turno.setPagado(true);
        turnoService.crear(turno);
        LocalDate date = LocalDate.now();
        for (int i = 0; i < orden.getDetalle().size(); i++) {
            orden.getDetalle().get(i).setOrden(ordenguardad);
            detalle=orden.getDetalle().get(i);
            if(detalle.getFecha()==null){
                detalle.setFecha(date);
            }
            detalle.setOrden(ordenguardada);
            detalle.setTurno(orden.getTurno());

            detalleService.crear(detalle);
        }

*/






    }



    @PostMapping("/crear")
    public Orden crearOrden(@RequestBody Orden orden){
     /*    or=new Orden();
         or.setEmpleado(orden.getEmpleado());
        or.setMetodoPago(orden.getMetodoPago());
        or.setTotal(orden.getTotal());

        ordenguardad= ordenService.crear(or);
        for (int i = 0; i < orden.getDetalle().size(); i++) {
            orden.getDetalle().get(i).setOrden(ordenguardad);
            detalle=detalleService.crear(orden.getDetalle().get(i));
        }


   */

        Orden ordenguardada= ordenService.crear(orden);
        Turno turno=ordenguardada.getTurno();
        turno.setPagado(true);
        turnoService.crear(turno);
        LocalDate date = LocalDate.now();
        for (int i = 0; i < orden.getDetalle().size(); i++) {
            orden.getDetalle().get(i).setOrden(ordenguardad);
            detalle=orden.getDetalle().get(i);
            if(detalle.getFecha()==null){
                detalle.setFecha(date);
            }
            detalle.setOrden(ordenguardada);
            detalle.setTurno(orden.getTurno());

            detalleService.crear(detalle);
        }


        //MERCADO PAGO
        MercadoPagoConfig.setAccessToken("YOUR_ACCESS_TOKEN");



        return ordenguardada;

    }

    @GetMapping("/{id}")
    public Orden obtenerPorId(@PathVariable Long id){
        Orden orden= ordenService.obtenerPorId(id);
        return orden;
    }

    @GetMapping("/porFechas")
    public List<Orden> obtenerPorfechas(@RequestParam(required = true) LocalDate desde,
                                         @RequestParam(required = true) LocalDate hasta){
        List<Orden> lista= ordenService.listadoporFechas(desde,hasta);

        return lista;
    }
}
