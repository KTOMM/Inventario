/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Kim
 */
public class Producto extends Inventario {
    private int idmarca, existencia;
    private double precio_costo, precio_venta;
    private Conexion cn;

    public Producto() {}
    public Producto(int idmarca, int existencia, double precio_costo, double precio_venta, int id, String producto, String descripcion) {
        super(id, producto, descripcion);
        this.idmarca = idmarca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
   public DefaultTableModel leer(){
   DefaultTableModel tabla = new DefaultTableModel();
   try{
       cn = new Conexion();
       cn.abrir_conexion();
       
       String query = "select e.idproducto as id,e.producto,e.descripcion,e.precio_costo,e.precio_venta,e.existencia,p.marca,p.idmarca FROM productos as e inner join marcas as p on e.idmarca = p.idmarca;";
       ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
       String encabezado[] = {"id","producto","descripcion","precio_costo","precio_venta","existencia","marca","idmarca"};
       tabla.setColumnIdentifiers(encabezado);
       String datos[] = new String[10];
       while(consulta.next()){
           datos[0] = consulta.getString("id");
           datos[1] = consulta.getString("producto");
           datos[2] = consulta.getString("marca");
           datos[3] = consulta.getString("descripcion");
           datos[4] = consulta.getString("precio_costo");
           datos[5] = consulta.getString("precio_venta");
           datos[6] = consulta.getString("existencia");
           datos[7] = consulta.getString("idmarca");
           tabla.addRow(datos);
       }
       
       cn.cerrar_conexion();
   }catch(SQLException ex){
       System.out.println(ex.getMessage());
   }
   return tabla;
   }
   
   @Override
    public int agregar(){
        int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="INSERT INTO productos(producto,idmarca,descripcion,precio_costo,precio_venta,existencia) VALUES (?,?,?,?,?,?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getProducto());
        parametro.setInt(2, getIdmarca());
        parametro.setString(3, getDescripcion());
        parametro.setDouble(4, getPrecio_costo());
        parametro.setDouble(5, getPrecio_venta());
        parametro.setInt(6, getExistencia());
        
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    
    return retorno;
    }
    
    @Override
    public int modificar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update productos set producto=?, idmarca=?, descripcion=?, precio_costo=?, precio_venta=?, existencia=? where idproducto=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getProducto());
            parametro.setInt(2, this.getIdmarca());
            parametro.setString(3, this.getDescripcion());
            parametro.setDouble(4, this.getPrecio_costo());
            parametro.setDouble(5, this.getPrecio_venta());
            parametro.setInt(6, this.getExistencia());
            parametro.setInt(7, this.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage()); 
        }
        return retorno;  
        
    }
    
    @Override
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from productos where idproducto=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage()); 
        }
        return retorno;  
    }
}
