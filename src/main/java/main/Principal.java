    package main;

import vista.forms.LoginForm;

public class Principal {

    public static void main(String[] args) {
        

         java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true)); 
    
    }
}
/**
CREATE DATABASE hotel_db;
USE hotel_db;
CREATE TABLE Personas (
    personaID INT IDENTITY(1,1) PRIMARY KEY,
    nombres NVARCHAR(40) NOT NULL,
    tipoDocumento VARCHAR(20) NOT NULL,
    numeroDocumento NVARCHAR(20) UNIQUE NOT NULL,
    telefono NVARCHAR(20),
    email NVARCHAR(100),
    fechaRegistro DATETIME DEFAULT GETDATE()
);
CREATE TABLE Facturas (
    facturaID INT IDENTITY(1,1) PRIMARY KEY,
    numeroFactura NVARCHAR(20) NOT NULL UNIQUE,
    reservaID INT NOT NULL,
    fechaEmision DATETIME DEFAULT GETDATE(),
    tipoDocumento NVARCHAR(20) NOT NULL,
    subTotal DECIMAL(10,2) NOT NULL,
    igv DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    metodoPago NVARCHAR(30),
    estado NVARCHAR(20) DEFAULT 'Emitida',
    FOREIGN KEY (reservaID) REFERENCES Reservas(reservaID)
);
CREATE TABLE Habitaciones (
    habitacionID INT IDENTITY(1,1) PRIMARY KEY,
    id_tipo_habitacion INT NOT NULL,
    piso NVARCHAR(10) NOT NULL,
    puerta NVARCHAR(30) NOT NULL,
    estado NVARCHAR(20) NOT NULL DEFAULT 'Disponible',
    FOREIGN KEY(id_tipo_habitacion) REFERENCES TipoHabitacion(id_tipo_habitacion),
    CONSTRAINT UQ_Habitacion_PisoPuerta UNIQUE (piso, puerta)
);  */