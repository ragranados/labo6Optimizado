/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultapsa;

import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import labo6.dao.FiltroDao;
import labo6.modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {
    public JLabel lblCodigo,lblMarca,lblStock,lblExistencia;
    
    public JTextField codigo,descripcion,stock;
    public JComboBox marca;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar,limpiar,actualizar;
    
    private static final int ANCHOC = 130, ALTOC =30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblMarca);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(codigo);
        container.add(marca);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
        
    }
    
    public final void agregarLabels(){
        lblCodigo = new JLabel ("Codigo");
        lblMarca = new JLabel("Marca");
        lblStock = new JLabel("Stock");
        lblExistencia = new JLabel("Stock en tienda");
        lblCodigo.setBounds(10,10, ANCHOC,ALTOC);
        lblMarca.setBounds(10,60, ANCHOC, ALTOC);
        lblStock.setBounds(10,100, ANCHOC, ALTOC);
        lblExistencia.setBounds(10,140, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        codigo = new JTextField();
        marca = new JComboBox();
        stock = new JTextField();
        si = new JRadioButton("si",true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton ("Buscar");
        insertar = new JButton ("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        
        //agregar elementos a la combo marca
        marca.addItem("FRAM");
        marca.addItem("Wix");
        marca.addItem("Luber finer");
        marca.addItem("OSK");
        //agregando los radio a un grupo
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        codigo.setBounds(140,10, ANCHOC, ALTOC);
        marca.setBounds(140,60, ANCHOC, ALTOC);
        stock.setBounds(140,100, ANCHOC, ALTOC);
        
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(240, 140, 50, ALTOC);
        
        buscar.setBounds(300, 100, ANCHOC, ALTOC);
        insertar.setBounds(10,210,ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450,210,ANCHOC,ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla() {
        //aca le colocamos el tipo de dato que tendra nuestra columa
        //si el un dato booleado aparecera un checkbox en el JTable
        tm = new DefaultTableModel() {

            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                    case 1:
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;

                }

            }
        };
        //agregamos columnas que se mostratan
        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en sucursal");
        
        //realizamos nuestra consulta a la base de datos por medio del metodo readAll
        
        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();
        
        //agregamos el resultado a nuestro table
        // se agregan de tipo object 
        
        for(Filtro fi: filtros){
            tm.addRow(new Object[]{fi.getCodigo(), fi.getMarca(), fi.getStock(), fi.getExistencia()});
            
        }
        
        // le gregamos el modelo a nuestra tabla
        
        resultados.setModel(tm);
        
        
        
    }
    
    public void eventos(){
        //insertar
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                
                Filtro f= new Filtro(codigo.getText(), marca.getSelectedItem().toString(),Integer.parseInt(stock.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro Registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo crear el filtro");
                }
            }
        });
        
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), marca.getSelectedItem().toString(),Integer.parseInt(stock.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null, "Si se pudo");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "nel");
                }
                
            }
        });
        
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null, "Fitro eliminado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Nel");
                }  
            }
        });
        
        buscar.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                
                if(f == null){
                    JOptionPane.showMessageDialog(null, "No se encontro");
                }else{
                    codigo.setText(f.getCodigo());
                    marca.setSelectedItem(f.getMarca());
                    stock.setText(Integer.toString(f.getStock()));
                    
                    if(f.getExistencia()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(false);
                    }
                }
                
            }
        });
        
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        
    }
    
    public void limpiarCampos(){
        
        codigo.setText("");
        marca.setSelectedItem("FRAM");
        stock.setText("");
        
    }
    
}
