/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo6optimizado;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rau3
 */
public class Connection {

    private String url, driver, user, pass;

    
    private static Connection instance;

    public Connection() {
        cargarCredenciales();

        try {
            Class.forName(this.driver);
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public java.sql.Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, pass);
    }

    public void cerrarConexion() {
        instance = null;
    }

    private void cargarCredenciales() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost/filtros";
        this.user = "root";
        this.pass = "";

    }

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (Connection.class) {
                if (instance == null) {
                    instance = new Connection();
                }
            }
        }

        return instance;
    }

}
