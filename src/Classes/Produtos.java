/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author Erivan
 */
public class Produtos {

    private static ArrayList<Integer> codigo = new ArrayList<>();
    private static ArrayList<String> descricao = new ArrayList<>();
    private static ArrayList<Integer> unidades = new ArrayList<>();
    private static ArrayList<Float> preco = new ArrayList<>();

    public static ArrayList<Integer> getCodigo() {
        return codigo;
    }

    public static ArrayList<String> getDescricao() {
        return descricao;
    }

    public static ArrayList<Integer> getUnidades() {
        return unidades;
    }

    public static ArrayList<Float> getPreco() {
        return preco;
    }

    public static void adicionarProduto(int codigo, String descricao, int unidades, float preco) {
        getCodigo().add(codigo);
        getDescricao().add(descricao);
        getUnidades().add(unidades);
        getPreco().add(preco);
    }

    public static void alterarProduto(int codigo, String descricao, int unidades, float preco) {
        int linha = getCodigo().indexOf(codigo);
        getDescricao().add(linha, descricao);
        getUnidades().add(linha, unidades);
        getPreco().add(linha, preco);
    }

    public static void deletarProduto(int linha) {
        Produtos.getCodigo().remove(linha);
        Produtos.getDescricao().remove(linha);
        Produtos.getUnidades().remove(linha);
        Produtos.getPreco().remove(linha);
    }
}
