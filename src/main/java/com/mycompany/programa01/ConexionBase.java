/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author zS20006736
 */
public class ConexionBase {

    Connection conectar = null;
    String usuario = "postgres";
    String contraseña = "123456789";
    String bd = "ejemplo";
    String ip = "localhost";
    String puerto = "5433";
    String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;
    Statement st = null;

    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Se conecto la base de datos correctamente");
            st = conectar.createStatement();
            try (ResultSet rs = st.executeQuery(""
                    + "SELECT \"clave\", \"nombre\", \"dirreccion\", \"telefono\" "
                    + "FROM \"usuarios\" ")) {
                while (rs.next()) {
                    
                    String clave = rs.getString("clave");
                    String nombre = rs.getString("nombre");
                    String dirreccion = rs.getString("dirreccion");
                    String telefono = rs.getString("telefono");
                    
                    System.out.println("[ CLAVE #" + clave + " ]");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Dirrecion: " + dirreccion);
                    System.out.println("Telefono: " + telefono);
                    System.out.println();
                }
            }
            st.close();
            conectar.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos: " + e.toString());
        }

        return conectar;
    }
}
