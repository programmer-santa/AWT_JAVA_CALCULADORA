package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog; 
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame; 

import models.Manager;
import views.Window;

public class Controller implements ActionListener {

    private Window window;
    private Manager manager;

    
    public Controller() {
        manager = new Manager();
        window = new Window(this); 
    }

    
    public static void main(String[] args) {
        new Controller();
    }

    
    private void showAWTMessage(String message, String title, Frame owner) {
        Dialog dialog = new Dialog(owner, title, true);
        dialog.setLayout(new java.awt.FlowLayout()); 
        dialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.add(okButton);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        String command = e.getActionCommand(); 

        if ("CALCULAR".equals(command)) {
            String num1Str = window.getNumero1();
            String num2Str = window.getNumero2();
            String operacion = window.getOperacionSeleccionada(); 

            try {
                if (num1Str.trim().isEmpty() || num2Str.trim().isEmpty()) {
                    showAWTMessage("Por favor, ingrese valores en ambos campos numéricos.", 
                                  "Error de Entrada", window.getFrame()); 
                    return;
                }
                
                String resultado = manager.realizarOperacion(num1Str, num2Str, operacion);
                window.setResultado(resultado); 

            } catch (NumberFormatException ex) {
                showAWTMessage("Error: Los números ingresados no son válidos. Use solo dígitos y un punto para decimales.", 
                              "Error de Formato", window.getFrame());
                window.setResultado("Error");
            } catch (ArithmeticException ex) {
                showAWTMessage("Error de cálculo: " + ex.getMessage(), 
                              "Error Aritmético", window.getFrame());
                window.setResultado("Error");
            } catch (Exception ex) { 
                showAWTMessage("Ocurrió un error inesperado: " + ex.getMessage(), 
                              "Error General", window.getFrame());
                window.setResultado("Error");
            }
        }
    }
}