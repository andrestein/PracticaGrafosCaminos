/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.GradientPaint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Lienzo extends JPanel {

    
    
    public final ArrayList<Elemento> elementos = new ArrayList<Elemento>();
    public final ArrayList<Relacion> relaciones = new ArrayList<Relacion>();
    
    
    private Elemento elementoSeleccionado = new Elemento();
    private Elemento elementoAnterior = new Elemento();
    private boolean clicked = false;
    private boolean clickedFlechaS = false;

    //Tamaño del elemento
    private final int tElemento = 10;
    private final int tArco = tElemento / 2;
    private final Point flecha = new Point(-1, -1);

    private JPanel jGraficas = null;

    //Tipos de fuente a usar
    //fuentes
    private static final Font fContenido = new Font("Courier", Font.BOLD, 12);  //  @jve:decl-index=0:
    private static final Font fPeso = new Font("Helvetica", Font.BOLD, 10);
    private static final Font fNormal = new Font("TimesRoman", Font.PLAIN, 10);  //  @jve:decl-index=0:

    private static final long serialVersionUID = 2297839924871376233L;

    public Lienzo() {
        super();
        initialize();
        
    }

    private void initialize() {
        getJGraficas();
    }

    public void clear(){
        relaciones.clear();
        elementos.clear();
        repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setFont(fNormal);
        
        for (Elemento elemento : elementos) {
            dibujarElemento(grafico, elemento);
        }
        for (Relacion relacion : relaciones) {
            dibujarRelacion(grafico, relacion, Color.GRAY);
        }

        if (clickedFlechaS) {
            grafico.setColor(Color.RED);
            drawArrow(grafico, elementoSeleccionado.getX() + tArco, elementoSeleccionado.getY() + tArco, flecha.x + tArco, flecha.y + tArco, 3L, 2);
        }
    }
    
    private JPanel getJGraficas() {
       
        if (jGraficas == null) {
            jGraficas = this;
            jGraficas.setAutoscrolls(true);
            jGraficas.setPreferredSize(new Dimension(800, 600));            
            jGraficas.setBackground(Color.WHITE);
            jGraficas.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseReleased(java.awt.event.MouseEvent e) {
                    System.out.print("Clicked");
                    jS_mouseReleased(e);
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    jS_mousePressed(e);
                }
            });
            jGraficas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseDragged(java.awt.event.MouseEvent e) {
                    jS_mouseDragged(e);
                }
            });
            GridLayout borderLayout = new GridLayout(1, 2);
            borderLayout.setHgap(5);

            jGraficas.setLayout(borderLayout);

        }
        return jGraficas;
    }

    ////////////////////////////////////////////////
    // Dibujo de elementos, relaciones, y listeners
    protected void relaciones_mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (elementoEncima(e.getX(), e.getY())) {
                clicked = true;
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (elementoEncima(e.getX(), e.getY())) {
                clickedFlechaS = true;
            }
        }
    }

    protected void relaciones_mouseDragged(MouseEvent e) {
        if (clicked) {
            elementoSeleccionado.setX(e.getX() - tArco);
            elementoSeleccionado.setY(e.getY() - tArco);
            this.repaint();
        } else if (clickedFlechaS) {
            flecha.x = e.getX() - tArco;
            flecha.y = e.getY() - tArco;
            this.repaint();
        }
    }

    protected void relaciones_mouseReleased(MouseEvent e) {
        if (clickedFlechaS && elementoEncima(e.getX(), e.getY())) {
            Relacion nuevaRelacion = new Relacion();
            nuevaRelacion.setElemento1(elementoAnterior);
            nuevaRelacion.setElemento2(elementoSeleccionado);
            relaciones.add(nuevaRelacion);
        }
        flecha.x = -1;
        flecha.y = -1;
        clicked = false;
        clickedFlechaS = false;
        this.repaint();
    }

    private void dibujarElemento(Graphics grafico, Elemento elemento) {
        int x = elemento.getX();
        int y = elemento.getY();

        Color temp = grafico.getColor();
        Graphics2D g2d = (Graphics2D) grafico;
        GradientPaint gradient = new GradientPaint(10, 10, Color.GREEN, 30, 30, Color.BLUE, true);
        g2d.setPaint(gradient);
        grafico.fillArc(x, y, tElemento, tElemento, 0, 360);

        grafico.setColor(Color.BLACK);
        Stroke st = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.5f));
        grafico.drawArc(x, y, tElemento + 1, tElemento + 1, 0, 360);
        g2d.setStroke(st);

        grafico.setColor(Color.RED);
        grafico.setFont(fContenido);
        grafico.drawString("(" + elemento.getNombre() + ")", x, y - tElemento / 2);
        grafico.setFont(fNormal);
        grafico.setColor(temp);
    }

    private void dibujarRelacion(Graphics grafico, Relacion relacion, Color color) {
        dibujarRelacion(grafico, relacion, color, 1f);
    }

    private void dibujarRelacion(Graphics grafico, Relacion relacion, Color color, float ancho) {
        Elemento elemento1, elemento2;
        int x1, x2, y1, y2, x, y;
        elemento1 = relacion.getElemento1();
        elemento2 = relacion.getElemento2();
        x1 = elemento1.getX();
        x2 = elemento2.getX();
        y1 = elemento1.getY();
        y2 = elemento2.getY();
        Color temp = grafico.getColor();
        grafico.setColor(color);

        double dist = distancia(x1, x2, y1, y2);
        double angulo = Math.asin(Math.abs(y2 - y1) / dist);

        int Cop = (int) (Math.sin(angulo) * (tArco + 1));
        int Cad = (int) (Math.cos(angulo) * (tArco + 1));

        if (x2 > x1) {
            if (y2 > y1) {
                drawArrow(grafico, x1 + Cad + tArco, y1 + Cop + tArco, x2 - Cad + tArco, y2 - Cop + tArco, 3L, ancho);
            } else if (y2 == y1) {
                drawArrow(grafico, x1 + Cad + tArco, y1 + tArco, x2 - Cad + tArco, y2 + tArco, 3L, ancho);
            } else {
                drawArrow(grafico, x1 + Cad + tArco, y1 - Cop + tArco, x2 - Cad + tArco, y2 + Cop + tArco, 3L, ancho);
            }
        } else if (x2 < x1) {
            if (y2 > y1) {
                drawArrow(grafico, x1 - Cad + tArco, y1 + Cop + tArco, x2 + Cad + tArco, y2 - Cop + tArco, 3L, ancho);
            } else if (y2 == y1) {
                drawArrow(grafico, x1 - Cad + tArco, y1 + tArco, x2 + Cad + tArco, y2 + tArco, 3L, ancho);
            } else {
                drawArrow(grafico, x1 - Cad + tArco, y1 - Cop + tArco, x2 + Cad + tArco, y2 + Cop + tArco, 3L, ancho);
            }
        } else {
            if (y2 < y1) {
                drawArrow(grafico, x1 + tArco, y1 - Cop + tArco, x2 + tArco, y2 + Cop + tArco, 3L, ancho);
            } else if (y2 == y1) {
                grafico.drawOval(x1 - Cad - tElemento - 15, y1 - Cop - tArco - 4, tElemento + 20, tElemento + 5);
            } else {
                drawArrow(grafico, x1 + tArco, y1 + Cop + tArco, x2 + tArco, y2 - Cop + tArco, 3L, ancho);
            }
        }
    }

    private static void drawArrow(Graphics g, int xCenter, int yCenter, int x, int y, float stroke, float ancho) {
        Graphics2D g2d = (Graphics2D) g;
        double aDir = Math.atan2(xCenter - x, yCenter - y);
        g2d.setStroke(new BasicStroke(ancho));
        g2d.drawLine(x, y, xCenter, yCenter);
        g2d.setStroke(new BasicStroke(1f));
        Polygon tmpPoly = new Polygon();
        int i1 = 6 + (int) (stroke * 1.5);
        int i2 = 3 + (int) stroke;
        tmpPoly.addPoint(x, y);
        tmpPoly.addPoint(x + xCor(i1, aDir + .5), y + yCor(i1, aDir + .5));
        tmpPoly.addPoint(x + xCor(i2, aDir), y + yCor(i2, aDir));
        tmpPoly.addPoint(x + xCor(i1, aDir - .5), y + yCor(i1, aDir - .5));
        tmpPoly.addPoint(x, y);
        g2d.drawPolygon(tmpPoly);
        g2d.fillPolygon(tmpPoly);
    }

    private static int yCor(int len, double dir) {
        return (int) (len * Math.cos(dir));
    }

    private static int xCor(int len, double dir) {
        return (int) (len * Math.sin(dir));
    }

    private boolean elementoEncima(int x, int y) {
        int xElemento, yElemento;
        for (int i = 0; i < elementos.size(); i++) {
            xElemento = elementos.get(i).getX();
            yElemento = elementos.get(i).getY();
            if (distancia(x, xElemento, y, yElemento) < tElemento + 5) {
                elementoAnterior = elementoSeleccionado;
                elementoSeleccionado = elementos.get(i);
                return true;
            }
        }
        return false;
    }

    private double distancia(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    protected void jS_mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!elementoEncima(e.getX(), e.getY())) {
                String nombre = JOptionPane.showInputDialog("Escriba el nombre del elemento");
                if (nombre != null && !nombre.equals("")) {
                    Elemento temporal = new Elemento();
                    temporal.setX(e.getX() - tArco);
                    temporal.setY(e.getY() - tArco);
                    temporal.setNombre(nombre);

                    elementos.add(temporal);
                    this.repaint();
                }
            } else {
                clicked = true;
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (elementoEncima(e.getX(), e.getY())) {
                clickedFlechaS = true;
            }
        }
    }

    protected void jS_mouseDragged(MouseEvent e) {
        if (clicked) {
            elementoSeleccionado.setX(e.getX() - tArco);
            elementoSeleccionado.setY(e.getY() - tArco);
            this.repaint();
        } else if (clickedFlechaS) {
            flecha.x = e.getX() - tArco;
            flecha.y = e.getY() - tArco;
            this.repaint();
        }
    }

    protected void jS_mouseReleased(MouseEvent e) {
        if (clickedFlechaS && elementoEncima(e.getX(), e.getY())) {
            Relacion nuevaRelacion = new Relacion();
            nuevaRelacion.setElemento1(elementoAnterior);
            nuevaRelacion.setElemento2(elementoSeleccionado);
            relaciones.add(nuevaRelacion);
        }
        flecha.x = -1;
        flecha.y = -1;
        clicked = false;
        clickedFlechaS = false;
        this.repaint();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param jContentPane the jContentPane to set
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * @return the jContentPane
     */
}
