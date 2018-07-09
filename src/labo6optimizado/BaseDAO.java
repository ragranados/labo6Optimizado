/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo6optimizado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author rau3
 */
public abstract class BaseDAO<T> implements DAO<T> {
    protected TableData table;
    
    

    public BaseDAO() {
    }

    public BaseDAO(TableData table) {
        this.table = table;
    }

    public abstract T mapObject(ResultSet rs);
    
    public abstract PreparedStatement getSelectStatement(Connection con, T find, String by);
    
    public abstract PreparedStatement getInsertStatement(Connection con, T _new);
    
    public abstract PreparedStatement getDeleteStatement(Connection con, T toDelete);
    
    
    @Override
    public boolean insert(T toInsert) {
        Connection con = null;
        boolean inserted = false;
        
        try {
            con = this.con.getConnection();
            PreparedStatement ps = getInsertStatement(con,toInsert); 
            inserted = ps.execute();
            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close(con);
        }
                
        return inserted;
    }

    @Override
    public ArrayList<T> read(T find, String by) {
        ArrayList<T> resultados = new ArrayList();
        Connection con = null;
        
        try {
            con = this.con.getConnection();
            PreparedStatement ps = getSelectStatement(con,find,by);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                T row = mapObject(rs);
                resultados.add(row);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close(con);
        }
        
        
        return resultados;
    }

    @Override
    public ArrayList<T> readAll() {
        Connection con = null;
        ArrayList<T> resultados = new ArrayList();
        
        try {
            con = this.con.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM "+this.table.TABLE_NAME);
            
            while(rs.next()){
                T row = mapObject(rs);
                resultados.add(row);
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(con);
        }
        
        return resultados;
    }

    @Override
    public boolean update(T toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(T toDelete) {
        boolean deleted = false;
        Connection con = null;
        
        try {
            con = this.con.getConnection();
            
            PreparedStatement ps = getDeleteStatement(con, toDelete);
            deleted = ps.execute();
            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close(con);
        }
        
        return deleted;
    }

    
    private void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    class TableData{
        final String TABLE_NAME;
        final String PRIMARY_KEY;
        final String fields[];
        
        public TableData(String table_name, String primary_key, String fields[]){
            this.TABLE_NAME = table_name;
            this.PRIMARY_KEY = primary_key;
            this.fields = fields;
        }
        
    }
    
}
