/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo6optimizado;

import java.util.ArrayList;

/**
 *
 * @author rau3
 */
public interface DAO<T> {
    Connection con = Connection.getInstance();
    
    public boolean insert(T toInsert);
    
    public ArrayList<T> read(T find, String by);
    
    public ArrayList<T> readAll();
    
    public boolean update(T toUpdate);
    
    public boolean delete(T toDelete);
    
}
