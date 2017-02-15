/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoDeDados;

import ClasseProdutos.Produto;
import ClasseProdutos.ProdutoAtomico;
import ClassesFuncionarios.Caixa;
import ClassesFuncionarios.Cozinheiro;
import ClassesFuncionarios.Faxineiro;
import ClassesFuncionarios.FuncionarioTabela;
import ClassesFuncionarios.Gerente;
import ClassesFuncionarios.UserLogado;
import ClassesLojas.Fornecedor;
import ClassesLojas.Loja;
import ClassesNotas.NotaDeCompra;
import ClassesNotas.NotaDeVenda;
import InterfaceVendas.Pagamento;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YiazmaT
 */
public class BancoDeDados {

    private static final String BANCOusuario = "root";
    private static final String BANCOsenha = "mayara";
    private static final String enderecoIP = "jdbc:mysql://127.0.0.1:3306/goldfork";
    private static final String arqLogGeral = "LogGeral.txt";
    private static final String arqLogInsert = "LogInsert.txt";
    private static final String arqLogUpdate = "LogUpdate.txt";
    private static final String arqLogDelete = "LogDelete.txt";
    private static final String arqLogSelect = "LogSelect.txt";
    private static final String arqLogProcedureCall = "LogProcedureCall.txt";
    
    private static int idUsuarioLogado;
    private static int permissaoUsuarioLogado;
    private static int idLojaQueTrabalha;

    public static void setIdUsuarioLogado(int idUsuarioLogado) {
        BancoDeDados.idUsuarioLogado = idUsuarioLogado;
    }

    public static void setPermissaoUsuarioLogado(int permissaoUsuarioLogado) {
        BancoDeDados.permissaoUsuarioLogado = permissaoUsuarioLogado;
    }

    public static void setIdLojaQueTrabalha(int idLojaQueTrabalha) {
        BancoDeDados.idLojaQueTrabalha = idLojaQueTrabalha;
    }
    
    /*
     *-----SELECTS-----*
     */
    public static String recuperarNomeLoja(int idLanchonete) {
        String nome = null;
        String send = "SELECT nome FROM lanchonete WHERE lanchonete.id_lanchonete = " + idLanchonete+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            result.next();
            nome = result.getString("nome");

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return nome;
    }

    public static ArrayList<Loja> recuperarNomeEIDLojas(int id_dono) { //recupera apenas o nome e o id de todas as lojas 
        ArrayList<Loja> lojas = new ArrayList<Loja>();
        String send = "SELECT id_lanchonete, nome FROM lanchonete WHERE lanchonete.cod_dono = " + id_dono+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Loja loja = new Loja(Integer.parseInt(result.getString("id_lanchonete")), result.getString("nome"));
                lojas.add(loja);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return lojas;
    }

    public static ArrayList<Fornecedor> recuperarNomeEIDFornecedores(int id_loja) { //recupera apenas o nome e o id de todos os fornecedores que possuem vinculo com o idLoja passado como parametro 
        ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        String send = "SELECT id_fornecedor, nome FROM (SELECT * FROM fornecimento WHERE fornecimento.cod_lanchonete = " + id_loja
                + ") AS test INNER JOIN fornecedor ON test.cod_fornecedor = fornecedor.id_fornecedor"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Fornecedor forc = new Fornecedor(result.getInt("id_fornecedor"), result.getString("nome"));
                fornecedores.add(forc);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return fornecedores;
    }
    
    public static ArrayList<Loja> recuperarLojasFornecidasPorUmFornecedor(int id_fornecedor) { //recupera apenas o nome e o id de todos os fornecedores que possuem vinculo com o idLoja passado como parametro 
        ArrayList<Loja> lojas = new ArrayList<Loja>();
        String send = "SELECT nome, id_lanchonete FROM (SELECT * FROM fornecimento WHERE fornecimento.cod_fornecedor = " + id_fornecedor
                + ") AS test INNER JOIN lanchonete ON test.cod_lanchonete = lanchonete.id_lanchonete"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Loja loja = new Loja(result.getInt("id_lanchonete"), result.getString("nome"));
                lojas.add(loja);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return lojas;
    }
    
    public static ArrayList<ProdutoAtomico> recuperarProdutosAtomicos() {
        ArrayList<ProdutoAtomico> produtos = new ArrayList<ProdutoAtomico>();
        String send = "SELECT * FROM produto_atomico ORDER BY nome"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                ProdutoAtomico atual = new ProdutoAtomico(Integer.parseInt(result.getString("id_produto_atomico")), result.getString("unidade_medida"), result.getString("nome"));
                produtos.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return produtos;
    }

