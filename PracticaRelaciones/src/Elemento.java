
public class Elemento {
    private int x, y;
    private String nombre;
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public String toString() {
        return nombre;
    }
    public boolean equals(Elemento elemento) {
        if (this == elemento)
            return true;
        
        if (nombre.equals(elemento.getNombre()))
            return true;
        
        return false;
    }
}
