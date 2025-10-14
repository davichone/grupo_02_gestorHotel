package modelo;

import interfaces.ServicioAdicional;

/**
 *
 * @author Franz
 */
public class ServicioSauna implements ServicioAdicional{
    @Override
    public String getNombre(){
        return "Servicio de Sauna";
    }
    
    @Override
    public double getPrecio(){
        return PRECIO_SAUNA;
    }
}
