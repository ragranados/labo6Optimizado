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
        Filtro fil = new Filtro("codigo","astridteamo",4,true);
        f.update(fil);
        ArrayList<Filtro> res= f.readAll();
        for(Filtro fe : res){
            System.out.println(fe.getCodFiltro()+" | "+fe.getMarca()+" | "+fe.getStock()+" | "+fe.getExistencia());
        }
        //Filtro fil = new Filtro("codigo","marca",12,true);
        //f.insert(fil);
        
    }
    
}
