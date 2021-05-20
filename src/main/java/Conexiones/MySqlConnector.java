package Conexiones; 

import java.sql.Connection; //Importe de librerías 
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MySqlConnector { //Creación de la clase MySqlConnector
    Connection con = null; //Inicio de la conexió nula llamada "con"
    
    public Connection conectar(){ //Función que permitirá conectarnos a la base de datos
        try{
            String db = "jdbc:mysql://localhost/personas"; //Dirección para conectarnos a la base de datos
            String usuario = "root"; //Usuario raíz para nuestra base de datos
            String pass = "";

            Class.forName("com.mysql.cj.jdbc.Driver"); //Lectura de la clase Driver
            con = DriverManager.getConnection(db, usuario, pass); //Agregado de los valores "db, usuario y pass" para nuestra variable "con"
            
            JOptionPane.showMessageDialog(null, "Base de datos conectada"); //Notidicación que nos dirá que es correcta la conexióna a nuestra base de datos
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de base de datos"); //Notidicación que nos dirá que es incorrecta la conexióna a nuestra base de datos
        }
        return con;
    }
}
