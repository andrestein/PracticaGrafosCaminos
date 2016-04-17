import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Desarrollo interfaz
 * @author Daniel Guillermo Correa Isaza
 * @url    http://www.sinfocol.org/
 */

public class JMain extends JFrame {
    private static final long serialVersionUID = 1L;
    //Vectores que almacenan los elementos y las relaciones
    private Vector<Elemento> elementos = new Vector<Elemento>();  //  @jve:decl-index=0:
    private Vector<Relacion> R = new Vector<Relacion>();
    /*private Vector<Relacion> S = new Vector<Relacion>();*/
    private Vector<Relacion> universal = new Vector<Relacion>(); //Se usa para almacenar las relaciones del producto cartesiano

    private Elemento elementoSeleccionado = new Elemento();  //  @jve:decl-index=0:
    private Elemento elementoAnterior = new Elemento();
    private boolean clicked = false;
    private boolean clickedFlechaR = false;
    private boolean clickedFlechaS = false;


    //Tamaño del elemento
    private int tElemento = 10;
    private int tArco = tElemento / 2;
    private Point flecha = new Point(-1, -1);  //  @jve:decl-index=0:

    private JPanel jContentPane = null;
    private JPanel jGraficas = null;
    private JPanel jR = null;
    /*private JPanel jS = null;*/
    private JPanel jBotones = null;
    private JPanel jResultados = null;
    private JPanel jCaminos;

    //Botones
    private JButton btnGenerar = null;
    //private JButton btnUnion = null;
    //private JButton btnDifsimetrica = null;
    //private JButton btnDiferenciaRS = null;
    //private JButton btnDiferenciaSR = null;
    //private JButton btnComplementoR = null;
    //private JButton btnComplementoS = null;
    //private JButton btnProducto = null;
    //private JButton btnCalcular = null;
    private JButton btnLimpiar = null;

    //Definicion de las listas
    /*private JScrollPane jspInterseccion = null;
    private JScrollPane jspUnion = null;
    private JScrollPane jspDifsimetrica = null;
    private JScrollPane jspDiferenciaRS = null;
    private JScrollPane jspDiferenciaSR = null;
    private JScrollPane jspComplementoR = null;
    private JScrollPane jspComplementoS = null;
    private JScrollPane jspProducto = null;
    private JList lstInterseccion = null;
    private JList lstUnion = null;
    private JList lstDifsimetric = null;
    private JList lstDiferenciaRS = null;
    private JList lstDiferenciaSR = null;
    private JList lstComplementoR = null;
    private JList lstComplementoS = null;
    private JList lstProducto = null;
    private DefaultListModel interseccion = null;
    private DefaultListModel union = null;
    private DefaultListModel diferenciaSimetrica = null;
    private DefaultListModel diferenciaRS = null;
    private DefaultListModel diferenciaSR = null;
    private DefaultListModel complementoR = null;
    private DefaultListModel complementoS = null;
    private DefaultListModel producto = null;
*/
    //Tipos de fuente a usar
    //fuentes
    private static Font fContenido = new Font("Courier", Font.BOLD,  12);  //  @jve:decl-index=0:
    private static Font fPeso = new Font("Helvetica", Font.BOLD,  10);
    private static Font fNormal = new Font("TimesRoman", Font.PLAIN,  10);  //  @jve:decl-index=0:

    /**
     * This is the default constructor
     */
    public JMain() {
        super();
        initialize();
    }

