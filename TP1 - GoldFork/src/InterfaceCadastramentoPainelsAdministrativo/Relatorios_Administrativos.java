/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceCadastramentoPainelsAdministrativo;

import BancoDeDados.BancoDeDados;
import ClassesLojas.Loja;
import InterfaceDialogsRelatorios.EstoqueLanchonetes;
import InterfaceDialogsRelatorios.RelatorioFuncionarios;
import InterfaceDialogsRelatorios.TodasAsLanchonetes;
import InterfaceDialogsRelatorios.TodasAsNotasDeCompra;
import InterfaceDialogsRelatorios.TodasAsNotasDeVenda;
import InterfaceDialogsRelatorios.TodosOsFornecedores;
import InterfaceDialogsRelatorios.TodosOsProdutos;
import InterfaceDialogsRelatorios.TodosOsProdutosAtomicos;
import InterfaceMain.Main;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eymar Lima
 */
public class Relatorios_Administrativos extends javax.swing.JPanel {
    private Main pai;
    private ArrayList<Loja> todasLojas;
    private ArrayList<PanelLojaAuxiliarParaListagem> todasLojasPanel;
    /**
     * Creates new form Relatorios
     */
    public Relatorios_Administrativos(Main pai){
        initComponents();
        this.pai = pai;
        
        todasLojasPanel = new ArrayList<PanelLojaAuxiliarParaListagem>();
        
        this.todasLojas = BancoDeDados.recuperarNomeEIDLojas(pai.getIdUsuarioLogado());
        PanelLojas.setLayout(new GridLayout(0,1));
        PanelLojas.setPreferredSize(new Dimension(400, 298));
        
        
        
        for (Loja loja : todasLojas) {
            PanelLojaAuxiliarParaListagem p = new PanelLojaAuxiliarParaListagem(loja);
            PanelLojas.add(p);
            todasLojasPanel.add(p);
        }
        PanelLojas.revalidate();
        PanelLojas.repaint();
        
        travarlojas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelLojas = new javax.swing.JPanel();
        relatoriosBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        selecionarTodos = new javax.swing.JRadioButton();

        setOpaque(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        PanelLojas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout PanelLojasLayout = new javax.swing.GroupLayout(PanelLojas);
        PanelLojas.setLayout(PanelLojasLayout);
        PanelLojasLayout.setHorizontalGroup(
            PanelLojasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelLojasLayout.setVerticalGroup(
            PanelLojasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        relatoriosBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        relatoriosBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Produtos", "Funcionários", "Produtos Atômicos", "Lanchonetes", "Fornecedores", "Estoque", "Nota de Compra", "Nota de Venda" }));
        relatoriosBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de Relatório:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Lojas Afetadas:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/report.png"))); // NOI18N
        jButton1.setText("Gerar Relatório");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        selecionarTodos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selecionarTodos.setText("Selecionar Todas as Lojas");
        selecionarTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionarTodosMouseClicked(evt);
            }
        });
        selecionarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relatoriosBox, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelLojas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(selecionarTodos)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relatoriosBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selecionarTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(PanelLojas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, relatoriosBox});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int linha = relatoriosBox.getSelectedIndex();
        
        switch(linha){
            case 0: {
                TodosOsProdutos d = new TodosOsProdutos(this.pai, true, this.pai);
                d.setLocationRelativeTo(this);
                d.setVisible(true);
                break;
            }
            case 1: {
                ArrayList<Loja> selecionadas = new ArrayList<Loja>();
                for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
                    if(atual.isBotaoSelecionado()){
                        selecionadas.add(atual.getLoja());
                    }
                }
                if(selecionadas.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione pelo menos uma loja!");
                    return;
                }
                
                RelatorioFuncionarios r = new RelatorioFuncionarios(this.pai, true, selecionadas, this.pai);
                r.setLocationRelativeTo(this);
                r.setVisible(true);
                break;
            }
            case 2: {
                TodosOsProdutosAtomicos t = new TodosOsProdutosAtomicos(this.pai, true, this.pai);
                t.setLocationRelativeTo(this);
                t.setVisible(true);
                break;
            }
            case 3:{
                TodasAsLanchonetes t = new TodasAsLanchonetes(this.pai, true, this.pai);
                t.setLocationRelativeTo(this);
                t.setVisible(true);
                pai.chamarRelatorio_Administrativo();
                break;
            }
            case 4:{
                ArrayList<Loja> selecionadas = new ArrayList<Loja>();
                for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
                    if(atual.isBotaoSelecionado()){
                        selecionadas.add(atual.getLoja());
                    }
                }
                if(selecionadas.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione pelo menos uma loja!");
                    return;
                }
                
