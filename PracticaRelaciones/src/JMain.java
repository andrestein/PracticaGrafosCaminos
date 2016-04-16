/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author audoban
 */
public class JMain extends JFrame {

    
    private UI ui;

    JMain() {
        super();
        initialize();
    }

    protected final void initialize() {
        ui = new UI();        
        this.setSize(new Dimension(600, 400));
        this.setResizable(false);
        this.setTitle("Practica grafos");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(ui);
        
    }

    public static void main(String args[]) {    
        
       JMain contenedor = new JMain();
       contenedor.setVisible(true);     
    }
    
    
    public void recorido ()
    {        
        Lienzo lienzo  = new Lienzo();
        for(int i =0 ;i <lienzo.relaciones.size();i++ )
        {
        System.out.println(lienzo.relaciones.get(i));
     
        }
    }
}

