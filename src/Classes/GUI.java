/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import GUI.JanelaPrincipal;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Erivan
 */
public class GUI {

    private static Color corAntiga;

    public static void criar(JInternalFrame obj) {
        JanelaPrincipal.getDtDesktop().add(obj);
        obj.setVisible(true);
    }

    public static void fechar(JInternalFrame obj) {
        if (JOptionPane.showInternalConfirmDialog(obj, "Você tem certeza de que deseja fechar a janela?") == JOptionPane.YES_OPTION) {
            obj.dispose();
        }
    }

    public static void ganhaFoco(JTextField campo) {
        corAntiga = campo.getBackground();
        campo.setBackground(Color.YELLOW);
        campo.setSelectionStart(0);
        campo.setSelectionEnd(campo.getText().length());
    }

    public static void perdeFoco(JTextField campo) {
        campo.setBackground(corAntiga);
        campo.setSelectionStart(0);
        campo.setSelectionEnd(0);
    }
}