                TodosOsFornecedores t = new TodosOsFornecedores(this.pai, true, selecionadas, this.pai);
                t.setLocationRelativeTo(this);
                t.setVisible(true);
                break;
            }
            case 5:{
                ArrayList<Loja> selecionadas = new ArrayList<Loja>();
                for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
                    if(atual.isBotaoSelecionado()){
                        selecionadas.add(atual.getLoja());
                    }
                }
                if(selecionadas.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione pelo menos uma loja!");
                    return;
                }
                
                EstoqueLanchonetes e = new EstoqueLanchonetes(this.pai, true, selecionadas, this.pai);
                e.setLocationRelativeTo(this);
                e.setVisible(true);
                break;
            }
            case 6:{
                ArrayList<Loja> selecionadas = new ArrayList<Loja>();
                for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
                    if(atual.isBotaoSelecionado()){
                        selecionadas.add(atual.getLoja());
                    }
                }
                if(selecionadas.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione pelo menos uma loja!");
                    return;
                }
                
                TodasAsNotasDeCompra t = new TodasAsNotasDeCompra(this.pai, true, selecionadas);
                t.setLocationRelativeTo(this);
                t.setVisible(true);
                break;
            }
            case 7:{
                ArrayList<Loja> selecionadas = new ArrayList<Loja>();
                for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
                    if(atual.isBotaoSelecionado()){
                        selecionadas.add(atual.getLoja());
                    }
                }
                if(selecionadas.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione pelo menos uma loja!");
                    return;
                }
                
                TodasAsNotasDeVenda t = new TodasAsNotasDeVenda(this.pai, true, selecionadas);
                t.setLocationRelativeTo(this);
                t.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void selecionarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarTodosActionPerformed
        this.verificaSelecionado();
    }//GEN-LAST:event_selecionarTodosActionPerformed

    private void selecionarTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionarTodosMouseClicked
        this.verificaSelecionado();
    }//GEN-LAST:event_selecionarTodosMouseClicked

    private void relatoriosBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosBoxActionPerformed
        int linha = relatoriosBox.getSelectedIndex();
        
        switch(linha){
            //todos os produtos, nao possui loja
            case 0: selecionarTodos(false); travarlojas(); break;
            case 1: destravarlojas(); break;
            case 2: selecionarTodos(false); travarlojas(); break;
            case 3: selecionarTodos(false); travarlojas(); break;
            case 4: destravarlojas(); break;
            case 5: destravarlojas(); break;
            case 6: destravarlojas(); break;
            case 7: destravarlojas(); break;    
        }
    }//GEN-LAST:event_relatoriosBoxActionPerformed
    
    public void travarlojas(){
        for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
            atual.travarselect(false);
        }
    }
    
    public void destravarlojas(){
        for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
            atual.travarselect(true);
        }
    }
    
    public void verificaSelecionado(){
        if(selecionarTodos.isSelected()){
            selecionarTodos(true);
        }
        else{
            selecionarTodos(false);
        }  
    }
    public void selecionarTodos(boolean b){
        for(PanelLojaAuxiliarParaListagem atual : todasLojasPanel){
            atual.selecionar(b);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLojas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox relatoriosBox;
    private javax.swing.JRadioButton selecionarTodos;
    // End of variables declaration//GEN-END:variables
}
