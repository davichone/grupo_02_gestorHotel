package modelo.entidades;

/**
 *
 * @author franz
 */
public class ServicioEstacionamiento implements ServicioAdicional{
    @Override
    public String getNombre(){
        return "Servicio de Estacionamiento";
    }
    
    @Override
    public double getPrecio(){
        return PRECIO_ESTACIONAMIENTO;
    }
}
