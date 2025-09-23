/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

public class ReservaService {
    private String roomType;
    private String addExtra;
    private Byte diasEstancia;
    
    public ReservaService (String roomType, String addExtra, Byte diasEstancia){
        this.roomType=roomType;
        this.addExtra=addExtra;
        this.diasEstancia=diasEstancia;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAddExtra() {
        return addExtra;
    }

    public void setAddExtra(String addExtra) {
        this.addExtra = addExtra;
    }

    public Byte getDiasEstancia() {
        return diasEstancia;
    }

    public void setDiasEstancia(Byte diasEstancia) {
        this.diasEstancia = diasEstancia;
    }
    
    
}
