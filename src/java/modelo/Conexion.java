/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kim
 */
    public class Conexion {
    public Connection conexionBD;
    private final String puerto = "3306";
    private final String bd = "db_inventario";
    private final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s",puerto,bd+"?useTimezone=true&serverTimezone=UTC");
    private final String usuario = "usr_empresa";
    private final String contra = "Empres@123";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
        try{
        Class.forName(jdbc);
        conexionBD = DriverManager.getConnection(urlConexion,usuario,contra);
                }catch(HeadlessException | ClassNotFoundException | SQLException ex){
                    System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public void cerrar_conexion(){
    try{
        conexionBD.close();
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage());
    }
    }
}
    
