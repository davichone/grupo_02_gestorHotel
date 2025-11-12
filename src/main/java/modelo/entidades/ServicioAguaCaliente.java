package modelo.entidades;

/**
 *
 * @author franz
 */
public class ServicioAguaCaliente implements ServicioAdicional{
    @Override
    public String getNombre(){
        return "Servicio de Agua Caliente";
    }
    
    @Override
    public double getPrecio(){
        return PRECIO_AGUA_CALIENTE;
    }
}
