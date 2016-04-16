
public class Relacion {
    //La relaci�n elemento1 apunta a elemento2
    private Elemento elemento1, elemento2;
    public void setElemento1(Elemento elemento1) {
        this.elemento1 = elemento1;
    }
    public Elemento getElemento1() {
        return elemento1;
    }
    public void setElemento2(Elemento elemento2) {
        this.elemento2 = elemento2;
    }
    public Elemento getElemento2() {
        return elemento2;
    }
    public String toString() {
        return "(" + elemento1 + ", " + elemento2 + ")";
    }
    public boolean equals(Relacion relacion) {
        if (this == relacion)
            return true;
        
        if (elemento1.equals(relacion.getElemento1()) && elemento2.equals(relacion.getElemento2()))
            return true;
        
        return false;
    
    }
    public boolean notEquals(Relacion relacion) {
        if (this == relacion)
            return false;
        if (elemento1.equals(relacion.getElemento1()) && elemento2.equals(relacion.getElemento2()))
            return false;
        
        return true;
    }
}
