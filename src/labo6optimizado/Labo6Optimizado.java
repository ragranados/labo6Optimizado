/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo6optimizado;

import entidades.Filtro;
import java.util.ArrayList;

/**
 *
 * @author rau3
 */
public class Labo6Optimizado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FiltroDAO f= new FiltroDAO();
        ArrayList<Filtro> res= f.readAll();
        for(Filtro fe : res){
            System.out.println(fe.getCodFiltro());
        }
        // TODO code application logic here
    }
    
}
