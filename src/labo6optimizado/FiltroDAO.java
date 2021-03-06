/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo6optimizado;

import entidades.Filtro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rau3
 */
public class FiltroDAO extends BaseDAO<Filtro>{
    
    public FiltroDAO(){
        table = new TableData("filtros_aceite",
                "idFiltro",
                new String[] {"codFiltro","marca","stock","existencia"});
    }

    @Override
    public Filtro mapObject(ResultSet rs) {
        Filtro pojo = new Filtro();
        try {
            pojo.setIdFiltro(rs.getInt(table.PRIMARY_KEY));
            pojo.setCodFiltro(rs.getString(table.fields[0]));
            pojo.setMarca(rs.getString(table.fields[1]));
            pojo.setStock(rs.getInt(table.fields[2]));
            pojo.setExistencia(rs.getBoolean(table.fields[3]));
            
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pojo;
    }

    @Override
    public PreparedStatement getSelectStatement(Connection con, Filtro find, String by) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement getInsertStatement(Connection con, Filtro _new) {
        PreparedStatement ps = null;
        String querty = "INSERT INTO "+table.TABLE_NAME+" ("+table.fields[0]+","+table.fields[1]+","+table.fields[2]+","+table.fields[3]+") VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(querty);
            ps.setString(1, _new.getCodFiltro());
            ps.setString(2, _new.getMarca());
            ps.setInt(3, _new.getStock());
            ps.setBoolean(4, true);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return ps;
    }

    @Override
    public PreparedStatement getDeleteStatement(Connection con, Filtro toDelete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement getUpdateStatement(Connection con, Filtro toUpdate) {
        PreparedStatement ps = null;
        String querty = "UPDATE "+table.TABLE_NAME+" SET "+table.fields[1]+" = ? , "+table.fields[2]+" = ? , "+table.fields[3]+
                " = ? WHERE "+table.fields[0]+" = ?";
        try {
            ps = con.prepareStatement(querty);
            ps.setString(1, toUpdate.getMarca());
            ps.setInt(2, toUpdate.getStock());
            ps.setBoolean(3, toUpdate.getExistencia());
            ps.setString(4, toUpdate.getCodFiltro());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }
    
    

    
    
}
