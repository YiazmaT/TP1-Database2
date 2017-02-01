/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMain;

import InterfaceCadastramentoPainelsAdministrativo.NovaLanchonete_Administrativo;
import InterfaceCadastramentoPainelsGerencial.NovaNotaCompra_Gerencial;
import InterfaceCadastramentoPainelsAdministrativo.NovaNotaCompra_Administrativo;
import InterfaceCadastramentoPainelsAdministrativo.NovoFornecedor_Administrativo;
import InterfaceCadastramentoPainelsGerencial.NovoFuncionario_Gerencial;
import InterfaceCadastramentoPainelsAdministrativo.NovoFuncionario_Administrativo;
import InterfaceCadastramentoPainelsAdministrativo.NovoProduto_Administrativo;
import InterfaceCadastramentoPainelsAdministrativo.NovoProdutoAtomico_Administrativo;
import InterfaceCommonPainels.Desenvolvedores;
import InterfaceCommonPainels.Login;
import InterfaceCommonPainels.MenuInicial;
import InterfaceCadastramentoPainelsAdministrativo.Relatorios_Administrativos;
import InterfaceCadastramentoPainelsGerencial.NovoFornecedor_Gerencial;
import InterfaceDialogsAlteracaoDeDados.AlterarSenha;
import InterfaceVendas.Vendas;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.System.exit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Eymar Lima
 */
