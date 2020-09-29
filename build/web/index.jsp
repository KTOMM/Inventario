<%-- 
    Document   : index
    Created on : 19/09/2020, 06:00:30 PM
    Author     : Kim
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Marca"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <h1>FORMULARIO PRODUCTOS</h1>
        <button type="button" name="btn_nuevo" id="btn_nuevo" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_producto" onclick="limpiar()">Nuevo</button>
        
        <div class="container">
        <div class="modal fade" id="modal_producto" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
            <form action="sr_producto" method="post" class="form-group">
                <label for="lbl_id"><b>ID:</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value ="0" readonly>  
                <label for="lbl_producto"><b>PRODUCTO:</b></label>
                <input type="text" name="txt_producto" id="txt_producto" class="form-control" required>  
                 <label for="lbl_sangre"><b>MARCA</b></label>
                <select name="drop_sangre" id="drop_list" class="form-control">
                    <% 
                        Marca marca = new Marca();
                        HashMap<String, String> drop = marca.drop_sangre();
                        for(String i: drop.keySet()){
                            out.println("<option value='" + i + "'>" + drop.get(i) +"</option>");
                        }
                    %>
                </select>
                <label for="lbl_descripcion"><b>DESCRIPCION:</b></label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" required>
                <label for="lbl_precio_costo"><b>PRECIO COSTO:</b></label>
                <input type="text" name="txt_precio_costo" id="txt_precio_costo" class="form-control" required>  
                <label for="lbl_precio_venta"><b>PRECIO VENTA:</b></label>
                <input type="text" name="txt_precio_venta" id="txt_precio_venta" class="form-control" required>
                <label for="lbl_existencia"><b>EXISTENCIA:</b></label>
                <input type="number" name="txt_existencia" id="txt_existencia" class="form-control" required> 
                <br>
                <input type="submit" class="btn btn-primary" name="btn_agregar" id="btn_agregar" value="agregar" >
                <input type="submit" class="btn btn-success" name="btn_modificar" id="btn_modificar" value="modificar">
                <input type="submit" class="btn btn-danger" name="btn_eliminar" id="btn_eliminar" value="eliminar"onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">
                <button type="button" class="btn btn-warning" data-dismiss="modal">Cerrar</button>
               
            </form>
        </div>
      </div>
      
    </div>
  </div>  
                
            <table class="table table-striped">
    <thead>
      <tr>
        <th>PRODUCTO</th>
        <th>MARCA</th>
        <th>DESCRIPCION</th>
        <th>PRECIO COSTO</th>
        <th>PRECIO VENTA</th>
        <th>EXISTENCIA</th>
      </tr>
    </thead>
    <tbody id="tbl_producto">
        <% 
        Producto producto= new Producto();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = producto.leer();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0) + " data-id_p=" + tabla.getValueAt(t,7) + ">");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("</tr>");
        
        }
        %>
      
    </tbody>
  </table>
        </div>
        
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
        
     function limpiar(){
   $("#txt_id").val(0);
   $("#txt_producto").val('');
   $("#drop_list").val(1);
   $("#txt_descripcion").val('');
   $("#txt_precio_costo").val('');
   $("#txt_precio_venta").val('');
   $("#txt_existencia").val('');
     }   
        $('#tbl_producto').on('click','tr td', function(evt){
   var target,id,id_p,producto,descripcion,pcosto,pventa,existencia;
   
   target = $(event.target);
   id = target.parent().data('id');
   id_p = target.parent().data('id_p');
   producto= target.parents("tr").find("td").eq(0).html();
   descripcion= target.parents("tr").find("td").eq(2).html();
   pcosto= target.parents("tr").find("td").eq(3).html();
   pventa= target.parents("tr").find("td").eq(4).html();
   existencia= target.parents("tr").find("td").eq(5).html();
   
   $("#txt_id").val(id);
   $("#txt_producto").val(producto);
   $("#txt_descripcion").val(descripcion);
   $("#txt_precio_costo").val(pcosto);
   $("#txt_precio_venta").val(pventa);
   $("#txt_existencia").val(existencia);
   $("#drop_list").val(id_p);
   $("#modal_producto").modal('show');
   });
</script>
    </body>
</html>