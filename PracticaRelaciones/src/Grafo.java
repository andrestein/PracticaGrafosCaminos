/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class Grafo {
    
    private ArrayList<Relacion> relaciones;
    private ArrayList<Elemento> elementos;
    private ArrayList<Integer> num;
    
    public Grafo(ArrayList<Relacion> relaciones,ArrayList<Elemento> elementos){
        this.relaciones = relaciones;
        this.elementos = elementos;
        this.grado();        
    }
    
    public boolean dirigido(){
        if(!relaciones.isEmpty()){
            for (Relacion relacion1 : relaciones) {
                for (Relacion relacion2 : relaciones) {
                    if(relacion1.getElemento1().equals(relacion2.getElemento2()) &&
                            relacion1.getElemento2().equals(relacion2.getElemento1())){
                        return false;
                    }
                }
            }
        }
        return true;        
    }
    
    public boolean conexo(){        
            for (Elemento elemento: elementos) {
                for (Relacion relacion : relaciones) {
                    if(relacion.getElemento1().equals(elemento) || relacion.getElemento2().equals(elemento)){
                        return true;
                    }
                }
            }
        
        return false;
    }    
    
    public void grado(){
        num = new ArrayList<Integer>();
        for (Elemento elem : elementos) {
            int count=0;
            for(Relacion relacion: relaciones){
                if(elem.equals(relacion.getElemento1()) || elem.equals(relacion.getElemento2())){
                    count++;
                }
            }
            num.add(count);
        }
    }
    
    public int gradoNodo(Elemento elemento){
        int count =0;
        for (Relacion relacion : relaciones) {
            if(elemento.equals(relacion.getElemento1()) || elemento.equals(relacion.getElemento2())){
                count++;
            }
        }
        return count;
    }

    /**
     * @return the num
     */
    public ArrayList<Integer> getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(ArrayList<Integer> num) {
        this.num = num;
    }
}
