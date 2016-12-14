/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import GUI.JanelaPrincipal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Erivan
 */
public class Arquivos {

    private static FileWriter arquivoGravar;
    private static BufferedWriter gravador;
    private static FileReader arquivoLer;
    private static BufferedReader leitor;

    public static void gravarProdutos(String dados) {
        try {
            arquivoGravar = new FileWriter("Produtos");
            gravador = new BufferedWriter(arquivoGravar);

            gravador.write(dados);
            gravador.flush();
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Falha ao realizar a operação:\n\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void lerProdutos() {
        try {
            String linha;

            arquivoLer = new FileReader("Produtos");
            leitor = new BufferedReader(arquivoLer);

            while ((linha = leitor.readLine()) != null) {
                StringTokenizer array = new StringTokenizer(linha, ";");

                Produtos.getCodigo().add(Integer.parseInt(array.nextToken()));
                Produtos.getDescricao().add(array.nextToken());
                Produtos.getUnidades().add(Integer.parseInt(array.nextToken()));
                Produtos.getPreco().add(Float.parseFloat(array.nextToken()));
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Falha ao realizar a operação:\n\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Falha ao realizar a operação:\n\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