public class Main extends javax.swing.JFrame {
    private String nomeUsuarioLogado;
    private int idUsuarioLogado;
    private int permissaoUsuarioLogado; // 0==dono, 1==gerente, 2==caixa
    private int idUsuarioLoja; //id da loja que o usuario trabalha, caso seja dono será 0;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        //icon
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("src\\Images\\mainicon.png");
            setIconImage(icon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "ERRO", 0);
        }
        //
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        principal.setLayout(new GridLayout(1, 1));
        
        this.travarMenus();
        
        this.chamarLogin();
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
        principal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        inicio = new javax.swing.JMenuItem();
        trocarSenhaMenu = new javax.swing.JMenuItem();
        loginmenu = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        funcionalidades = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        gerencialMenu = new javax.swing.JMenu();
        novoFuncMenuGerencial = new javax.swing.JMenuItem();
        menuNotaCompra = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        administrativoMenu = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        menuNotaCompraAdministrativo = new javax.swing.JMenuItem();
        relatorios = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GoldFork");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        principal.setOpaque(false);

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        jPanel1.add(principal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 580));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 580));

        jMenu3.setText("Iniciar");

        inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        inicio.setText("Inicio");
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });
        jMenu3.add(inicio);

        trocarSenhaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange small.png"))); // NOI18N
        trocarSenhaMenu.setText("Trocar Senha");
        trocarSenhaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trocarSenhaMenuActionPerformed(evt);
            }
        });
        jMenu3.add(trocarSenhaMenu);

        loginmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginin.png"))); // NOI18N
        loginmenu.setText("Login");
        loginmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginmenuActionPerformed(evt);
            }
        });
        jMenu3.add(loginmenu);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logOut.png"))); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jMenu3.add(logout);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        funcionalidades.setText("Funcionalidades");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))); // NOI18N
        jMenuItem4.setText("Vendas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        funcionalidades.add(jMenuItem4);

        jMenuBar2.add(funcionalidades);

        gerencialMenu.setText("Gerencial");

        novoFuncMenuGerencial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-new-user.png"))); // NOI18N
        novoFuncMenuGerencial.setText("Novo Funcionário");
        novoFuncMenuGerencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoFuncMenuGerencialActionPerformed(evt);
            }
        });
        gerencialMenu.add(novoFuncMenuGerencial);

        menuNotaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coins-on-hand.png"))); // NOI18N
        menuNotaCompra.setText("Nova Nota de Compra");
        menuNotaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNotaCompraActionPerformed(evt);
            }
        });
        gerencialMenu.add(menuNotaCompra);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/people-trading.png"))); // NOI18N
        jMenuItem1.setText("Novo Fornecedor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItem1);

        jMenuBar2.add(gerencialMenu);

        administrativoMenu.setText("Administrativo");

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shopping-cart-sign.png"))); // NOI18N
        jMenuItem8.setText("Nova Lanchonete");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        administrativoMenu.add(jMenuItem8);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-new-user.png"))); // NOI18N
        jMenuItem11.setText("Novo Funcionário");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        administrativoMenu.add(jMenuItem11);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/apple-black-silhouette-with-a-leaf.png"))); // NOI18N
        jMenuItem7.setText("Novo Produto Atômico");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        administrativoMenu.add(jMenuItem7);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/package.png"))); // NOI18N
        jMenuItem2.setText("Novo Produto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        administrativoMenu.add(jMenuItem2);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/people-trading.png"))); // NOI18N
        jMenuItem10.setText("Novo Fornecedor");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        administrativoMenu.add(jMenuItem10);

        menuNotaCompraAdministrativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coins-on-hand.png"))); // NOI18N
        menuNotaCompraAdministrativo.setText("Nova Nota de Compra");
        menuNotaCompraAdministrativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNotaCompraAdministrativoActionPerformed(evt);
            }
        });
        administrativoMenu.add(menuNotaCompraAdministrativo);

        relatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reporting.png"))); // NOI18N
        relatorios.setText("Relatórios");
        relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosActionPerformed(evt);
            }
        });
        administrativoMenu.add(relatorios);

        jMenuBar2.add(administrativoMenu);

        jMenu1.setText("Sobre");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png"))); // NOI18N
        jMenuItem5.setText("Ajuda");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/team.png"))); // NOI18N
        jMenuItem6.setText("Desenvolvedores");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        this.chamarMenuInicial();
    }//GEN-LAST:event_inicioActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Deseja Realmente Fazer Logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirmation == 0) {
            this.travarMenus();
            this.chamarLogin();
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.chamarVendas();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.chamarDesenvolvedores();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Deseja Realmente Fechar o Sistema?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirmation == 0) {
            exit(0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void loginmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginmenuActionPerformed
        this.chamarLogin();
    }//GEN-LAST:event_loginmenuActionPerformed

    private void relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosActionPerformed
        this.chamarRelatorio_Administrativo();
    }//GEN-LAST:event_relatoriosActionPerformed

    private void novoFuncMenuGerencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoFuncMenuGerencialActionPerformed
        this.chamarNovoFuncionario_Gerencial();
    }//GEN-LAST:event_novoFuncMenuGerencialActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.chamarNovoProduto_Administrativo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        this.chamarNovoProdutoAtomico_Administrativo();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        this.chamarNovaLanchonete_Administrativo();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void menuNotaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNotaCompraActionPerformed
        this.chamarNovaNotaCompra_Gerencial();
    }//GEN-LAST:event_menuNotaCompraActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        this.chamarNovoFornecedor_Administrativo();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.chamarCadastrarFuncionario_Administrativo();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void menuNotaCompraAdministrativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNotaCompraAdministrativoActionPerformed
        this.chamarNovaNotaDeCompra_Administrativo();
    }//GEN-LAST:event_menuNotaCompraAdministrativoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.chamarNovoFornecedor_Gerencial();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void trocarSenhaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trocarSenhaMenuActionPerformed
        this.chamarTrocarSenha();
    }//GEN-LAST:event_trocarSenhaMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    //sets
    public void setIdUsuarioLoja(int idUsuarioLoja) {
        this.idUsuarioLoja = idUsuarioLoja;
    }

    public void setNomeUsuarioLogado(String nomeUsuario) {
        this.nomeUsuarioLogado = nomeUsuario;
    }

    public void setIdUsuarioLogado(int idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

    public void setPermissaoUsuarioLogado(int permissaoUsuarioLogado) {
        this.permissaoUsuarioLogado = permissaoUsuarioLogado;
    }
    
    //gets
    public int getIdUsuarioLoja() {
        return idUsuarioLoja;
    }
    
    public String getNomeUsuarioLogado() {
        return nomeUsuarioLogado;
    }

    public int getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    public int getPermissaoUsuarioLogado() {
        return permissaoUsuarioLogado;
    }
    
    //travar menus antes do login
    public void travarMenus(){
        funcionalidades.setEnabled(false);
        inicio.setEnabled(false);
        logout.setEnabled(false);
        loginmenu.setEnabled(true);
        gerencialMenu.setEnabled(false);
        administrativoMenu.setEnabled(false);
        trocarSenhaMenu.setEnabled(false);
    }
    
    //destravar menus após o login
    public void destravarMenus(){
        if(this.permissaoUsuarioLogado == 0){ //login dono
            funcionalidades.setEnabled(false);
            inicio.setEnabled(true);
            logout.setEnabled(true);
            loginmenu.setEnabled(false);
            gerencialMenu.setEnabled(false);
            administrativoMenu.setEnabled(true);
            novoFuncMenuGerencial.setEnabled(false);
            menuNotaCompra.setEnabled(false);
            trocarSenhaMenu.setEnabled(true);
        }
        else if(this.permissaoUsuarioLogado == 1){ //login gerente
            funcionalidades.setEnabled(false);
            inicio.setEnabled(true);
            logout.setEnabled(true);
            loginmenu.setEnabled(false);
            gerencialMenu.setEnabled(true);
            administrativoMenu.setEnabled(false);
            novoFuncMenuGerencial.setEnabled(true);
            menuNotaCompra.setEnabled(true);
            trocarSenhaMenu.setEnabled(true);
        }
        else{
            funcionalidades.setEnabled(true); //login caixa
            inicio.setEnabled(true);
            logout.setEnabled(true);
            loginmenu.setEnabled(false);
            gerencialMenu.setEnabled(false);
            administrativoMenu.setEnabled(false);
            trocarSenhaMenu.setEnabled(true);
        }     
    }
    
    //chamadores de janelas comuns
    public void chamarLogin(){
        principal.removeAll();
        principal.add(new Login(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarMenuInicial(){
        principal.removeAll();
        principal.add(new MenuInicial(this.nomeUsuarioLogado, this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarDesenvolvedores(){
        principal.removeAll();
        principal.add(new Desenvolvedores());
        principal.revalidate();
        principal.repaint();
    }
    public void chamarTrocarSenha(){
        AlterarSenha a = new AlterarSenha(this, true, this);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }
    
    //chamadores de janelas menu Vendas
    public void chamarVendas(){
        principal.removeAll();
        principal.add(new Vendas(this));
        principal.revalidate();
        principal.repaint();
    }
    
    //chamadores de janelas menu Gerencial
    public void chamarNovoFuncionario_Gerencial(){
        principal.removeAll();
        principal.add(new NovoFuncionario_Gerencial(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovaNotaCompra_Gerencial(){
        principal.removeAll();
        principal.add(new NovaNotaCompra_Gerencial(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovoFornecedor_Gerencial(){
        principal.removeAll();
        principal.add(new NovoFornecedor_Gerencial(this));
        principal.revalidate();
        principal.repaint();
    }
    
    //chamar janelas menu administrativo
    public void chamarRelatorio_Administrativo(){
        principal.removeAll();
        principal.add(new Relatorios_Administrativos(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovaNotaCompra_Administrativo(){
        principal.removeAll();
        principal.add(new NovaNotaCompra_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovoProduto_Administrativo(){
        principal.removeAll();
        principal.add(new NovoProduto_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovoProdutoAtomico_Administrativo(){
        principal.removeAll();
        principal.add(new NovoProdutoAtomico_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovaLanchonete_Administrativo(){
        principal.removeAll();
        principal.add(new NovaLanchonete_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovoFornecedor_Administrativo(){
        principal.removeAll();
        principal.add(new NovoFornecedor_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarCadastrarFuncionario_Administrativo(){
        principal.removeAll();
        principal.add(new NovoFuncionario_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    public void chamarNovaNotaDeCompra_Administrativo(){
        principal.removeAll();
        principal.add(new NovaNotaCompra_Administrativo(this));
        principal.revalidate();
        principal.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu administrativoMenu;
    private javax.swing.JMenu funcionalidades;
    private javax.swing.JMenu gerencialMenu;
    private javax.swing.JMenuItem inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem loginmenu;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem menuNotaCompra;
    private javax.swing.JMenuItem menuNotaCompraAdministrativo;
    private javax.swing.JMenuItem novoFuncMenuGerencial;
    private javax.swing.JPanel principal;
    private javax.swing.JMenuItem relatorios;
    private javax.swing.JMenuItem trocarSenhaMenu;
    // End of variables declaration//GEN-END:variables
}
