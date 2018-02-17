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
public enum OrdenTipoValorEnum {

    INC("INCREMENTO"),
    DEC("DECREMENTO");

    private String texto;

    private OrdenTipoValorEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
