package com.tingeso.pagoservice.services;

import com.tingeso.pagoservice.entities.PagoEntity;
import com.tingeso.pagoservice.models.AcopioModel;
import com.tingeso.pagoservice.models.CalidadModel;
import com.tingeso.pagoservice.models.ProveedorModel;
import com.tingeso.pagoservice.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    RestTemplate restTemplate;

    public AcopioModel acopioByProveedor(Integer id_proveedor){
        AcopioModel acopio = restTemplate.getForObject("http://acopio-service/acopio/" + id_proveedor, AcopioModel.class);
        if(acopio == null){
            return null;
        }
        return acopio;
    }

    public List<AcopioModel> acopiosByProveedor(Integer id_proveedor){
        List<AcopioModel> acopio = restTemplate.getForObject("http://acopio-service/acopio/" + id_proveedor, List.class);
        if(acopio.isEmpty()){
            return null;
        }
        return acopio;
    }

    public List<CalidadModel> calidadByProveedor(Integer id_proveedor){
        List<CalidadModel> calidad = restTemplate.getForObject("http://calidad-service/calidad/" + id_proveedor, List.class);
        if(calidad.isEmpty()){
            return null;
        }
        return calidad;
    }

    public List<ProveedorModel> getProveedores(){
        List<ProveedorModel> proveedores = restTemplate.getForObject("http://proveedor-service/proveedor", List.class);
        if(proveedores.isEmpty()){
            return null;
        }
        return proveedores;
    }

    public ProveedorModel proveedorById(Integer id_proveedor){
        ProveedorModel proveedor = restTemplate.getForObject("http://proveedor-service/proveedor/" + id_proveedor, ProveedorModel.class);
        return proveedor;
    }

    public List<PagoEntity> planillaPago(){
        planillaProveedores();
        return pagoRepository.findAll();
    }

    public void planillaProveedores(){
        List<ProveedorModel> proveedores = getProveedores();
        for (ProveedorModel proveedor: proveedores
        ) {
            calcularPago(proveedor.getId_proveedor());
        }
    }

    public void calcularPago(Integer id_proveedor){
        ProveedorModel proveedor = proveedorById(id_proveedor);    // Encontrar al proveedor para poder saber cuantos kilos de leche trajo
        List<AcopioModel> acopiosProveedor = acopiosByProveedor(id_proveedor);   // Listar los acopios del proveedor
        if(calidadByProveedor(id_proveedor).isEmpty() || acopiosByProveedor(id_proveedor).isEmpty()){
            return;
        }
        CalidadModel calidad = calidadByProveedor(id_proveedor).get(0);  // saber la calidad del acopio de leche
        PagoEntity pago = new PagoEntity(); // Pago para settear
        Integer klsLeche = getKlsLeche(id_proveedor);   // kls totales
        pago.setTotalKlsLeche(klsLeche);
        Integer dias = getDiasEnvio(id_proveedor);  // días que mandó leche
        Integer porGrasa = calidad.getPor_grasa();  // grasa del acopio
        pago.setPorGrasa(porGrasa); // Cambiamos el % de grasa
        pago.setSolidosTotales(calidad.getPor_solidos()); // cambiamos el % de st
        // Info proveedor fecha
        infoProveedorFecha(proveedor,acopiosProveedor,pago);
        //
        pago.setNroDiasDeEnvio(dias);
        // Pagos
        pago.setPromDiarioKlsLeche(promKls(klsLeche, dias));
        pago.setPagoXLeche(montoCategoria(proveedor.getCategoria(),klsLeche));
        pago.setPagoXGrasa(pagoPorGrasa(porGrasa,klsLeche));
        pago.setPagoXST(pagoPorSolidos(calidad.getPor_solidos(),klsLeche));
        double bonos = bonificaciones(id_proveedor, pago.getPagoXLeche());    // Calculo de bonos
        pago.setBonoFrecuencia(bonos);
        // Pago por leche
        double pagoLeche = pagoAcopioLeche(proveedor.getCategoria(),klsLeche,porGrasa,calidad.getPor_solidos(), proveedor.getId_proveedor());
        // Variaciones
        todasLasVariaciones(id_proveedor,pago, klsLeche, porGrasa, calidad.getPor_solidos());
        // Descuentos
        descuentos(pago,pagoLeche);
        // pago total
        pagoTotal(pago, pagoLeche);
        pago.setRetencion(retencion(proveedor, pago.getPagoTotal()));
        pago.setMontoFinal(pago.getPagoTotal() - pago.getRetencion());
        if (!exist(pago)){
            pagoRepository.save(pago);
        }
    }

    public  Double dctoLeche(Double variacion, Double pagoLeche){
        Double dcto = 0.0;
        if(variacion >=0 && variacion <= 8){
            dcto = 0.0 * pagoLeche;
        }
        if(variacion >= 9 && variacion <= 25){
            dcto = 0.07 * pagoLeche;
        }
        if(variacion >= 26 && variacion <= 45){
            dcto = 0.15 * pagoLeche;
        }
        if(variacion >= 46){
            dcto = 0.3 * pagoLeche;
        }
        dcto = Math.round(dcto*100.0)/100.0;
        return dcto;
    }

    public double dctoGrasa(double variacion, double pagoTotal) {
        double desc = 0;
        if (variacion >= 0 && variacion <= 15) {
            desc = 0;
        } else if (variacion >= 16 && variacion <= 25) {
            desc = 0.12;
        } else if (variacion >= 26 && variacion <= 40) {
            desc = 0.2;
        } else if (variacion >= 41) {
            desc = 0.3;
        }
        return desc * pagoTotal;
    }


    public double dctoSolidos(double variacion, double pagoTotal) {
        double desc = 0;
        if (variacion >= 0 && variacion <= 6) {
            desc = 0;
        } else if (variacion > 6 && variacion < 13) {
            desc = 0.18;
        } else if (variacion > 12 && variacion < 36) {
            desc = 0.27;
        } else if (variacion > 35) {
            desc = 0.45;
        }
        return Math.round(desc * pagoTotal);
    }

    public double pagoAcopioLeche(String cat, Integer klsLeche, Integer porGrasa, Integer porST, Integer id_proveedor){
        Integer pagoCategoria = montoCategoria(cat,klsLeche);
        Integer pagoGrasa = pagoPorGrasa(porGrasa, klsLeche);
        Integer pagoST = pagoPorSolidos(porST, klsLeche);
        double bonos = bonificaciones(id_proveedor, pagoCategoria);
        double pagos = pagoCategoria + pagoGrasa + (double)pagoST;
        return (pagos + bonos);

    }

    public void pagoTotal(PagoEntity pago, double pagoLeche){
        double dcto = pago.getDctoVarST()+pago.getDctoVarGrasa()+pago.getDctoVarLeche();
        double total = pagoLeche-dcto;
        if (total < 0){
            total= total*(-1);
        }
        pago.setPagoTotal(total);
    }

    public void infoProveedorFecha(ProveedorModel proveedor, List<AcopioModel> acopiosProveedor, PagoEntity pago){
        pago.setFecha(nuevaFecha(acopiosProveedor.get(0).getFecha().toString()));   // formato de fecha
        pago.setId_proveedor(proveedor.getId_proveedor());
        pago.setNombreProveedor(proveedor.getNombre());
    }

    public List<PagoEntity> pagosByCodigo(Integer id_proveedor) {
        List<PagoEntity> pagosTotales = pagoRepository.findAll();
        List<PagoEntity> pagosProveedor = new ArrayList<>();
        for (int i = 0; i < pagosTotales.size(); i++) {
            if (pagosTotales.get(i).getId_proveedor().equals(id_proveedor)) {
                pagosProveedor.add(pagosTotales.get(i));
            }
        }
        return pagosProveedor;
    }
    public void todasLasVariaciones(Integer id_proveedor, PagoEntity pagos, Integer klsLeche, Integer porGrasa, Integer porST){
        List<PagoEntity> pagosProveedor = pagosByCodigo(id_proveedor);
        if(pagosProveedor.isEmpty()){
            pagos.setVariacionLeche(0.0);
            pagos.setVarGrasa(0.0);
            pagos.setVarST(0.0);
        }else {
            Integer cant = (pagosProveedor.size() - 1);
            Double varLeche = variacion(pagosProveedor.get(cant).getTotalKlsLeche(), klsLeche);
            Double varGrasa = varGrasaySolido(pagosProveedor.get(cant).getPorGrasa(), porGrasa);
            Double varSolidos = varGrasaySolido(pagosProveedor.get(cant).getSolidosTotales(), porST);
            pagos.setVariacionLeche(varLeche);
            pagos.setVarGrasa(varGrasa);
            pagos.setVarST(varSolidos);
        }
    }

    public void descuentos(PagoEntity pagos, double pagoTotal){
        pagos.setDctoVarLeche(dctoLeche(pagos.getVariacionLeche(), pagoTotal));
        pagos.setDctoVarGrasa(dctoGrasa(pagos.getVarGrasa(),pagoTotal));
        pagos.setDctoVarST(dctoSolidos(pagos.getVarST(),pagoTotal));
    }

    public double retencion(ProveedorModel proveedor, double pagoTotal){
        double ret = 0.0;
        if (proveedor.getRetencion().equalsIgnoreCase("SI")){
            ret = Math.round(0.13*pagoTotal);
        }
        return ret;
    }

    public boolean exist(PagoEntity pago){
        List<PagoEntity> pagos = pagoRepository.findAll();
        boolean flag = false;
        Integer prov = pago.getId_proveedor();
        String fecha = pago.getFecha();
        for (PagoEntity pagoEntity : pagos) {
            if (pagoEntity.getId_proveedor().equals(prov) && pagoEntity.getFecha().equals(fecha)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String nuevaFecha(String fecha){
        return fecha.substring(0, fecha.length() - 2) +  quincena(fecha).toString();
    }

    public Integer quincena(String fecha){
        Integer mitadMes = 15;
        Integer quincena = 0;
        if(Integer.parseInt(fecha.substring(8)) <= mitadMes){
            quincena = 1;
        }else if(Integer.parseInt(fecha.substring(8)) >= mitadMes){
            quincena = 2;
        }
        return quincena;
    }

    public Integer getKlsLeche(Integer id_proveedor){
        ProveedorModel proveedor = proveedorById(id_proveedor);
        Integer kilos = 0;
        List<AcopioModel> acopiosprov = restTemplate.getForObject("http://acopio-service/acopio/proveedor/" + id_proveedor, List.class);

        for (AcopioModel acopio: acopiosprov
        ) {
            kilos = kilos + acopio.getKls_leche();
        }
        return kilos;
    }

    public int getDiasEnvio(int id_proveedor) {
        int contDias = 0;
        ProveedorModel proveedor = proveedorById(id_proveedor);
        List<AcopioModel> acopios = restTemplate.getForObject("http://acopio-service/acopio/proveedor/" + id_proveedor, List.class);
        for (int i = 0; i < acopios.size() - 1; i++) {
            AcopioModel acopioA = acopios.get(i);  // Acopio actual
            AcopioModel acopioB = acopios.get(i + 1);  // Acopio sgte
            LocalDate fechaA = acopioA.getFecha();
            LocalDate fechaB = acopioB.getFecha();
            String turnoA = acopioA.getTurno();
            String turnoB = acopioB.getTurno();
            if (fechaA.equals(fechaB) && turnoA != null && turnoB != null && !turnoA.equals(turnoB)){
                contDias++;
            } else if (!fechaA.equals(fechaB) && turnoA != null && turnoB != null){
                contDias++;
            }
        }
        if (contDias >15){
            contDias = 15;
        }
        return contDias;
    }

    public double promKls(Integer klsLeche, Integer diasEnvio) {
        return Math.round((double)klsLeche / diasEnvio);
    }

    public double bonificaciones(Integer id_proveedor, double pagoLeche) {
        List<AcopioModel> acopProv = restTemplate.getForObject("http://acopio-service/acopio/proveedor/" + id_proveedor, List.class);
        List<Integer> list = mrngT(acopProv);
        double bono= porcent(list.get(0), list.get(1));
        return bono*pagoLeche;
    }

    public List<Integer> mrngT(List<AcopioModel> acopiosProv) {
        Integer acopiosMrng = 0;
        Integer acopiosTarde = 0;
        for (AcopioModel acopios : acopiosProv) {
            if (acopios.getTurno().equals("M")) {
                acopiosMrng++;
            } else if (acopios.getTurno().equals("T")) {
                acopiosTarde++;
            }
        }
        return List.of(acopiosMrng, acopiosTarde);
    }

    public double porcent(int mrng, int tarde) {
        if (mrng >= 10 && tarde >= 10) {
            return 0.2;
        } else if (mrng >= 10 && tarde < 10) {
            return 0.12;
        } else if (tarde >= 10 && mrng < 10) {
            return 0.08;
        } else {
            return 0.0;
        }
    }

    public Integer montoCategoria(String categoria, Integer kls_leche){
        if(categoria.equalsIgnoreCase("A")){
            return 700*kls_leche;
        }
        if(categoria.equalsIgnoreCase("B")){
            return 550*kls_leche;
        }
        if (categoria.equalsIgnoreCase("C")){
            return 400*kls_leche;
        }
        if (categoria.equalsIgnoreCase("D")){
            return 250*kls_leche;
        }else
            return kls_leche;
    }

    public Integer pagoPorGrasa(Integer porGrasa, Integer kls_leche){
        if(porGrasa > 0 && porGrasa <= 20){
            return kls_leche*30;
        }if(porGrasa > 20 && porGrasa <= 45){
            return kls_leche*80;
        }if(porGrasa > 45){
            return kls_leche*120;
        }else{
            return null;
        }
    }

    public Integer pagoPorSolidos(Integer porSolidos, Integer kls_leche){
        if(porSolidos > 0 && porSolidos <= 7){
            return kls_leche*-130;
        }if (porSolidos > 7 && porSolidos <= 18){
            return kls_leche*-90;
        }if(porSolidos > 18 && porSolidos <= 35) {
            return kls_leche * 95;
        }if (porSolidos > 35){
            return kls_leche * 150;
        }else{
            return null;
        }
    }

    public static double variacion(double valorFinal, double valorInicial) {
        return Math.round(((valorFinal - valorInicial) / valorInicial) * 100);
    }

    public Double varGrasaySolido(Integer nuevoTotal, Integer valor_antiguo){
        double variacion = (double)valor_antiguo - (double)nuevoTotal;
        if(variacion < 0.0){
            variacion = 0.0;
        }
        return (double) Math.round(variacion);
    }

}
