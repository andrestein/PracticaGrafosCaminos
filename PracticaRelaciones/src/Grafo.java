/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octographs;

import java.util.ArrayList;

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
        if(!this.dirigido()){
            for (Elemento elemento: elementos) {
                for (Relacion relacion : relaciones) {
                    if(relacion.getElemento1().equals(elemento) || relacion.getElemento2().equals(elemento)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean fuertementeConexo(){
        for (Relacion relacion1 : relaciones) {
            for (Relacion relacion2 : relaciones) {
                if(relacion1.getElemento1().equals(relacion2.getElemento2()) &&
                        relacion1.getElemento2().equals(relacion2.getElemento1())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public int grado(){
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
        int total=0;
        for(Integer numero:num){
            total = total+numero;
        }
        return total;
    }
    
    public boolean grafoRegular(){
        if(this.dirigido()){
            this.grado();
            for(int i=0;i<num.size();i++){
                if(num.get(0).equals(num.get(i))){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    
    public boolean grafoCompleto(){
        if(this.dirigido()){
            int k=(elementos.size()*(elementos.size()-1))/2;
            if(k == relaciones.size()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
    }   
    
    
}