    //trocar para procedure caso sobre tempo; \/
    public static boolean verificaLojaComNomeIgual(String nome, int idDono) {
        String send = "SELECT nome FROM lanchonete WHERE lanchonete.nome = " + "\"" + nome + "\""
                + " AND lanchonete.cod_dono = " + idDono+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            if (!result.next()) {
                return false;
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return true;
    }

    //trocar para procedure caso sobre tempo; \/
    public static boolean verificaLojaComCNPJIgual(String cnpj, int idDono) {
        String send = "SELECT nome FROM lanchonete WHERE lanchonete.cnpj = " + "\"" + cnpj + "\""
                + " AND lanchonete.cod_dono = " + idDono+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            if (!result.next()) {
                return false;
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return true;
    }

    public static ArrayList<Produto> recuperarTodosOsProdutos() {
        ArrayList<Produto> p = new ArrayList<Produto>();
        String send = "SELECT * FROM produto WHERE produto.ativo = true"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Produto atual = new Produto(result.getInt("id_produto"), result.getString("nome"), result.getFloat("preco"), result.getBoolean("ativo"));
                p.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return p;
    }

    public static boolean possuiEstoqueParaVender(Produto p, int idLoja) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            Statement stat2 = conn.createStatement();
            String send = "SELECT * FROM composicao_produto WHERE composicao_produto.cod_produto = " + p.getId_produto()+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);

            ResultSet resultComposicaoProduto = stat.executeQuery(send);
            ResultSet verif;
            while (resultComposicaoProduto.next()) {
                send = "CALL verificaDisponibilidadeNoEstoque(" + idLoja + ", " + resultComposicaoProduto.getInt("cod_produto_atomico") + ", " + (p.getQuantidade() * resultComposicaoProduto.getFloat("quantidade")) + ")"+";";
                BancoDeDados.logGeral(send);
                BancoDeDados.logProcedureCall(send);

                verif = stat2.executeQuery(send);
                verif.next();
                if (verif.getBoolean("Msg") == false) {
                    return false; //estoque insuficiente
                }
            }

            stat.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static ArrayList<ProdutoAtomico> recuperarProdutoAtomicoComIdProduto(int id_produto){
        ArrayList<ProdutoAtomico> p = new ArrayList<ProdutoAtomico>();
        String send = "SELECT id_produto_atomico, nome, unidade_medida, quantidade FROM produto_atomico INNER JOIN composicao_produto " +
                    "WHERE composicao_produto.cod_produto_atomico = produto_atomico.id_produto_atomico " +
                    "AND composicao_produto.cod_produto = "+id_produto+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                ProdutoAtomico atual = new ProdutoAtomico(result.getInt("id_produto_atomico"), result.getString("unidade_medida"), result.getString("nome"), result.getFloat("quantidade"));
                p.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return p;
    }
    
    public static ArrayList<Gerente> gerentesDeUmaLoja(int id_loja){
        ArrayList<Gerente> g = new ArrayList<Gerente>();
        String send = "SELECT * FROM gerente WHERE gerente.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Gerente atual = new Gerente(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_gerente"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
                g.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return g;
    }
    
    public static ArrayList<Caixa> caixasDeUmaLoja(int id_loja){
        ArrayList<Caixa> c = new ArrayList<Caixa>();
        String send = "SELECT * FROM caixa WHERE caixa.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Caixa atual = new Caixa(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_caixa"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
                c.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return c;
    }
    
    public static ArrayList<Faxineiro> faxineirosDeUmaLoja(int id_loja){
        ArrayList<Faxineiro> f = new ArrayList<Faxineiro>();
        String send = "SELECT * FROM faxineiro WHERE faxineiro.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Faxineiro atual = new Faxineiro(result.getString("nome"), result.getInt("id_faxineiro"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
                f.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return f;
    }
    
    public static ArrayList<Cozinheiro> cozinheirosDeUmaLoja(int id_loja){
        ArrayList<Cozinheiro> c = new ArrayList<Cozinheiro>();
        String send = "SELECT * FROM cozinheiro WHERE cozinheiro.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Cozinheiro atual = new Cozinheiro(result.getString("nome"), result.getInt("id_cozinheiro"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
                c.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return c;
    }
    
    public static ArrayList<Loja> todasAsLojas(int id_dono){
        ArrayList<Loja> l = new ArrayList<Loja>();
        String send = "SELECT * FROM lanchonete"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Loja atual = new Loja(
                        result.getInt("id_lanchonete"),
                        result.getString("telefone"),
                        result.getString("nome"),
                        result.getString("endereco_cidade"),
                        result.getString("endereco_bairro"),
                        result.getString("endereco_estado"),
                        result.getString("endereco_rua"),
                        result.getString("endereco_cep"),
                        result.getInt("endereco_numero"),
                        result.getString("cnpj")
                );
                l.add(atual);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return l;
    }
    
    public static ArrayList<Fornecedor> todosOsFornecedoresDeUmaLoja(int id_loja){
        ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        String send = "SELECT id_fornecedor, nome, telefone, cnpj, ativo FROM (SELECT * FROM fornecimento WHERE fornecimento.cod_lanchonete = " + id_loja
                + ") AS test INNER JOIN fornecedor ON test.cod_fornecedor = fornecedor.id_fornecedor"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Fornecedor forc = new Fornecedor(result.getInt("id_fornecedor"), result.getString("nome"), result.getString("telefone"), result.getString("cnpj"), result.getBoolean("ativo"));
                fornecedores.add(forc);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return fornecedores;
    }
    
    public static ArrayList<ProdutoAtomico> estoqueDeUmaLoja(int id_loja){
        ArrayList<ProdutoAtomico> pas = new ArrayList<ProdutoAtomico>();
        String send = "SELECT id_produto_atomico, nome, unidade_medida, quantidade FROM (SELECT * FROM estoqueloja WHERE estoqueloja.cod_lanchonete = "+id_loja
                + ") AS test INNER JOIN produto_atomico ON test.cod_produto_atomico = produto_atomico.id_produto_atomico"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                ProdutoAtomico p = new ProdutoAtomico(result.getInt("id_produto_atomico"), result.getString("unidade_medida"), result.getString("nome"), result.getFloat("quantidade"));
                pas.add(p);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return pas;
    }
    
    public static ArrayList<NotaDeCompra> notasDeCompra(int id_loja){
        ArrayList<NotaDeCompra> notas = new ArrayList<NotaDeCompra>();
        String send = "SELECT * FROM nota_de_compra WHERE nota_de_compra.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                NotaDeCompra n = new NotaDeCompra(result.getInt("id_nota_compra"), result.getString("data"), result.getFloat("valor_total"), result.getInt("cod_fornecedor"), result.getInt("cod_lanchonete"));
                notas.add(n);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return notas;
    }
    
    public static String nomeDeUmFornecedor(int id_fornecedor){
        String nome = "ERROR";
        String send = "SELECT nome FROM fornecedor WHERE fornecedor.id_fornecedor = "+id_fornecedor+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                nome = result.getString("nome");
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return nome;
    }
    
    public static ArrayList<ProdutoAtomico> itensDeUmaNotaCompra(int id_nota_de_compra){
        ArrayList<ProdutoAtomico> p = new ArrayList<ProdutoAtomico>();
        String send = "SELECT id_produto_atomico, nome, unidade_medida, quantidade, valor_unitario FROM (SELECT * FROM itens_nota_compra WHERE itens_nota_compra.cod_nota_de_compra = "+id_nota_de_compra
                + ") AS test INNER JOIN produto_atomico ON test.cod_produto_atomico = produto_atomico.id_produto_atomico"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                ProdutoAtomico pp = new ProdutoAtomico(result.getInt("id_produto_atomico"), result.getString("unidade_medida"), result.getString("nome"), result.getFloat("quantidade"), result.getFloat("valor_unitario"));
                p.add(pp);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        
        return p;
    }
    
    public static ArrayList<NotaDeVenda> notasDeVenda(int id_loja){
        ArrayList<NotaDeVenda> notas = new ArrayList<NotaDeVenda>();
        String send = "SELECT * FROM nota_de_venda WHERE nota_de_venda.cod_lanchonete = "+id_loja+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                NotaDeVenda n = new NotaDeVenda(result.getInt("id_nota_venda"), result.getString("data"), result.getFloat("valor_total"), result.getInt("cod_caixa"), result.getInt("cod_lanchonete"));
                notas.add(n);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return notas;
    }
    
    public static String nomeDeUmCaixa(int id_caixa){
        String nome = "ERROR";
        String send = "SELECT nome FROM caixa WHERE caixa.id_caixa = "+id_caixa+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                nome = result.getString("nome");
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return nome;
    }
    
    public static ArrayList<Produto> itensDeUmaNotaVenda(int id_nota_de_venda){
        ArrayList<Produto> p = new ArrayList<Produto>();
        String send = "SELECT id_produto, nome, quantidade, valor_unitario, ativo FROM (SELECT * FROM itens_nota_venda WHERE itens_nota_venda.cod_nota_venda = "+id_nota_de_venda
                + ") AS test INNER JOIN produto ON test.cod_produto = produto.id_produto"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Produto pp = new Produto(result.getInt("id_produto"), result.getString("nome"), result.getInt("quantidade"), result.getFloat("valor_unitario"), result.getBoolean("ativo"));
                p.add(pp);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        
        return p;
    }
    
    public static ArrayList<Pagamento> pagamentosDeUmaNotaVenda(int id_nota_de_venda){
        ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
        
        try {
            //pagamentos em dinheiro;
            String send = "SELECT * FROM pagamento_dinheiro WHERE pagamento_dinheiro.cod_nota_venda = "+id_nota_de_venda+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);
            while (result.next()) {
                Pagamento pp = new Pagamento(result.getFloat("valor"), false);
                pagamentos.add(pp);
            }

            //pagamentos em cartão;
            send = "SELECT * FROM pagamento_cartao WHERE pagamento_cartao.cod_nota_venda = "+id_nota_de_venda+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            result = stat.executeQuery(send);
            while (result.next()) {
                Pagamento pp = new Pagamento(result.getFloat("valor"), true, result.getInt("ultimos_digitos"), result.getString("bandeira"), result.getString("tipo"));
                pagamentos.add(pp);
            }
            
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        
        return pagamentos;
    }
    
    public static Gerente informacoesDeUmGerente(int id_gerente){
        String send = "SELECT * FROM gerente WHERE gerente.id_gerente = "+id_gerente+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        Gerente atual = null;
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                atual = new Gerente(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_gerente"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return atual;
    }
    
    public static Caixa informacoesDeUmCaixa(int id_caixa){
        String send = "SELECT * FROM caixa WHERE caixa.id_caixa = "+id_caixa+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        Caixa atual = null;
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                atual = new Caixa(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_caixa"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return atual;
    }
    
    public static Faxineiro informacoesDeUmFaxineiro(int id_faxineiro){
        String send = "SELECT * FROM faxineiro WHERE faxineiro.id_faxineiro = "+id_faxineiro+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        Faxineiro atual = null;
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                atual = new Faxineiro(result.getString("nome"), result.getInt("id_faxineiro"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return atual;
    }
    
    public static Cozinheiro informacoesDeUmCozinheiro(int id_cozinheiro){
        String send = "SELECT * FROM cozinheiro WHERE cozinheiro.id_cozinheiro = "+id_cozinheiro+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        Cozinheiro atual = null;
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                atual = new Cozinheiro(result.getString("nome"), result.getInt("id_cozinheiro"), result.getString("telefone"), result.getString("cpf"), result.getBoolean("ativo"));
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return atual;
    } 
    
    public static ArrayList<NotaDeCompra> notasDeCompraEntreDatas(int id_loja, String data1, String data2){
        ArrayList<NotaDeCompra> notas = new ArrayList<NotaDeCompra>();
        String send = "SELECT * FROM nota_de_compra WHERE "
        +"nota_de_compra.cod_lanchonete = "+id_loja
        +" AND nota_de_compra.data BETWEEN "
        +"\""+data1+"\""
        +" AND "
        +"\""+data2+"\""
        +";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                NotaDeCompra n = new NotaDeCompra(result.getInt("id_nota_compra"), result.getString("data"), result.getFloat("valor_total"), result.getInt("cod_fornecedor"), result.getInt("cod_lanchonete"));
                notas.add(n);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return notas;
    }
    
    public static ArrayList<NotaDeVenda> notasDeVendaEntreDatas(int id_loja, String data1, String data2){
        ArrayList<NotaDeVenda> notas = new ArrayList<NotaDeVenda>();
        String send = "SELECT * FROM nota_de_venda WHERE "
        +"nota_de_venda.cod_lanchonete = "+id_loja
        +" AND nota_de_venda.data BETWEEN "
        +"\""+data1+"\""
        +" AND "
        +"\""+data2+"\""
        +";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logSelect(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                NotaDeVenda n = new NotaDeVenda(result.getInt("id_nota_venda"), result.getString("data"), result.getFloat("valor_total"), result.getInt("cod_caixa"), result.getInt("cod_lanchonete"));
                notas.add(n);
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }

        return notas;
    }
    
    /*
     *-----INSERTS-----*
     */
    public static void cadastrarNovaLoja(String nome, String telefone, String cnpj, String rua, int numero, String bairro, String cidade, String cep, String estado, int cod_dono) {
        String send = "INSERT INTO lanchonete(nome, telefone, cnpj, endereco_rua, endereco_numero, endereco_bairro, endereco_cidade, endereco_cep, endereco_estado, cod_dono) VALUES("
                + "\"" + nome + "\"" + ","
                + "\"" + telefone + "\"" + ","
                + "\"" + cnpj + "\"" + ","
                + "\"" + rua + "\"" + ","
                + numero + ","
                + "\"" + bairro + "\"" + ","
                + "\"" + cidade + "\"" + ","
                + "\"" + cep + "\"" + ","
                + "\"" + estado + "\"" + ","
                + "\"" + cod_dono + "\""
                + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logInsert(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Lanchonete cadastrada com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void cadastrarNovoFuncionario(int tipo, int idLoja, String nome, String cpf, String telefone, String login, String senha) {
        switch (tipo) {
            case 0: { //caixa
                try {
                    Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
                    Statement stat = conn.createStatement();

                    String send = "INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + "\"" + login + "\"" + ","
                            + "\"" + senha + "\"" + ","
                            + idLoja +", true"+")"+";";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);

                    stat.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Caixa cadastrado com Sucesso!!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
                }
                break;
            }
            case 1: { //gerente
                try {
                    Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
                    Statement stat = conn.createStatement();

                    String send = "INSERT INTO gerente(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + "\"" + login + "\"" + ","
                            + "\"" + senha + "\"" + ","
                            + idLoja +", true"+")"+";";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);

                    stat.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Gerente cadastrado com Sucesso!!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
                }
                break;
            }
            case 2: { //auxiliar
                try {
                    Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
                    Statement stat = conn.createStatement();
                    String send = "INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + idLoja +", true"+")"+";";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);

                    stat.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Auxiliar de Limpeza cadastrado com Sucesso!!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
                }
                break;
            }
            case 3: { //cozinheiro
                try {
                    Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
                    Statement stat = conn.createStatement();
                    String send = "INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + idLoja +", true"+")"+";";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);

                    stat.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Cozinheiro cadastrado com Sucesso!!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
                }
                break;
            }
        }
    }

    public static void cadastrarNovoProdutoAtomico(String nome, String unidade_medida) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            String send = "INSERT INTO produto_atomico(nome, unidade_medida) VALUES("
                    + "\"" + nome + "\"" + ","
                    + "\"" + unidade_medida + "\"" + ")"+";";

            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto Atômico cadastrado com Sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void cadastrarNovoProduto(String nome, float preco, ArrayList<ProdutoAtomico> composicao) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            //criando produto
            String send = "INSERT INTO produto(nome, preco, ativo) VALUES("
                    + "\"" + nome + "\"" + ","
                    + preco + ", true)"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            send = "SELECT id_produto FROM produto ORDER BY id_produto DESC LIMIT 1"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idProduto = result2.getInt("id_produto");

            //criando tabela de composicao
            for (ProdutoAtomico composicao1 : composicao) {
                send = "INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES ("
                        + (idProduto) + ","
                        + composicao1.getIdProdutoAtomico() + ","
                        + composicao1.getQuantidade() + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);
            }

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void cadastrarNovoFornecedor(String nome, String telefone, String cnpj, ArrayList<Loja> lojasAfetadas) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            //cadastrar o fornecedor;
            String send = "INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("
                    + "\"" + nome + "\"" + ","
                    + "\"" + telefone + "\"" + ","
                    + "\"" + cnpj + "\"" + ", true)"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar o id do mesmo;
            send = "SELECT id_fornecedor FROM fornecedor ORDER BY id_fornecedor DESC LIMIT 1"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idFornecedor = result2.getInt("id_fornecedor");

            //cadastrando tabela de fornecimento;
            for (Loja atual : lojasAfetadas) {
                send = "INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES("
                        + idFornecedor + ", "
                        + atual.getId_lanchonete() + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);
            }

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void cadastrarNovaNotaDeCompra(String data, float valorTotalNota, int idFornecedor, int idLanchonete, ArrayList<ProdutoAtomico> itensNota) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            //cadastrar nova nota de compra;
            String send = "INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("
                    + "\"" + data + "\"" + ", "
                    + valorTotalNota + ", "
                    + idFornecedor + ", "
                    + idLanchonete + ")"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar id da mesma;
            send = "SELECT id_nota_compra FROM nota_de_compra ORDER BY id_nota_compra DESC LIMIT 1"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idNotaDeCompra = result2.getInt("id_nota_compra");

