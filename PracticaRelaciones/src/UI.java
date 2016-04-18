
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class UI extends javax.swing.JPanel {
    
    private JPanel panelCaminos,panelBotones;
    private JButton btnGenerar,btnEliminar;
    private JCheckBox checkCaminoEuler,checkCicloEuler,checkCaminoHaminton,checkCicloHaminton;
    private JTextArea txtCaminoEuler,txtCicloEuler,txtCaminoHaminton,txtCicloHaminton;
    private JLabel lb;
    private Lienzo lienzo;

    /**
     * Creates new form UI
     */
    public UI() {
        iniciarComponentes();
        botones();
    }
    
    private void iniciarComponentes(){
        this.setLayout(new BorderLayout());
        panelBotones();
        lienzo= new Lienzo();
        panelBotones();
        this.add(panelBotones,BorderLayout.SOUTH);
        this.add(lienzo, BorderLayout.CENTER);        
        panelCaminos();
        this.add(panelCaminos,BorderLayout.EAST);        
        
    }
    
    private void panelBotones(){
        panelBotones = new JPanel(new GridLayout(1,2));
        panelBotones.setBounds(0,0, 500, 20);
        panelBotones.add(btnGenerar = new JButton("Generar"));
        panelBotones.add(btnEliminar = new JButton("Limpiar"));
    }
    
    private void panelCaminos(){
        //Panel para caminos
        panelCaminos = new JPanel(new GridLayout(4,3));
        Border border = BorderFactory.createLineBorder(Color.BLACK);      
        //camino de euler check
        checkCaminoEuler = new JCheckBox("Camino de Euler");
        checkCaminoEuler.setEnabled(false);
        panelCaminos.add(checkCaminoEuler);
        //ciclo de euler check
        checkCicloEuler = new JCheckBox("Ciclo de Euler");
        checkCicloEuler.setEnabled(false);
        panelCaminos.add(checkCicloEuler);
        //camino de euler text area
        txtCaminoEuler = new JTextArea();
        txtCaminoEuler.setBorder(BorderFactory.createCompoundBorder(border, 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        txtCaminoEuler.setEditable(false);
        panelCaminos.add(txtCaminoEuler);
        //ciclo de euler text area
        txtCicloEuler = new JTextArea();
        txtCicloEuler.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10,10,10,10)));        
        txtCicloEuler.setEditable(false);
        panelCaminos.add(txtCicloEuler);        
        //camino haminton check
        checkCaminoHaminton =  new JCheckBox("Camino de Haminton");
        checkCaminoHaminton.setEnabled(false);
        panelCaminos.add(checkCaminoHaminton);
        //ciclo haminton check
        checkCicloHaminton = new JCheckBox("Ciclo de Haminton");
        checkCicloHaminton.setEnabled(false);
        panelCaminos.add(checkCicloHaminton);
        //Text area camino haminton
        txtCaminoHaminton = new JTextArea();
        txtCaminoHaminton.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10,10,10,10)));  
        txtCaminoHaminton.setEditable(false);
        panelCaminos.add(txtCaminoHaminton);
        //Text area ciclo haminton
        txtCicloHaminton = new JTextArea();
        txtCicloHaminton.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10,10,10,10)));  
        txtCicloHaminton.setEditable(false);
        panelCaminos.add(txtCicloHaminton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
 
    
    public void botones(){
        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkCaminoHaminton.setSelected(caminoHamilton());
                checkCicloHaminton.setSelected(cicloHamilton());
                checkCaminoEuler.setSelected(caminoEuler());
                checkCicloEuler.setSelected(cicloEuler());
                txtCaminoEuler.setText(mostrarCaminoEuler());
                txtCaminoHaminton.setText(mostrarCaminoHamilton());
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkCaminoEuler.setSelected(false);
                checkCicloEuler.setSelected(false);
                checkCaminoHaminton.setSelected(false);
                checkCicloHaminton.setSelected(false);
                txtCaminoEuler.setText("");
                txtCaminoHaminton.setText("");
                txtCicloEuler.setText("");
                txtCicloEuler.setText("");
                lienzo.clear();

                 
            }
        });
    }
    public String mostrarCaminoHamilton(){
        
        return "";
    }
    
    public String mostrarCaminoEuler(){        
        Grafo grafo = new Grafo(lienzo.relaciones, lienzo.elementos);
        ArrayList<Elemento> inicioFin = new ArrayList<Elemento>();
        ArrayList<Relacion> recorrido=new ArrayList<Relacion>();
        if(caminoEuler()){
            for (int i=0;i<grafo.getNum().size();i++){
                if(grafo.getNum().get(i) % 2 != 0){                    
                    inicioFin.add(lienzo.elementos.get(i));                 
                }
            }
            Elemento temp= inicioFin.get(0);
            String camino=temp.getNombre();
            for (Relacion relacion: lienzo.relaciones){
                if(relacion.getElemento1().equals(temp)){
                    for (Relacion relacion1 : lienzo.relaciones) {
                        if(relacion1.getElemento1().equals(relacion.getElemento2())){
                            if(!recorrido.contains(relacion1)){
                                recorrido.add(relacion1);
                                camino = camino+" , "+relacion1.getElemento1().getNombre();
                            }
                        }
                    }                    
                }else if(relacion.getElemento2().equals(temp)){
                    for (Relacion relacion1 : lienzo.relaciones) {
                        if(relacion1.getElemento2().equals(relacion.getElemento1())){
                            if(!recorrido.contains(relacion1)){
                                recorrido.add(relacion1);
                                camino = camino+" , "+relacion1.getElemento2().getNombre();
                            }
                        }
                    }                    
                    
                }
            }
            return camino;
        }else{
            return "No tiene camino de euler";
        }
        
    }
    
    public boolean cicloEuler(){
        int k=0;
        float aux=0;
        Grafo grafo = new Grafo(lienzo.relaciones, lienzo.elementos);
        if(grafo.conexo()){
            for (int i=0;i<grafo.getNum().size();i++){
                if(grafo.getNum().get(i)%2!=0)
                {
                
                    k++;
                
                
            }
            }
            if(k >= 1){
                return false;
            }else{
                return true;
           }
        }else{
            return false;
        }
    }
    
    public boolean caminoEuler(){
        int k=0;        
        float aux=0;
        Grafo grafo = new Grafo(lienzo.relaciones, lienzo.elementos);
        if(grafo.conexo()){
            for (int i=0;i<grafo.getNum().size();i++){
                
                if(grafo.getNum().get(i) == aux){
                    k++;
                }
                aux=grafo.getNum().get(i);
            }
            if(k <= 1){
                return true;
            }else{
                return false;
           }
        }else{
            return false;
        }
    }
    
    public boolean caminoHamilton(){
        int m=0;
        float aux=0;
        Grafo grafo =new Grafo(lienzo.relaciones, lienzo.elementos);
        if(grafo.conexo()){
            for(int i=0;i<grafo.getNum().size();i++){
                if (grafo.getNum().get(i)==aux){
                 m++;   
                }
                aux=grafo.getNum().get(i);
            }
            if(m<=1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean cicloHamilton(){
        int m=0;
        float aux=0;
        Grafo grafo = new Grafo(lienzo.relaciones, lienzo.elementos);
        if(grafo.conexo()){
            for (int i=0;i<grafo.getNum().size();i++){
                if(grafo.getNum().get(i) == aux){
                    m++;
                }
                aux=grafo.getNum().get(i);
            }
            if(m >= 2){
                return true;
            }else{
                return false;
           }
        }else{
            return false;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