    /**
     * main
     */
    public static void main(String args[]) {
        JMain jr = new JMain();
        jr.setVisible(true);
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(861, 457);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Práctica Grafos");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jCaminos = new PanelCamino();
            BorderLayout bordeGeneral = new BorderLayout();
            bordeGeneral.setVgap(5);
            jContentPane.setLayout(bordeGeneral);
            jContentPane.add(getJGraficas(), BorderLayout.CENTER);
            jContentPane.add(getJBotones(), BorderLayout.NORTH);
            jContentPane.add(jCaminos, BorderLayout.EAST);
            
        }
        return jContentPane;
    }
    
    

    /**
     * This method initializes jGraficas
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJGraficas() {
        if (jGraficas == null) {
            jGraficas = new JPanel();
            jGraficas.setPreferredSize(new Dimension(1000, 400));
            jR = new JPanel(){
                private static final long serialVersionUID = -1893015740780641565L;
                public void paintComponent(Graphics grafico){
                    super.paintComponent(grafico);
                    grafico.setFont(fNormal);
                    for (int i = 0; i < elementos.size(); i++) {
                        dibujarElemento(grafico, elementos.elementAt(i));
                    }
                    for (int i = 0; i < R.size(); i++) {
                        dibujarRelacion(grafico, R.elementAt(i), Color.GRAY);
                    }

                    if (clickedFlechaR) {
                        grafico.setColor(Color.RED);
                        drawArrow(grafico, elementoSeleccionado.getX() + tArco, elementoSeleccionado.getY() + tArco, flecha.x + tArco, flecha.y + tArco, 3L, 2);
                    }
                }
            };
            jR.setBackground(Color.BLACK);
            jR.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent e) {
                    jR_mouseReleased(e);
                }
                public void mousePressed(java.awt.event.MouseEvent e) {
                    jR_mousePressed(e);
                }
            });
            jR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent e) {
                    jR_mouseDragged(e);
                }
            });
            /*jS = new JPanel() {
                private static final long serialVersionUID = 2297839924871376233L;
                public void paintComponent(Graphics grafico){
                    super.paintComponent(grafico);
                    grafico.setFont(fNormal);
                    for (int i = 0; i < elementos.size(); i++) {
                        dibujarElemento(grafico, elementos.elementAt(i));
                    }
                    for (int i = 0; i < S.size(); i++) {
                        dibujarRelacion(grafico, S.elementAt(i), Color.GRAY);
                    }

                    if (clickedFlechaS) {
                        grafico.setColor(Color.RED);
                        drawArrow(grafico, elementoSeleccionado.getX() + tArco, elementoSeleccionado.getY() + tArco, flecha.x + tArco, flecha.y + tArco, 3L, 2);
                    }
                }
            };
            jS.setBackground(Color. BLACK);
            jS.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent e) {
                    jS_mouseReleased(e);
                }
                public void mousePressed(java.awt.event.MouseEvent e) {
                    jS_mousePressed(e);
                }
            });
            jS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent e) {
                    jS_mouseDragged(e);
                }
            });
*/
            GridLayout borderLayout = new GridLayout(1, 2);
            borderLayout.setHgap(5);

            jGraficas.setLayout(borderLayout);
            jGraficas.add(jR);
            /*jGraficas.add(jS);*/

        }
        return jGraficas;
    }

    /**
     * This method initializes jBotones
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJBotones() {
        if (jBotones == null) {
            jBotones = new JPanel();
            jBotones.setPreferredSize(new Dimension(90,45));
            

            GridLayout gridBotones = new GridLayout(1, 4);
            gridBotones.preferredLayoutSize(jBotones);
            jBotones.setLayout(gridBotones);
            btnGenerar = new JButton("Generar");
            btnGenerar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    /*btnInterseccion_click(e);*/
                }
            });
            /*
            btnUnion = new JButton("Unión");
            btnUnion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    /*btnUnion_click(e);*/
               /* }/*
            });/*
            btnDifsimetrica = new JButton("Diferencia Simétrica");
            btnDifsimetrica.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    /* btnDifsimetrica_click(e);*/
               /* }
            });
            */
            btnLimpiar = new JButton ("Limpiar");
            btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    btnLimpiar_click(e);
                }
            });

            
            //jBotones.add(btnUnion);
            jBotones.add(btnGenerar);  
            //jBotones.add(btnDifsimetrica);
            jBotones.add(btnLimpiar);

        }
        return jBotones;
    }

    /**
     * This method initializes jBotones
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJResultados() {
        if (jResultados == null) {
            jResultados = new JPanel();
            jResultados.setPreferredSize(new Dimension(1000, 110));

            GridLayout gridListas = new GridLayout(1, 4);
            gridListas.setHgap(5);
            gridListas.setVgap(5);

            jResultados.setLayout(gridListas);
/*
            interseccion = new DefaultListModel();
            union = new DefaultListModel();
            diferenciaSimetrica = new DefaultListModel();
            
            
            lstInterseccion = new JList(interseccion);
            lstUnion = new JList(union);
            lstDifsimetric = new JList(diferenciaSimetrica);
            
            jspInterseccion = new JScrollPane(lstInterseccion);
            jspUnion = new JScrollPane(lstUnion);
            jspDifsimetrica = new JScrollPane(lstDifsimetric);
            
            
            jResultados.add(jspUnion);
            jResultados.add(jspInterseccion);
            jResultados.add(jspDifsimetrica);*/
        }
        return jResultados;
    }

    ////////////////////////////////////////////////
    // Dibujo de elementos, relaciones, y listeners
    protected void jR_mousePressed(MouseEvent e) {
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
                clickedFlechaR = true;
            }
        }
    }

    protected void jR_mouseDragged(MouseEvent e) {
        if (clicked) {
            elementoSeleccionado.setX(e.getX() - tArco);
            elementoSeleccionado.setY(e.getY() - tArco);
            this.repaint();
        } else if (clickedFlechaR) {
            flecha.x = e.getX() - tArco;
            flecha.y = e.getY() - tArco;
            this.repaint();
        }
    }

    protected void jR_mouseReleased(MouseEvent e) {
        if (clickedFlechaR && elementoEncima(e.getX(), e.getY())) {
            Relacion nuevaRelacion = new Relacion();
            nuevaRelacion.setElemento1(elementoAnterior);
            nuevaRelacion.setElemento2(elementoSeleccionado);
            R.add(nuevaRelacion);
        }
        flecha.x = -1;
        flecha.y = -1;
        clicked = false;
        clickedFlechaR = false;
        this.repaint();
    }

    protected void jS_mousePressed(MouseEvent e) {
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
            /*S.add(nuevaRelacion);*/
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
        Graphics2D g2d = (Graphics2D)grafico;
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

    private void dibujarRelacion(Graphics grafico, Relacion relacion, Color color){
        dibujarRelacion(grafico, relacion, color, 1f);
    }

    private void dibujarRelacion(Graphics grafico, Relacion relacion, Color color, float ancho){
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
        double angulo = Math.asin(Math.abs(y2 - y1) / dist );

        int Cop = (int)(Math.sin(angulo) * (tArco + 1));
        int Cad = (int)(Math.cos(angulo) * (tArco + 1));

        if (x2 > x1) {
            if (y2 > y1) {
                drawArrow(grafico, x1 + Cad + tArco, y1 + Cop + tArco, x2 - Cad + tArco, y2 - Cop + tArco, 3L, ancho);
            } else if (y2 == y1){
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
                grafico.drawOval(x1 - Cad - tElemento - 15, y1 - Cop - tArco- 4, tElemento + 20, tElemento + 5);
            } else {
                drawArrow(grafico, x1 + tArco, y1 + Cop + tArco, x2 + tArco, y2 - Cop + tArco, 3L, ancho);
            }
        }
    }

    private static void drawArrow(Graphics g, int xCenter, int yCenter, int x, int y, float stroke, float ancho) {
        Graphics2D g2d = (Graphics2D)g;
        double aDir = Math.atan2(xCenter-x, yCenter-y);
        g2d.setStroke(new BasicStroke(ancho));
        g2d.drawLine(x, y, xCenter, yCenter);
        g2d.setStroke(new BasicStroke(1f));
        Polygon tmpPoly = new Polygon();
        int i1 = 6 + (int) (stroke*1.5);
        int i2 = 3 + (int) stroke;
        tmpPoly.addPoint(x, y);
        tmpPoly.addPoint(x + xCor(i1, aDir + .5), y + yCor(i1, aDir + .5));
        tmpPoly.addPoint(x + xCor(i2, aDir), y + yCor(i2, aDir));
        tmpPoly.addPoint(x + xCor(i1, aDir - .5), y + yCor(i1, aDir - .5));
        tmpPoly.addPoint(x, y);
        g2d.drawPolygon(tmpPoly);
        g2d.fillPolygon(tmpPoly);
    }
    private static int yCor(int len, double dir) {return (int)(len * Math.cos(dir));}
    private static int xCor(int len, double dir) {return (int)(len * Math.sin(dir));}

    private boolean elementoEncima(int x, int y) {
        int xElemento, yElemento;
        for (int i = 0; i < elementos.size(); i++) {
            xElemento = elementos.elementAt(i).getX();
            yElemento = elementos.elementAt(i).getY();
            if (distancia(x, xElemento, y, yElemento) < tElemento + 5) {
                elementoAnterior = elementoSeleccionado;
                elementoSeleccionado = elementos.elementAt(i);
                return true;
            }
        }
        return false;
    }

    private double distancia(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
     * Instrucciones de uso
     * 1. Creación de un elemento: clic izquierdo únicamente sobre el panel izquierdo
     *    Cada elemento creado se copia en el panel derecho
     *    clic izquierdo sostenido sobre un elemento hace que se desplace de lugar
     * 2. Creación de una relación: clic derecho sostenido desde un elemento a otro
     *    Funciona en ambos paneles
     * 3. Cada elemento es almacenado en el vector "elementos"
     *    Es de tipo "Elemento", ej: Elemento elemento1 = new Elemento();
     * 4. Las relaciones del panel izquierdo se almacenan en el vector "R"
     *    Es de tipo "Relacion", ej: Relacion relacionTemporal = new Relacion();
     * 5. Las relaciones del panel derecho se almacenan en el vector "S"
     *    Es de tipo "Relacion", ej: Relacion relacionTemporal = new Relacion();
     *
     *    Formas de recorrer el vector:
     * 1.
     *    for (int i = 0; i < elementos.size(); i++) {
     *        System.out.println(elementos.elementAt(i));
     *    }
     * 2.
     *    for (Elemento elemento : elementos) {
     *        System.out.println(elemento);
     *    }
     *
     *    Listas
     * 1. Los nombres para las listas son:union, interseccion
     *    diferenciaSimetrica
     * 2. Para agregar elementos a la lista se usa el método addElement
     *    ej: union.addElement("elemento1");
     * 3. Para borrar los elementos se usa el método clear
     *    ej: union.clear();
     *
     *    Comparación
     * 1. Para comparar objetos de tipo Relacion se usa el método equals()
     *    ej: relacion1.equals(relacion2), retorna verdadero si los nombres de sus elementos son iguales y tienen el mismo sentido
     *                                     retorna falso si los nombres de sus elementos son diferentes o están en diferente sentido
     * 2. Para comparar objetos de tipo Elemento se usa el método equals()
     *    ej: elemento1.equals(elemento2), retorna verdadero si sus nombres son iguales
     *                                     retorna falso si sus nombres son diferentes
     */
/*
    protected void btnUnion_click(ActionEvent e) {
        System.out.println("Botón producto");
        universal.clear();
        union.clear();
        for (int i = 0; i < elementos.size(); i++) {
            for (int j=0 ; j < elementos.size();j++){
                Relacion temp = new Relacion();
                temp.setElemento1(elementos.elementAt(i));
                temp.setElemento2(elementos.elementAt(j));
                universal.addElement(temp);
                union.addElement(temp);
            }
        }
    }
    */
/*
    protected void btnUnion_click(ActionEvent e) {
        System.out.println("Botón unión");
        universal.clear();
        union.clear();
        /*
        for(int i=0;i<R.size();i++){
            for (int j=0;j<S.size();j++){
            
            
            Relacion hola=new Relacion();
            hola.setElemento1(R.get(i).getElemento1());
            hola.setElemento2(R.get(i).getElemento2());
            universal.add(hola);
            union.addElement(hola);
            if(R.get(i).equals (S.get(j))){
                
                i++;
                
            }    
            
            }
        }
        */
  /*     
        for(int i=0;i<R.size();i++){
            Relacion hola=new Relacion();
            hola.setElemento1(R.get(i).getElemento1());
            hola.setElemento2(R.get(i).getElemento2());
            universal.add(hola);
            union.addElement(hola);
        }
        for(int i=0;i<S.size();i++){
            Relacion hola=new Relacion();
            hola.setElemento1(S.get(i).getElemento1());
            hola.setElemento2(S.get(i).getElemento2());
            universal.add(hola);
            union.addElement(hola);
            
        }
        
    }        
     
*/
    protected void btnGenerar_click(ActionEvent e) {
        System.out.println("Boton Generar");
        universal.clear();
        
          
    }    
            
            
            
            
            
        
    

  /*  

    protected void btnDifsimetrica_click(ActionEvent e) {
        System.out.println("Boton Diferencia Simetrica");
        universal.clear();
        diferenciaSimetrica.clear();
        for(int i=0;i<elementos.size();i++){    
        
                if(R.get(i).notEquals(S.get(i))){
                    Relacion hola1 = new Relacion();
                    hola1.setElemento1(R.get(i).getElemento1());
                    hola1.setElemento2(R.get(i).getElemento2());
                    universal.addElement(hola1);
                    diferenciaSimetrica.addElement(hola1); 
                    
            }
        
        }
        for(int i=0;i<elementos.size();i++){      
  
                if(S.get(i).notEquals(R.get(i))){
                    Relacion hola = new Relacion();
                    hola.setElemento1(S.get(i).getElemento1());
                    hola.setElemento2(S.get(i).getElemento2());
                    universal.addElement(hola);
                    diferenciaSimetrica.addElement(hola); 
                    
            }
                
                    
                    
        }
        
        /*
        for(int i=0;i<R.size();i++){
            for (int j=0;j<S.size();j++){
            if(R.get(i).notEquals (S.get(j))){
                Relacion hola=new Relacion();
                hola.setElemento1(R.get(i).getElemento1());
                hola.setElemento2(R.get(i).getElemento2());
                i++;
        universal.addElement(hola);
        diferenciaSimetrica.addElement(hola);
    }
            }
        }
        for(int i=0;i<S.size();i++){
            for (int j=0;j<R.size();j++){
            if(S.get(i).notEquals (R.get(j))){
                Relacion hola=new Relacion();
                hola.setElemento1(S.get(i).getElemento1());
                hola.setElemento2(S.get(i).getElemento2());
                i++;
        universal.addElement(hola);
        diferenciaSimetrica.addElement(hola);
    }
            }
        }
    */
       /* if(diferenciaSimetrica.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No es Simetrica");
        }
    }
    

    
*/
    protected void btnLimpiar_click(ActionEvent e) {
        R.clear();
   /*     S.clear();*/
        universal.clear();
        elementos.clear();
/*        union.clear();
        interseccion.clear();
        diferenciaSimetrica.clear();
  */      this.repaint();
        JOptionPane.showMessageDialog(null, "Elementos eliminados con Exito");
    }


}  //  @jve:decl-index=0:visual-constraint="200,24"