            //construir tabela de itens da nota de compra;
            for (ProdutoAtomico atual : itensNota) {
                send = "INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES("
                        + idNotaDeCompra + ", "
                        + atual.getIdProdutoAtomico() + ", "
                        + atual.getQuantidade() + ", "
                        + atual.getValorUnitario() + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);

                //atualizar estoque da loja;
                send = "CALL atualizarEstoque("
                        + idLanchonete + ", "
                        + atual.getIdProdutoAtomico() + ", "
                        + atual.getQuantidade() + ")"+";";
                
                BancoDeDados.logGeral(send);
                BancoDeDados.logProcedureCall(send);
                stat.executeQuery(send);
            }

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Nota de Compra cadastrada com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void cadastrarNovaNotaDeVenda(String data, float valor_total, int cod_caixa, int cod_lanchonete, ArrayList<Produto> produtos, ArrayList<Pagamento> pagamentos) {
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            Statement stat2 = conn.createStatement();

            //cadastrar nova nota de venda;
            String send = "INSERT INTO nota_de_venda (data, valor_total, cod_caixa, cod_lanchonete) VALUES("
                    + "\"" + data + "\"" + ", "
                    + valor_total + ", "
                    + cod_caixa + ", "
                    + cod_lanchonete + ")"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar id da mesma;
            send = "SELECT id_nota_venda FROM nota_de_venda ORDER BY id_nota_venda DESC LIMIT 1"+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logSelect(send);
            
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idNotaDeVenda = result2.getInt("id_nota_venda");

            //construir tabela de itens da nota de compra;
            for (Produto atual : produtos) {
                send = "INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES("
                        + idNotaDeVenda + ", "
                        + atual.getId_produto() + ", "
                        + atual.getQuantidade() + ", "
                        + atual.getValor() + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);

                //atualizar estoque da loja;
                send = "SELECT * FROM composicao_produto WHERE composicao_produto.cod_produto = " + atual.getId_produto()+";";
                BancoDeDados.logGeral(send);
                BancoDeDados.logSelect(send);
                
                ResultSet resultComposicaoProduto = stat.executeQuery(send);
                while (resultComposicaoProduto.next()) {
                    send = "CALL atualizarEstoqueSaida(" + cod_lanchonete + ", " + resultComposicaoProduto.getInt("cod_produto_atomico") + ", " + (atual.getQuantidade() * resultComposicaoProduto.getFloat("quantidade")) + ")"+";";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logProcedureCall(send);
                    stat2.executeUpdate(send);
                }
            }

            //construir tabela de pagamentos, sendo eles dinheiro ou cartão
            for (Pagamento atual : pagamentos) {
                if (atual.isCartao()) {
                    send = "INSERT INTO pagamento_cartao (valor, cod_nota_venda, ultimos_digitos, bandeira, tipo) VALUES("
                            + atual.getValor() + ", "
                            + idNotaDeVenda + ", "
                            + atual.getUltimos4Digitos() + ", "
                            + "\"" + atual.getBandeira() + "\"" + ", "
                            + "\"" + atual.getTipo() + "\"" + ")"+";";

                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);
                } else {
                    send = "INSERT INTO pagamento_dinheiro (valor, cod_nota_venda) VALUES("
                            + atual.getValor() + ", "
                            + idNotaDeVenda + ")"+";";

                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);

                }
            }

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Nota de Venda cadastrada com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    } 
    
    /*
     *-----UPDATES-----*
     */
    public static void updateFuncionario(String nome, String telefone, FuncionarioTabela func){
        String send = "";
        if(func.getTipo().equals("caixa")){
            send = "UPDATE caixa SET caixa.nome = "+"\""+nome+"\""+", caixa.telefone = "+"\""+telefone+"\""+" WHERE caixa.id_caixa = "+func.getId()+";"; 
        }
        if(func.getTipo().equals("gerente")){
            send = "UPDATE gerente SET gerente.nome = "+"\""+nome+"\""+", gerente.telefone = "+"\""+telefone+"\""+" WHERE gerente.id_gerente = "+func.getId()+";";
        }
        if(func.getTipo().equals("faxineiro")){
            send = "UPDATE faxineiro SET faxineiro.nome = "+"\""+nome+"\""+", faxineiro.telefone = "+"\""+telefone+"\""+" WHERE faxineiro.id_faxineiro = "+func.getId()+";";
        }
        if(func.getTipo().equals("cozinheiro")){
            send = "UPDATE cozinheiro SET cozinheiro.nome = "+"\""+nome+"\""+", cozinheiro.telefone = "+"\""+telefone+"\""+" WHERE cozinheiro.id_cozinheiro = "+func.getId()+";";
        }
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateFornecedor(int id_fornecedor, String nome, String telefone, ArrayList<Loja> lojas){
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            //cadastrar o fornecedor;
            String send = "UPDATE fornecedor SET "
                    +"fornecedor.nome = "+ "\"" + nome + "\"" + ","
                    +"fornecedor.telefone = "+ "\"" + telefone + "\"" 
                    +" WHERE fornecedor.id_fornecedor = "+id_fornecedor+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logUpdate(send);
            stat.executeUpdate(send);

            //apagando a tabela de fornecimentos antiga;       
            send = "DELETE FROM fornecimento WHERE fornecimento.cod_fornecedor = "+id_fornecedor+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logDelete(send);
            stat.executeUpdate(send);
            
            //cadastrando tabela de fornecimento;
            for (Loja atual : lojas) {
                send = "INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES("
                        + id_fornecedor + ", "
                        + atual.getId_lanchonete() + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);
            }

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateProdutoAtomico(int id_pa, String nome, String unidade){
        String send = "UPDATE produto_atomico SET produto_atomico.nome = "+"\""+nome+"\""+", produto_atomico.unidade_medida = "+"\""+unidade+"\""+" WHERE produto_atomico.id_produto_atomico = "+id_pa+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateProduto(int id_produto, String nome, float valor, ArrayList<ProdutoAtomico> composicao){
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            
            //alterar o produto;
            String send = "UPDATE produto SET produto.nome = "+"\""+nome+"\""+", produto.preco = "+valor+" WHERE produto.id_produto = "+id_produto+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logUpdate(send);
            stat.executeUpdate(send);
            
            //deletar composicao antiga
            send = "DELETE FROM composicao_produto WHERE composicao_produto.cod_produto = "+id_produto+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logDelete(send);
            stat.executeUpdate(send);
            
            //cadastrar nova composicao
            for (ProdutoAtomico atual : composicao) {
                send = "INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES("
                        + id_produto + ", "
                        + atual.getIdProdutoAtomico() + ", "
                        + atual.getQuantidade() 
                        + ")"+";";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);
            }
            
            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateLanchonete(String nome, String telefone, String rua, int numero, String bairro, String cidade, String cep, String estado, int id_lanchonete){
        String send = "UPDATE lanchonete SET "
                +"lanchonete.nome = "+"\""+nome+"\""
                +", lanchonete.telefone = "+"\""+telefone+"\""
                +", lanchonete.endereco_rua = "+"\""+rua+"\""
                +", lanchonete.endereco_numero = "+numero
                +", lanchonete.endereco_bairro = "+"\""+bairro+"\""
                +", lanchonete.endereco_cidade = "+"\""+cidade+"\""
                +", lanchonete.endereco_cep = "+"\""+cep+"\""
                +", lanchonete.endereco_estado = "+"\""+estado+"\""
                +" WHERE lanchonete.id_lanchonete = "+id_lanchonete+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateEstoque(int id_produto_atomico, int id_lanchonete, float quantidade){
        String send = "UPDATE estoqueloja SET"
                +" estoqueloja.quantidade = "+quantidade
                +" WHERE estoqueloja.cod_lanchonete = "+id_lanchonete
                +" AND estoqueloja.cod_produto_atomico = "+id_produto_atomico+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void updateSenha(String novaSenha, int tipo, int id_usuario){
        String send = "";
        if(tipo == 0){
            send = "UPDATE dono SET dono.senha = "+"\""+novaSenha+"\""+" WHERE dono.id_dono = "+id_usuario+";";
        }
        if(tipo == 1){
            send = "UPDATE gerente SET gerente.senha = "+"\""+novaSenha+"\""+" WHERE gerente.id_gerente = "+id_usuario+";";
        }
        if(tipo == 2){
            send = "UPDATE caixa SET caixa.senha = "+"\""+novaSenha+"\""+" WHERE caixa.id_caixa = "+id_usuario+";";
        }
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Senha alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void desligarFuncionario(int id_funcionario, int tipo){
        String send = "";
        switch(tipo){
            //gerente
            case 0: send = "UPDATE gerente SET gerente.ativo = false WHERE gerente.id_gerente = "+id_funcionario+";"; break;
            //caixa
            case 1: send = "UPDATE caixa SET caixa.ativo = false WHERE caixa.id_caixa = "+id_funcionario+";"; break;
            //faxineiro
            case 2: send = "UPDATE faxineiro SET faxineiro.ativo = false WHERE faxineiro.id_faxineiro = "+id_funcionario+";"; break;
            //cozinheiro
            case 3: send = "UPDATE cozinheiro SET cozinheiro.ativo = false WHERE cozinheiro.id_cozinheiro = "+id_funcionario+";"; break;
        }
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Usuário Desligado Com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }

    public static void aumentarPreçoTodosOsProdutos(float porcentagem){
        String send = "UPDATE produto SET produto.preco = produto.preco + (produto.preco*("+porcentagem+"/100))"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Valores alterados com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void abaixarPreçoTodosOsProdutos(float porcentagem){
        String send = "UPDATE produto SET produto.preco = produto.preco - (produto.preco*("+porcentagem+"/100))"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logUpdate(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            stat.executeUpdate(send);

            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Valores alterados com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    /*
     *-----DELETES-----*
     */
    public static void deletarFornecedor(int id_fornecedor){
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            //desligando fornecedor
            String send = "UPDATE fornecedor SET fornecedor.ativo = false WHERE fornecedor.id_fornecedor = "+id_fornecedor+";";
            stat.executeUpdate(send);
            BancoDeDados.logGeral(send);
            BancoDeDados.logUpdate(send);
            
            //apagando tabela de fornecimento      
            send = "DELETE FROM fornecimento WHERE fornecimento.cod_fornecedor = "+id_fornecedor+";";
            BancoDeDados.logGeral(send);
            BancoDeDados.logDelete(send);
            stat.executeUpdate(send);
            
            
            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Fornecedor apagado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    public static void deletarProduto(int id_produto){
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();

            String send = "UPDATE produto SET produto.ativo = false WHERE produto.id_produto = "+id_produto+";";
            stat.executeUpdate(send);
            BancoDeDados.logGeral(send);
            BancoDeDados.logUpdate(send);
            
            stat.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto apagado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
    }
    
    /*
     *-----PROCEDURES CALL-----*
     */
    public static boolean verificaIfLoginCaixaExistente(String login) {
        String send = "CALL verificaIfLoginCaixaExistente(" + "\"" + login + "\"" + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um login igual a este
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfLoginGerenteExistente(String login) {
        String send = "CALL verificaIfLoginGerenteExistente(" + "\"" + login + "\"" + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um login igual a este
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfCpfFuncionarioExistente(String cpf) {
        String send = "CALL verificaIfCpfFuncionarioExistente(" + "\"" + cpf + "\"" + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um cpf igual a este
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfProdutoAtomicoComNomeIgual(String nome) {
        String send = "CALL verificaIfProdutoAtomicoComNomeIgual(" + "\"" + nome + "\"" + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um produto atomico com nome igual a este
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfProdutoComNomeIgual(String nome) {
        String send = "CALL verificaIfProdutoComNomeIgual(" + "\"" + nome + "\"" + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um produto com nome igual a este
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfFornecedorComNomeIgualUtilizandoIdDono(String nomeFornecedor, int id_dono) {
        String send = "CALL verificaIfFornecedorComNomeIgualUtilizandoIdDono(" + "\"" + nomeFornecedor + "\", " + id_dono + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um fornecedor com este nome
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static boolean verificaIfFornecedorComCNPJIgualUtilizandoIdDono(String cnpjFornecedor, int id_dono) {
        String send = "CALL verificaIfFornecedorComCNPJIgualUtilizandoIdDono(" + "\"" + cnpjFornecedor + "\", " + id_dono + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true; //ja existe um fornecedor com este cnpj
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }

    public static UserLogado verificaIfLoginExistente(String login, String senha) {
        //quando chamar a procedure de validar login, adote 1 para donos, 2 para caixas e 3 para gerentes;
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            String send = "CALL validaLogin("
                    + "\"" + login + "\"" + ", "
                    + "\"" + senha + "\"" + ", ";

            ResultSet result;

            if (login.contains("@dono.goldfork.com")) {//1
                send += 1 + ")"+";";
                BancoDeDados.logGeral(send);
                BancoDeDados.logProcedureCall(send);
                
                result = stat.executeQuery(send);

                //verifica se existe
                if (!result.next()) {
                    return null;
                } else {
                    UserLogado user = new UserLogado(result.getInt("id_dono"), result.getString("nome"), 0, 0);
                    return user;
                }
            } else if (login.contains("@caixa.goldfork.com")) {//2
                send += 2 + ")"+";";
                BancoDeDados.logGeral(send);
                BancoDeDados.logProcedureCall(send);
                
                result = stat.executeQuery(send);

                //verifica se existe
                if (!result.next()) {
                    return null;
                } else {
                    UserLogado user = new UserLogado(result.getInt("id_caixa"), result.getString("nome"), 2, result.getInt("cod_lanchonete"));
                    return user;
                }
            } else if (login.contains("@gerente.goldfork.com")) {//3
                send += 3 + ")"+";";
                BancoDeDados.logGeral(send);
                BancoDeDados.logProcedureCall(send);
                
                result = stat.executeQuery(send);

                //verifica se existe
                if (!result.next()) {
                    return null;
                } else {
                    UserLogado user = new UserLogado(result.getInt("id_gerente"), result.getString("nome"), 1, result.getInt("cod_lanchonete"));
                    return user;
                }
            }

            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return null;
    }
    
    public static boolean verificaSenhaCorreta(String senha, int tipo, int id_usuario){
        String send = "CALL validaSenha(" 
                +"\""+senha+"\""+", " 
                +tipo+ ", "
                +id_usuario
                + ")"+";";
        BancoDeDados.logGeral(send);
        BancoDeDados.logProcedureCall(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            result.next();
            if (result.getBoolean("Msg") == true) {
                return true;
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!!\n" + ex.getMessage());
        }
        return false;
    }
    
    /*
     *-----MÉTODOS PARA GERAR LOG-----*
     */
    private static void logGeral(String send) {
        System.out.println(send); //fazer arquivo txt de LOG;

        try {
            File f = new File(arqLogGeral);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Geral" + ex);
        }
    }

    private static void logInsert(String send) {
        try {
            File f = new File(arqLogInsert);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Inserts" + ex);
        }
    }
    
    private static void logUpdate(String send){
        try {
            File f = new File(arqLogUpdate);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Updates" + ex);
        }
    }
    
    private static void logDelete(String send){
        try {
            File f = new File(arqLogDelete);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Deletes" + ex);
        }
    }
    
    private static void logSelect(String send){
        try {
            File f = new File(arqLogSelect);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Selects" + ex);
        }
    }
    
    private static void logProcedureCall(String send){
        try {
            File f = new File(arqLogProcedureCall);
            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (f.exists() == false) {
                f.createNewFile();
            }
            //escreve no arquivo
            printWriter.println(send);

            printWriter.close();
            buffer.close();
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Arquivo de Log Procedure Call" + ex);
        }
    }

}//end class;
