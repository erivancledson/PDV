/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.GUI;
import Classes.Produtos;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erivan
 */
public class VenderProduto extends javax.swing.JInternalFrame {

    /**
     * Creates new form VenderProduto
     */
    public VenderProduto() {
        initComponents();
    }

    public javax.swing.JTextField getTfCodigo() {
        return tfCodigo;
    }

    public static void adicionar() {
        String cod = tfCodigo.getText().trim();

        if (!cod.isEmpty() && !tfQuantidade.getText().trim().isEmpty()) {
            int idArray = Produtos.getCodigo().indexOf(Integer.parseInt(tfCodigo.getText().trim()));
            if (idArray != -1) {
                DefaultTableModel modelo = (DefaultTableModel) tbItens.getModel();
                Object[] linha = new Object[5];

                linha[0] = Produtos.getCodigo().get(idArray);
                linha[1] = Produtos.getDescricao().get(idArray);
                linha[2] = tfQuantidade.getText();
                linha[3] = Produtos.getPreco().get(idArray);
                linha[4] = Integer.parseInt(linha[2].toString()) * Float.parseFloat(linha[3].toString().replaceAll(",", "."));

                Produtos.getUnidades().add(idArray, (Produtos.getUnidades().get(idArray) - Integer.parseInt(linha[2].toString())));

                modelo.addRow(linha);

                tbItens.setModel(modelo);
            }
        }
        calcularTotal();
    }

    public static void remover() {
        if (tbItens.getSelectedRowCount() == 1) {
            if (JOptionPane.showInternalConfirmDialog(JanelaPrincipal.getDtDesktop(), "Deseja mesmo realizar essa operação?") == JOptionPane.YES_OPTION) {
                int linhaSelecionada = tbItens.getSelectedRow();
                int idArray = Produtos.getCodigo().indexOf(Integer.parseInt(tbItens.getValueAt(linhaSelecionada, 0).toString()));

                DefaultTableModel modelo = (DefaultTableModel) tbItens.getModel();

                if (idArray != -1) {
                    Produtos.getUnidades().add(idArray, (Produtos.getUnidades().get(idArray) + Integer.parseInt(tbItens.getValueAt(linhaSelecionada, 2).toString())));
                }
                modelo.removeRow(linhaSelecionada);
                tbItens.setModel(modelo);
            }
        } else {
            JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Você precisa selecionar um produto.");
        }
        calcularTotal();
    }

    public static void removerTudo() {
        if (tbItens.getRowCount() > 0) {
            if (JOptionPane.showInternalConfirmDialog(JanelaPrincipal.getDtDesktop(), "Deseja mesmo realizar essa operação?") == JOptionPane.YES_OPTION) {
                DefaultTableModel modelo = (DefaultTableModel) tbItens.getModel();
                for (int i = 0; i < tbItens.getRowCount(); i++) {
                    int idArray = Produtos.getCodigo().indexOf(Integer.parseInt(tbItens.getValueAt(i, 0).toString()));

                    if (idArray != -1) {
                        Produtos.getUnidades().add(idArray, (Produtos.getUnidades().get(idArray) + Integer.parseInt(tbItens.getValueAt(i, 2).toString())));
                    }
                }
                modelo.setRowCount(0);
                tbItens.setModel(modelo);
                calcularTotal();
            }
        }
    }

    public static void calcularTotal() {
        float total = 0;

        for (int i = 0; i < tbItens.getRowCount(); i++) {
            total = total + Float.parseFloat(tbItens.getValueAt(i, 4).toString());
        }

        lbTotal.setText("Total: R$ " + Float.toString(total).replaceAll(",", "."));
    }

