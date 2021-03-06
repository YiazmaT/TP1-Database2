/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceDialogsAlteracaoDeDados;

import BancoDeDados.BancoDeDados;
import ClasseProdutos.Produto;
import ClasseProdutos.ProdutoAtomico;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YiazmaT
 */
public class ModificarProduto extends javax.swing.JDialog {
    private ArrayList<ProdutoAtomico> composicao = new ArrayList<ProdutoAtomico>();
    private ArrayList<ProdutoAtomico> atomicosCadastrados;
    private Produto produto;
    /**
     * Creates new form ModificarProduto
     */
    public ModificarProduto(java.awt.Frame parent, boolean modal, Produto produto) {
        super(parent, modal);
        initComponents();
        this.produto = produto;
        
        atomicosCadastrados = BancoDeDados.recuperarProdutosAtomicos();
        //carregar produtos na tabela;
        DefaultTableModel dtm = (DefaultTableModel) tabelaAtomicos.getModel();
        for (ProdutoAtomico atomico : atomicosCadastrados) {
            String [] linha = new String[2];
            linha[0] = atomico.getNome();
            linha[1] = atomico.getUnidade();
            dtm.addRow(linha);
        }
        
        //buscando a composição atual do produto
        this.composicao = BancoDeDados.recuperarProdutoAtomicoComIdProduto(this.produto.getId_produto());
        this.atualizarTabelaComposicao(this.composicao);
        
        //preenchendo nome e valor;
        nomeField.setText(this.produto.getNome());
        valorField.setText(String.format("%.2f",this.produto.getValor()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtomicos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        quantidadeField = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        composicaoTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        valorField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Produto");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shuffle (1).png"))); // NOI18N
        jButton3.setText("Alterar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabelaAtomicos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelaAtomicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Unidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaAtomicos);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/left-arrow.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/right-arrow.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        quantidadeField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.000"))));

        composicaoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Unidade", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(composicaoTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quantidade:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Produtos Atômicos Presentes no Produto:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Produtos Cadastrados no Estoque:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Nome do Produto:");

        nomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Valor:");

        valorField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shuffle (1).png"))); // NOI18N
        jLabel2.setText("Alterar Dados do Produto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valorField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(quantidadeField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(valorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(quantidadeField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel3, jLabel4, jLabel6, jLabel7, nomeField, quantidadeField, valorField});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //pegar e validar nome;
        String nome = nomeField.getText();
        if(nome.equals("")) {this.erro(3); return;}
        if(nome.length() > 200) {this.erro(4); return;}
        nome = nome.toLowerCase();
        if(!nome.matches("([0-9a-zA-Z ]|â|à|ã|ä|á|Á|ê|ë|é|É|î|ï|í|Í|ô|õ|ö|ó|Ó|û|ü|ú|Ú|'|ç|-|_|\\+){1,50}")){this.erro(1); return;}
        nome = nome.toUpperCase();
        if(!this.produto.getNome().equals(nome)){
            if(BancoDeDados.verificaIfProdutoComNomeIgual(nome) == true){this.erro(6); return;}
        }

        //pegar e validar valor;
        if(valorField.getText().equals("")) {this.erro(3); return;}
        float valor = Float.parseFloat(valorField.getText().replaceAll("\\,", "\\."));
        if(valor <= 0) {this.erro(7); return;}
        
        if(this.composicao.isEmpty()){
            this.erro(5);
            return;
        }
        
        String c="";
        for(ProdutoAtomico atual : this.composicao){
            c+="\n"+atual.getNome()+" - Quantidade: "+String.format("%.3f", atual.getQuantidade());
        }
        int confirm;
        confirm = JOptionPane.showConfirmDialog(null, "Deseja Realmente Alterar as Informações do Produto para: "
                +"\nNome: "+nome
                +"\nValor: R$"+String.format("%.2f",valor)
                +"\nComposicao:"+c
                , "Modificar Produto", JOptionPane.YES_NO_OPTION);
        if(confirm == 0){
            BancoDeDados.updateProduto(this.produto.getId_produto(), nome, valor, this.composicao);
        }
        else return;
        
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int linha = composicaoTable.getSelectedRow();
        if(linha<0) return;
        composicao.remove(linha);
        atualizarTabelaComposicao(composicao);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int linha = tabelaAtomicos.getSelectedRow();
        if(linha<0) return;
        
        if(quantidadeField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor Insira uma quantidade!!");
            return;
        }
        if(this.verificaSeJaEstaNaComposicao(this.atomicosCadastrados.get(linha).getIdProdutoAtomico())){
            JOptionPane.showMessageDialog(null, "Este produto atômico ja está presente na composição!!");
            return;
        }
        float quantidade = Float.parseFloat(quantidadeField.getText().replaceAll("\\,", "\\."));
        if(quantidade <= 0){
            JOptionPane.showMessageDialog(null, "Não são permitidas quantidades negativas!!");
            return;
        }
        
        //pega o produto do arraylist de atomicos que esta na ram e coloca no de composicao que tb esta na ram
        ProdutoAtomico atual = this.atomicosCadastrados.get(linha);
        atual.setQuantidade(quantidade);
        this.composicao.add(atual);

        this.atualizarTabelaComposicao(this.composicao);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeFieldActionPerformed

    public void erro(int e){
        switch(e){
            case 1: JOptionPane.showMessageDialog(null, "Nome Inválido, utilize apenas letras!!"); break;
            case 3: JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!!"); break;
            case 5: JOptionPane.showMessageDialog(null, "Por favor selecione pelo menos 1 Produto Atômico!!"); break;
            case 4: JOptionPane.showMessageDialog(null, "Nome pode ter no máximo 200 caracteres!!"); break;
            case 6: JOptionPane.showMessageDialog(null, "Já existe um Produto cadastrado com esse nome!!"); break;
            case 7: JOptionPane.showMessageDialog(null, "Produto não pode ter valor negativo!!"); break;
        }
    }
    
    private void atualizarTabelaComposicao(ArrayList<ProdutoAtomico> comp){
        DefaultTableModel dtm = (DefaultTableModel) composicaoTable.getModel();
        
        //limpar tabela
        dtm.setRowCount(0);
        
        //atualizar tabela
        for (ProdutoAtomico atomico : comp) {
            String [] linha = new String[3];
            linha[0] = atomico.getNome();
            linha[1] = atomico.getUnidade();
            linha[2] = String.valueOf(atomico.getQuantidade());
            dtm.addRow(linha);
        }
    }
    
    private boolean verificaSeJaEstaNaComposicao(int id_produtoAtomico){
        for(ProdutoAtomico atual : this.composicao){
            if(atual.getIdProdutoAtomico() == id_produtoAtomico) return true;
        }
        return false;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable composicaoTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nomeField;
    private javax.swing.JFormattedTextField quantidadeField;
    private javax.swing.JTable tabelaAtomicos;
    private javax.swing.JFormattedTextField valorField;
    // End of variables declaration//GEN-END:variables
}
