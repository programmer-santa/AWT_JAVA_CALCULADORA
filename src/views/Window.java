package views;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;  
import java.awt.GridLayout;
import java.awt.Label;   
import java.awt.Panel; 
import java.awt.TextField;
import java.awt.BorderLayout; 
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {
    private Frame ventana; 
    private TextField txtNumero1; 
    private TextField txtNumero2;
    private Choice comboOperacion; 
    private Label lblResultado;
    private Button btnCalcular;
    private Panel panelEntrada;
    private Panel panelResultado;
    private Panel panelBotones;
    
    private static final Color COLOR_FONDO_VENTANA = new Color(220, 190, 220); // Lila suave
    private static final Color COLOR_TEXTFIELDS_FONDO = new Color(240, 220, 240); // Lila claro
    private static final Color COLOR_LABEL_RESULTADO_FONDO = new Color(180, 140, 180); // Lila intermedio
    private static final Color COLOR_BOTON_CALCULAR = new Color(140, 90, 140); // Lila oscuro
    private static final Color COLOR_TEXTO_NUMEROS_SIMBOLOS = Color.BLACK;
    private static final Color COLOR_TEXTO_RESULTADO = Color.WHITE;

    public Window(ActionListener controller) {
        configurarVentana();
        configurarComponentes(controller);
        ventana.setVisible(true);
    }

    private void configurarVentana() {
        ventana = new Frame("Calculadora AWT");
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null); 
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        ventana.setBackground(COLOR_FONDO_VENTANA); 
        ventana.setLayout(new BorderLayout(10, 10)); 
    }

    private void configurarComponentes(ActionListener controller) {
       
        panelEntrada = new Panel(new GridLayout(3, 2, 5, 5));
        panelEntrada.setBackground(COLOR_FONDO_VENTANA); 

        Label labelNum1 = new Label("Número 1:");
        labelNum1.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelNum1);
        txtNumero1 = new TextField(10); 
        txtNumero1.setFont(new Font("Arial", Font.BOLD, 20));
        txtNumero1.setBackground(COLOR_TEXTFIELDS_FONDO);
        txtNumero1.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(txtNumero1);

        Label labelOperacion = new Label("Operación:"); 
        labelOperacion.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelOperacion);
        comboOperacion = new Choice(); 
        comboOperacion.add("+");
        comboOperacion.add("-");
        comboOperacion.add("x");
        comboOperacion.add("/");
        comboOperacion.setFont(new Font("Arial", Font.BOLD, 20));
        comboOperacion.setBackground(COLOR_TEXTFIELDS_FONDO);
        comboOperacion.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(comboOperacion);

        Label labelNum2 = new Label("Número 2:"); 
        labelNum2.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelNum2);
        txtNumero2 = new TextField(10); 
        txtNumero2.setFont(new Font("Arial", Font.BOLD, 20));
        txtNumero2.setBackground(COLOR_TEXTFIELDS_FONDO);
        txtNumero2.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(txtNumero2);

        ventana.add(panelEntrada, BorderLayout.NORTH);

        panelBotones = new Panel();
        panelBotones.setBackground(COLOR_FONDO_VENTANA); 
        btnCalcular = new Button("Calcular"); 
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 24));
        btnCalcular.setBackground(COLOR_BOTON_CALCULAR); 
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setActionCommand("CALCULAR");
        btnCalcular.addActionListener(controller);
        panelBotones.add(btnCalcular);
        ventana.add(panelBotones, BorderLayout.CENTER); 

        panelResultado = new Panel(new BorderLayout()); 
        panelResultado.setBackground(COLOR_LABEL_RESULTADO_FONDO);
        
        lblResultado = new Label("Resultado:", Label.CENTER); 
        lblResultado.setFont(new Font("Arial", Font.BOLD, 36));
        lblResultado.setForeground(COLOR_TEXTO_RESULTADO); 
        panelResultado.add(lblResultado, BorderLayout.CENTER);
        ventana.add(panelResultado, BorderLayout.SOUTH);
    }

    // Métodos para que el Controller obtenga los valores de los TextField
    public String getNumero1() {
        return txtNumero1.getText();
    }

    public String getNumero2() {
        return txtNumero2.getText();
    }

    // Método para que el Controller obtenga la operación seleccionada del Choice
    public String getOperacionSeleccionada() {
        return comboOperacion.getSelectedItem();
    }

    // Método para que el Controller establezca el resultado en el Label
    public void setResultado(String resultado) {
        lblResultado.setText("Resultado: " + resultado);
    }
    
    // Método para que el Controller pueda obtener el Frame (ventana principal)
    // Esto es necesario para los diálogos de error de AWT (showAWTMessage)
    public Frame getFrame() {
        return ventana;
    }
}