    public static void concluirVenda() {
        if (tbItens.getRowCount() > 0) {
            float total = Float.parseFloat(lbTotal.getText().replace("Total: R$ ", "").replace(",", "."));
            float valorRecebido = Float.parseFloat(JOptionPane.showInternalInputDialog(JanelaPrincipal.getDtDesktop(), "Valor recebido:").replace(",", "."));

            float troco = (total - valorRecebido) * -1;

            if (troco > 0) {
                JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Troco: R$ " + troco);
            } else if (total == valorRecebido) {
                JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Troco: R$ 0,00");
            } else {
                JOptionPane.showInternalMessageDialog(JanelaPrincipal.getDtDesktop(), "Valor devido: R$ " + troco * -1);
            }

            DefaultTableModel modelo = (DefaultTableModel) tbItens.getModel();
            modelo.setRowCount(0);
            tbItens.setModel(modelo);

            tfCodigo.setText("");
            tfQuantidade.setText("1");
            lbTotal.setText("Total: R$ 0,00");
            tfCodigo.requestFocus();
        }
    }

    public static boolean fechar() {
        boolean res = false;

        if (tbItens.getRowCount() > 0) {
            if (JOptionPane.showInternalConfirmDialog(JanelaPrincipal.getDtDesktop(), "Deseja mesmo realizar essa operação?") == JOptionPane.YES_OPTION) {
                for (int i = 0; i < tbItens.getRowCount(); i++) {
                    int idArray = Produtos.getCodigo().indexOf(Integer.parseInt(tbItens.getValueAt(i, 0).toString()));

                    if (idArray != -1) {
                        Produtos.getUnidades().add(idArray, (Produtos.getUnidades().get(idArray) + Integer.parseInt(tbItens.getValueAt(i, 2).toString())));
                    }
                }
                res = true;
            }
        } else {
            res = true;
        }

        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnMenu = new javax.swing.JPopupMenu();
        mniDeletar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItens = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        lbTotal = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btConcluir = new javax.swing.JButton();

        mniDeletar.setText("Deletar");
        mniDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDeletarActionPerformed(evt);
            }
        });
        pmnMenu.add(mniDeletar);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Vender Produtos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        tbItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Unidades", "Preço (Unidade)", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItensMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbItens);
        if (tbItens.getColumnModel().getColumnCount() > 0) {
            tbItens.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbItens.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbItens.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbItens.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setText("Código:");

        tfCodigo.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setText("Quantidade:");

        tfQuantidade.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        tfQuantidade.setText("1");
        tfQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfQuantidadeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfQuantidadeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCodigo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfQuantidade))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTotal.setFont(new java.awt.Font("Ubuntu", 0, 30)); // NOI18N
        lbTotal.setText("Total: R$ 0,00");

        btAdicionar.setText("Adicionar Item");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btRemover.setText("Remover Item");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar Venda");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btConcluir.setText("Concluir Venda");
        btConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConcluirActionPerformed(evt);
            }
        });
        btConcluir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btConcluirFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btConcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTotal)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusGained
        GUI.ganhaFoco(tfCodigo);
    }//GEN-LAST:event_tfCodigoFocusGained

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        GUI.perdeFoco(tfCodigo);
    }//GEN-LAST:event_tfCodigoFocusLost

    private void tfQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfQuantidadeFocusGained
        GUI.ganhaFoco(tfQuantidade);
    }//GEN-LAST:event_tfQuantidadeFocusGained

    private void tfQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfQuantidadeFocusLost
        GUI.perdeFoco(tfQuantidade);
    }//GEN-LAST:event_tfQuantidadeFocusLost

    private void btConcluirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btConcluirFocusLost
        tfCodigo.requestFocus();
    }//GEN-LAST:event_btConcluirFocusLost

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar();
        tfCodigo.setText("");
        tfQuantidade.setText("1");
        tfCodigo.requestFocus();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        remover();
    }//GEN-LAST:event_btRemoverActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        removerTudo();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConcluirActionPerformed
        concluirVenda();
    }//GEN-LAST:event_btConcluirActionPerformed

    private void tbItensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItensMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            pmnMenu.show(tbItens, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbItensMouseClicked

    private void mniDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDeletarActionPerformed
        remover();
    }//GEN-LAST:event_mniDeletarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (fechar()) {
            dispose();
        }
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConcluir;
    private javax.swing.JButton btRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lbTotal;
    private javax.swing.JMenuItem mniDeletar;
    private javax.swing.JPopupMenu pmnMenu;
    private static javax.swing.JTable tbItens;
    private static javax.swing.JTextField tfCodigo;
    private static javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables

}
