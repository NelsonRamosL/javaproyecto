package pruebaconeccion;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nelson
 */
public class Conexion {

  
    
    public Connection Conectar() {
      Connection cn=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/pruebasdreamgifts", "root", "");
            System.out.println("Conectado a la base de datos");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
                }
     return cn; 
    }
  
    
    public void InsertarDatos(String nombre, int estado){
        Connection cn = Conectar();
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO comuna(COM_NOMBRE,COM_ESTADO) VALUES (?,?)");
        pst.setString(1, nombre);
        pst.setInt(2, estado);
        pst.executeUpdate();
        System.out.println("Comuna ingresada"+pst);
        
        } catch (SQLException ex) {
            System.out.println("Error Revisar funcion InsertarDatos" + ex);
        }
        
    }

    
    
    public boolean EditarDatos(int id,String nombre, int estado){
        Connection cn = Conectar();
        try {
        PreparedStatement pst = cn.prepareStatement("UPDATE comuna SET COM_NOMBRE=?, COM_ESTADO=? WHERE COM_ID=?");
        pst.setString(1, nombre);
        pst.setInt(2, estado);
        pst.setInt(3, id);
        pst.executeUpdate();
        System.out.println("Comuna editada"+pst);
        return true;
        } catch (SQLException ex) {
            System.out.println("Error Revisar funcion EditarDatos" + ex);
        }
        return false;
    }
    
    
    public ResultSet SeleccionarTodosComunas(){
    Connection cn = Conectar();
    Statement st;
    ResultSet rs = null;
    try{
    st = cn.createStatement();
    rs = st.executeQuery("SELECT * FROM comuna");
        
    }catch (SQLException ex){
    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
    }
    return rs;
    
    }
    
    
    
    public ResultSet SeleccionarLaComunas(String comuna){
      Connection cn = Conectar();
      ResultSet rs = null;
       try {
        PreparedStatement pst = cn.prepareStatement("SELECT * FROM comuna WHERE COM_NOMBRE=?");
        pst.setString(1, comuna);
        rs = pst.executeQuery();
        System.out.println("Buscar la comuna"+pst);
                } catch (SQLException ex) {
            System.out.println("Error Revisar funcion SeleccionarLaComuna" + ex);
        }
        return rs;
      
    }
    
}


