/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author Kim
 */
public class Marca {
    private int idmarca;
    private String marca;
    private Conexion cn;
    public Marca(){}
    public Marca(int idmarca, String marca) {
        this.idmarca = idmarca;
        this.marca = marca;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_sangre(){
    HashMap<String,String> drop = new HashMap();
    try{
        cn = new Conexion();
        String query= "SELECT idmarca as id,marca FROM marcas;";
        cn.abrir_conexion();
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
            drop.put(consulta.getString("id"), consulta.getString("marca"));
        }
        cn.cerrar_conexion();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
}
