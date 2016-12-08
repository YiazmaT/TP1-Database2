/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceCadastramentoPainelsAdministrativo;

import BancoDeDados.BancoDeDados;
import static BancoDeDados.BancoDeDados.verificaIfFornecedorComNomeIgualUtilizandoIdDono;
import ClassesLojas.Loja;
import InterfaceMain.Main;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author YiazmaT
 */
public class NovoFornecedor_Administrativo extends javax.swing.JPanel {
    private Main pai;
    private ArrayList<Loja> todasLojas;
    private ArrayList<PanelAuxiliarCadastrarFornecedor> lojasPanel;
    /**
     * Creates new form NovoFornecedor
     */
    public NovoFornecedor_Administrativo(Main pai) {
        initComponents();
        this.pai=pai;
        
        lojasPanel = new ArrayList<PanelAuxiliarCadastrarFornecedor>();
        
        this.todasLojas = BancoDeDados.recuperarNomeEIDLojas(pai.getIdUsuarioLogado());
        lojasDentroPanel.setLayout(new GridLayout(0,1));
        
        for (Loja loja : todasLojas) {
            PanelAuxiliarCadastrarFornecedor p = new PanelAuxiliarCadastrarFornecedor(this, loja);
            lojasDentroPanel.add(p);
            lojasPanel.add(p);
        }
        lojasDentroPanel.revalidate();
        lojasDentroPanel.repaint();
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        telefoneField = new javax.swing.JFormattedTextField();
        cnpjField = new javax.swing.JFormattedTextField();
        todasAsLojas = new javax.swing.JScrollPane();
        lojasDentroPanel = new javax.swing.JPanel();

        setOpaque(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/people-trading (1).png"))); // NOI18N
        jLabel1.setText("Cadastrar Novo Fornecedor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nome:");

        nomeField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-square-button.png"))); // NOI18N
        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("CNPJ:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Loja Afetadas:");

        try {
            telefoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefoneFieldActionPerformed(evt);
            }
        });

        try {
            cnpjField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout lojasDentroPanelLayout = new javax.swing.GroupLayout(lojasDentroPanel);
        lojasDentroPanel.setLayout(lojasDentroPanelLayout);
        lojasDentroPanelLayout.setHorizontalGroup(
            lojasDentroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        lojasDentroPanelLayout.setVerticalGroup(
            lojasDentroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );

        todasAsLojas.setViewportView(lojasDentroPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(todasAsLojas, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomeField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(telefoneField))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cnpjField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel5, jLabel6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cnpjField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todasAsLojas, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(94, 94, 94))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cnpjField, jLabel3, jLabel5, jLabel6, nomeField, telefoneField});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void telefoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //pegar lojas afetadas
        ArrayList<Loja> lojasAfetadas = new ArrayList<Loja>();
        for(PanelAuxiliarCadastrarFornecedor atual : this.lojasPanel){
            if(atual.isBotaoSelecionado()){
                lojasAfetadas.add(atual.getLoja());
            }
        }
        if(lojasAfetadas.isEmpty()){this.erro(0); return;}
        
        //pegar e validar nome;
        String nome = nomeField.getText();
        if(nome.equals("")) {this.erro(3); return;}
        if(nome.length() > 200){this.erro(2); return;}
        if(!nome.matches("([0-9a-zA-Z ]|â|à|ã|ä|á|Á|ê|ë|é|É|î|ï|í|Í|ô|õ|ö|ó|Ó|û|ü|ú|Ú|'|ç){1,50}")){this.erro(1); return;}
        nome = nome.toUpperCase();
        if(BancoDeDados.verificaIfFornecedorComNomeIgualUtilizandoIdDono(nome, pai.getIdUsuarioLogado()) == true){this.erro(4); return;}
        
        //pegar e validar telefone;
        String telefone = telefoneField.getText();
        if(telefone.contains(" ")) {erro(5); return;}
        
        //pegar e validar cnpj;
        String cnpj = cnpjField.getText();
        if(cnpj.contains(" ")) {this.erro(6); return;}
        if(BancoDeDados.verificaIfFornecedorComCNPJIgualUtilizandoIdDono(cnpj, pai.getIdUsuarioLogado()) == true){this.erro(7); return;}
        
        BancoDeDados.cadastrarNovoFornecedor(nome, telefone, cnpj, lojasAfetadas);
        pai.chamarNovoFornecedor_Administrativo();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeFieldActionPerformed

    private void erro(int e){
        switch(e){
            case 0: JOptionPane.showMessageDialog(null, "Selecione pelo menos 1 loja a ser afetada!!"); break;
            case 1: JOptionPane.showMessageDialog(null, "Nome Inválido!! Utilize apenas letras ou números."); break;
            case 2: JOptionPane.showMessageDialog(null, "Nome Inválido!! Nome pode ter no máximo 200 caracteres."); break;
            case 3: JOptionPane.showMessageDialog(null, "Nome não pode estar vazio!!"); break;
            case 4: JOptionPane.showMessageDialog(null, "Já existe um fornecedor cadastrado com esse nome!!"); break;
            case 5: JOptionPane.showMessageDialog(null, "Telefone Inválido!!"); break;
            case 6: JOptionPane.showMessageDialog(null, "CNPJ Inválido!!"); break;
            case 7: JOptionPane.showMessageDialog(null, "Já existe um fornecedor cadastrado com esse cnpj!!"); break;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cnpjField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel lojasDentroPanel;
    private javax.swing.JTextField nomeField;
    private javax.swing.JFormattedTextField telefoneField;
    private javax.swing.JScrollPane todasAsLojas;
    // End of variables declaration//GEN-END:variables
}
