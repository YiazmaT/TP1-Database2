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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        dataInicio = new javax.swing.JFormattedTextField();
        dataFim = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        todasAsNotas = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

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

        dataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        dataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Intervalo de Datas das Notas:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Inicio:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fim:");

        todasAsNotas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        todasAsNotas.setText("Todas as Notas");
        todasAsNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                todasAsNotasMouseClicked(evt);
            }
        });
        todasAsNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todasAsNotasActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/report.png"))); // NOI18N
        jButton2.setText("Exibir Notas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(todasAsNotas)
                                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todasAsNotas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5});

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

    private void todasAsNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todasAsNotasActionPerformed
        this.travarDatas();
    }//GEN-LAST:event_todasAsNotasActionPerformed

    private void todasAsNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todasAsNotasMouseClicked
        this.travarDatas();
    }//GEN-LAST:event_todasAsNotasMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(todasAsNotas.isSelected()){
            this.preencherTabelaTodasAsNotas();
        }
        else{
            if(dataFim.getText().contains(" ") || dataInicio.getText().contains(" ")){this.erro(2); return;}
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat saida = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date data1 = formato.parse(dataInicio.getText());
                Date data2 = formato.parse(dataFim.getText());
                
                if(data1.after(data2)){
                    this.erro(3);
                    return;
                }
                this.preecherTabelaComIntervalo(saida.format(data1), saida.format(data2));
            } catch (ParseException ex) {
                Logger.getLogger(TodasAsNotasDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public void preecherTabelaComIntervalo(String data1, String data2){
        //limpar tabela de itens
        DefaultTableModel limpar = (DefaultTableModel) itensNotaTable.getModel();
        limpar.setRowCount(0);
        
        //construir tabela de notas
        DefaultTableModel dtm = (DefaultTableModel) notaDeCompraTable.getModel();
        dtm.setRowCount(0);
        String []linha = new String[5];
        
        notas = new ArrayList<NotaDeCompra>();
        ArrayList<NotaDeCompra> temporario = new ArrayList<NotaDeCompra>();
        for(Loja lojaAtual:lojas){
            temporario = BancoDeDados.notasDeCompraEntreDatas(lojaAtual.getId_lanchonete(), data1, data2);
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
    
    public void preencherTabelaTodasAsNotas(){
        //limpar tabela de itens
        DefaultTableModel limpar = (DefaultTableModel) itensNotaTable.getModel();
        limpar.setRowCount(0);
        
        //construir tabela de notas
        DefaultTableModel dtm = (DefaultTableModel) notaDeCompraTable.getModel();
        dtm.setRowCount(0);
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

    public void travarDatas(){
        if(todasAsNotas.isSelected()){
            dataInicio.enable(false);
            dataFim.enable(false);
        }
        else{
            dataInicio.enable(true);
            dataFim.enable(true);
        }
    }
    
    public void erro(int e){
        switch(e){
            case 1: JOptionPane.showMessageDialog(null, "Por Favor Insira todas as Datas!"); break;
            case 2: JOptionPane.showMessageDialog(null, "Data Inválida!"); break;
            case 3: JOptionPane.showMessageDialog(null, "Data de Inicio não pode ser Maior que data de Fim!"); break;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dataFim;
    private javax.swing.JFormattedTextField dataInicio;
    private javax.swing.JTable itensNotaTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable notaDeCompraTable;
    private javax.swing.JRadioButton todasAsNotas;
    // End of variables declaration//GEN-END:variables
}
