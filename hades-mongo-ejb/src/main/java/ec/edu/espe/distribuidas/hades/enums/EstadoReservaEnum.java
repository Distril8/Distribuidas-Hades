/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.hades.enums;

/**
 *
 * @author Hades Cruise Corp.
 */
public enum EstadoReservaEnum {
    
    PRE("PRE-RESERVA"),
    RES("RESERVADO"),
    ANU("ANULADO");

    String texto;

    private EstadoReservaEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
