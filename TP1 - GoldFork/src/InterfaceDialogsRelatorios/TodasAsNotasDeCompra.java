/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDialogsRelatorios;

import BancoDeDados.BancoDeDados;
import ClasseProdutos.ProdutoAtomico;
import ClassesLojas.Loja;
import ClassesNotas.NotaDeCompra;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eymar Lima
 */
public class TodasAsNotasDeCompra extends javax.swing.JDialog {
    private ArrayList<Loja> lojas;
    private ArrayList<NotaDeCompra> notas;
    /**
     * Creates new form TodasAsNotasDeCompra
     */
    public TodasAsNotasDeCompra(java.awt.Frame parent, boolean modal, ArrayList<Loja> lojas) {
        super(parent, modal);
        initComponents();
        this.lojas=lojas;
        
        
        this.preencherTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        notaDeCompraTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itensNotaTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notas de Compra");

        notaDeCompraTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notaDeCompraTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número da Nota", "Data", "Loja", "Fornecedor", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(notaDeCompraTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Notas De Compra:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Itens da Nota:");

        itensNotaTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        itensNotaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto Atômico", "Unidade de Medida", "Quantidade", "Valor Unitário", "Valor Total Item"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(itensNotaTable);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visible-opened-eye-interface-option (1).png"))); // NOI18N
        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int linha = notaDeCompraTable.getSelectedRow();
        if(linha == -1) return;
        
        ArrayList<ProdutoAtomico> itensNota = BancoDeDados.itensDeUmaNotaCompra(notas.get(linha).getId_nota());
        DefaultTableModel dtm = (DefaultTableModel) itensNotaTable.getModel();
        dtm.setRowCount(0);
        String []line = new String[5];
        
        if(!itensNota.isEmpty()){
            for(ProdutoAtomico atual: itensNota){
                line[0] = atual.getNome();
                line[1] = atual.getUnidade();
                line[2] = String.format("%.2f", atual.getQuantidade());
                line[3] = String.format("%.2f", atual.getValorUnitario());
                line[4] = String.format("%.2f", atual.getValorUnitario()*atual.getQuantidade());
                dtm.addRow(line);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void preencherTabela(){
        DefaultTableModel dtm = (DefaultTableModel) notaDeCompraTable.getModel();
        String []linha = new String[5];
        
        notas = new ArrayList<NotaDeCompra>();
        ArrayList<NotaDeCompra> temporario = new ArrayList<NotaDeCompra>();
        for(Loja lojaAtual:lojas){
            temporario = BancoDeDados.notasDeCompra(lojaAtual.getId_lanchonete());
            if(!temporario.isEmpty()){
                for(NotaDeCompra atual:temporario){
                    linha[0] = String.valueOf(atual.getId_nota());
                    linha[1] = atual.getData();
                    linha[2] = lojaAtual.getNome();
                    linha[3] = BancoDeDados.nomeDeUmFornecedor(atual.getCod_fornecedor());
                    linha[4] = String.valueOf(atual.getValor_total());
                    notas.add(atual);
                    dtm.addRow(linha);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable itensNotaTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable notaDeCompraTable;
    // End of variables declaration//GEN-END:variables
}
