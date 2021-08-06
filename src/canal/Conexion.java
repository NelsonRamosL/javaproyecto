package canal;
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
            PreparedStatement pst = cn.prepareStatement("INSERT INTO Canal (CAN_NOMBRE,CAN_ESTADO) VALUES (?,?)");
        pst.setString(1, nombre);
        pst.setInt(2, estado);
        pst.executeUpdate();
        System.out.println("canal de comunicacion ingresado"+pst);
        
        } catch (SQLException ex) {
            System.out.println("Error Revisar funcion InsertarDatos" + ex);
        }
        
    }

    
    
    public boolean EditarDatos(int id,String nombre, int estado){
        Connection cn = Conectar();
        try {
        PreparedStatement pst = cn.prepareStatement("UPDATE canal SET CAN_NOMBRE=?, CAN_ESTADO=? WHERE CAN_ID_CANAL=?");
        pst.setString(1, nombre);
        pst.setInt(2, estado);
        pst.setInt(3, id);
        pst.executeUpdate();
        System.out.println("canal editada"+pst);
        return true;
        } catch (SQLException ex) {
            System.out.println("Error Revisar funcion EditarDatos" + ex);
        }
        return false;
    }
    
                     
    public ResultSet SeleccionarCanal(){
    Connection cn = Conectar();
    Statement st;
    ResultSet rs = null;
    try{
    st = cn.createStatement();
    rs = st.executeQuery("SELECT * FROM canal");
        
    }catch (SQLException ex){
    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
    }
    return rs;
    
    }
    
    
    
    public ResultSet SeleccionarElCanal(String canal){
      Connection cn = Conectar();
      ResultSet rs = null;
       try {
        PreparedStatement pst = cn.prepareStatement("SELECT * FROM canal WHERE CAN_NOMBRE=?");
        pst.setString(1, canal);
        rs = pst.executeQuery();
        System.out.println("Buscar el canal"+pst);
                } catch (SQLException ex) {
            System.out.println("Error Revisar funcion SeleccionarElCanal" + ex);
        }
        return rs;
      
    }
    
}
