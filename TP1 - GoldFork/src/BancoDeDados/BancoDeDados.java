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
import ClassesFuncionarios.Gerente;
import ClassesFuncionarios.UserLogado;
import ClassesLojas.Fornecedor;
import ClassesLojas.Loja;
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

    /*
     *-----SELECTS-----*
     */
    public static String recuperarNomeLoja(int idLanchonete) {
        String nome = null;
        String send = "SELECT nome FROM lanchonete WHERE lanchonete.id_lanchonete = " + idLanchonete;
        BancoDeDados.logGeral(send);

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
        String send = "SELECT id_lanchonete, nome FROM lanchonete WHERE lanchonete.cod_dono = " + id_dono;
        BancoDeDados.logGeral(send);

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
        String send = "SELECT id_fornecedor, nome FROM (select * from fornecimento where fornecimento.cod_lanchonete = " + id_loja
                + ") AS test INNER JOIN fornecedor ON test.cod_fornecedor = fornecedor.id_fornecedor";
        BancoDeDados.logGeral(send);

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

    public static ArrayList<ProdutoAtomico> recuperarProdutosAtomicos() {
        ArrayList<ProdutoAtomico> produtos = new ArrayList<ProdutoAtomico>();
        String send = "SELECT * FROM produto_atomico ORDER BY nome";
        BancoDeDados.logGeral(send);

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
                + " AND lanchonete.cod_dono = " + idDono;
        BancoDeDados.logGeral(send);

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
                + " AND lanchonete.cod_dono = " + idDono;
        BancoDeDados.logGeral(send);

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
        String send = "SELECT * FROM produto";
        BancoDeDados.logGeral(send);

        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = result = stat.executeQuery(send);

            while (result.next()) {
                Produto atual = new Produto(result.getInt("id_produto"), result.getString("nome"), result.getFloat("preco"));
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
            String send = "SELECT * FROM composicao_produto WHERE composicao_produto.cod_produto = " + p.getId_produto();
            BancoDeDados.logGeral(send);

            ResultSet resultComposicaoProduto = stat.executeQuery(send);
            ResultSet verif;
            while (resultComposicaoProduto.next()) {
                send = "CALL verificaDisponibilidadeNoEstoque(" + idLoja + ", " + resultComposicaoProduto.getInt("cod_produto_atomico") + ", " + (p.getQuantidade() * resultComposicaoProduto.getFloat("quantidade")) + ")";
                BancoDeDados.logGeral(send);

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
                    "AND composicao_produto.cod_produto = "+id_produto;
        BancoDeDados.logGeral(send);
        
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
        String send = "SELECT * FROM gerente WHERE gerente.cod_lanchonete = "+id_loja;
        BancoDeDados.logGeral(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Gerente atual = new Gerente(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_gerente"), result.getString("telefone"), result.getString("cpf"));
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
        String send = "SELECT * FROM caixa WHERE caixa.cod_lanchonete = "+id_loja;
        BancoDeDados.logGeral(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Caixa atual = new Caixa(result.getString("login"), result.getString("senha"), result.getString("nome"), result.getInt("id_caixa"), result.getString("telefone"), result.getString("cpf"));
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
        String send = "SELECT * FROM faxineiro WHERE faxineiro.cod_lanchonete = "+id_loja;
        BancoDeDados.logGeral(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Faxineiro atual = new Faxineiro(result.getString("nome"), result.getInt("id_faxineiro"), result.getString("telefone"), result.getString("cpf"));
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
        String send = "SELECT * FROM cozinheiro WHERE cozinheiro.cod_lanchonete = "+id_loja;
        BancoDeDados.logGeral(send);
        
        try {
            Connection conn = DriverManager.getConnection(enderecoIP, BANCOusuario, BANCOsenha);
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(send);

            while (result.next()) {
                Cozinheiro atual = new Cozinheiro(result.getString("nome"), result.getInt("id_cozinheiro"), result.getString("telefone"), result.getString("cpf"));
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
        String send = "SELECT * FROM lanchonete";
        BancoDeDados.logGeral(send);
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
                + ");";
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

                    String send = "INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + "\"" + login + "\"" + ","
                            + "\"" + senha + "\"" + ","
                            + idLoja + ")";
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

                    String send = "INSERT INTO gerente(nome, telefone, cpf, login, senha, cod_lanchonete) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + "\"" + login + "\"" + ","
                            + "\"" + senha + "\"" + ","
                            + idLoja + ")";
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
                    String send = "INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + idLoja + ")";
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
                    String send = "INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete) VALUES("
                            + "\"" + nome + "\"" + ","
                            + "\"" + telefone + "\"" + ","
                            + "\"" + cpf + "\"" + ","
                            + idLoja + ")";
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
                    + "\"" + unidade_medida + "\"" + ")";

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
            String send = "INSERT INTO produto(nome, preco) VALUES("
                    + "\"" + nome + "\"" + ","
                    + preco + ")";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            send = "SELECT id_produto FROM produto ORDER BY id_produto DESC LIMIT 1";
            BancoDeDados.logGeral(send);
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idProduto = result2.getInt("id_produto");

            //criando tabela de composicao
            for (ProdutoAtomico composicao1 : composicao) {
                send = "INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES ("
                        + (idProduto) + ","
                        + composicao1.getIdProdutoAtomico() + ","
                        + composicao1.getQuantidade() + ")";

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
            String send = "INSERT INTO fornecedor(nome, telefone, cnpj) VALUES("
                    + "\"" + nome + "\"" + ","
                    + "\"" + telefone + "\"" + ","
                    + "\"" + cnpj + "\"" + ")";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar o id do mesmo;
            send = "SELECT id_fornecedor FROM fornecedor ORDER BY id_fornecedor DESC LIMIT 1";
            BancoDeDados.logGeral(send);
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idFornecedor = result2.getInt("id_fornecedor");

            //cadastrando tabela de fornecimento;
            for (Loja atual : lojasAfetadas) {
                send = "INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES("
                        + idFornecedor + ", "
                        + atual.getId_lanchonete() + ")";

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
                    + idLanchonete + ")";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar id da mesma;
            send = "SELECT id_nota_compra FROM nota_de_compra ORDER BY id_nota_compra DESC LIMIT 1";
            BancoDeDados.logGeral(send);
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idNotaDeCompra = result2.getInt("id_nota_compra");

            //construir tabela de itens da nota de compra;
            for (ProdutoAtomico atual : itensNota) {
                send = "INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES("
                        + idNotaDeCompra + ", "
                        + atual.getIdProdutoAtomico() + ", "
                        + atual.getQuantidade() + ", "
                        + atual.getValorUnitario() + ")";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);

                //atualizar estoque da loja;
                send = "CALL atualizarEstoque("
                        + idLanchonete + ", "
                        + atual.getIdProdutoAtomico() + ", "
                        + atual.getQuantidade() + ")";
                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
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
                    + cod_lanchonete + ")";
            BancoDeDados.logGeral(send);
            BancoDeDados.logInsert(send);
            stat.executeUpdate(send);

            //pegar id da mesma;
            send = "SELECT id_nota_venda FROM nota_de_venda ORDER BY id_nota_venda DESC LIMIT 1";
            BancoDeDados.logGeral(send);
            ResultSet result2 = stat.executeQuery(send);
            result2.next();
            int idNotaDeVenda = result2.getInt("id_nota_venda");

            //construir tabela de itens da nota de compra;
            for (Produto atual : produtos) {
                send = "INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES("
                        + idNotaDeVenda + ", "
                        + atual.getId_produto() + ", "
                        + atual.getQuantidade() + ", "
                        + atual.getValor() + ")";

                BancoDeDados.logGeral(send);
                BancoDeDados.logInsert(send);
                stat.executeUpdate(send);

                //atualizar estoque da loja;
                send = "SELECT * FROM composicao_produto WHERE composicao_produto.cod_produto = " + atual.getId_produto();
                BancoDeDados.logGeral(send);
                ResultSet resultComposicaoProduto = stat.executeQuery(send);
                while (resultComposicaoProduto.next()) {
                    send = "CALL atualizarEstoqueSaida(" + cod_lanchonete + ", " + resultComposicaoProduto.getInt("cod_produto_atomico") + ", " + (atual.getQuantidade() * resultComposicaoProduto.getFloat("quantidade")) + ")";
                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
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
                            + "\"" + atual.getTipo() + "\"" + ")";

                    BancoDeDados.logGeral(send);
                    BancoDeDados.logInsert(send);
                    stat.executeUpdate(send);
                } else {
                    send = "INSERT INTO pagamento_dinheiro (valor, cod_nota_venda) VALUES("
                            + atual.getValor() + ", "
                            + idNotaDeVenda + ")";

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
     *-----PROCEDURES CALL-----*
     */

    public static boolean verificaIfLoginCaixaExistente(String login) {
        String send = "CALL verificaIfLoginCaixaExistente(" + "\"" + login + "\"" + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfLoginGerenteExistente(" + "\"" + login + "\"" + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfCpfFuncionarioExistente(" + "\"" + cpf + "\"" + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfProdutoAtomicoComNomeIgual(" + "\"" + nome + "\"" + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfProdutoComNomeIgual(" + "\"" + nome + "\"" + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfFornecedorComNomeIgualUtilizandoIdDono(" + "\"" + nomeFornecedor + "\", " + id_dono + ")";
        BancoDeDados.logGeral(send);

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
        String send = "CALL verificaIfFornecedorComCNPJIgualUtilizandoIdDono(" + "\"" + cnpjFornecedor + "\", " + id_dono + ")";
        BancoDeDados.logGeral(send);

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
                send += 1 + ")";
                BancoDeDados.logGeral(send);
                result = stat.executeQuery(send);

                //verifica se existe
                if (!result.next()) {
                    return null;
                } else {
                    UserLogado user = new UserLogado(result.getInt("id_dono"), result.getString("nome"), 0, 0);
                    return user;
                }
            } else if (login.contains("@caixa.goldfork.com")) {//2
                send += 2 + ")";
                BancoDeDados.logGeral(send);
                result = stat.executeQuery(send);

                //verifica se existe
                if (!result.next()) {
                    return null;
                } else {
                    UserLogado user = new UserLogado(result.getInt("id_caixa"), result.getString("nome"), 2, result.getInt("cod_lanchonete"));
                    return user;
                }
            } else if (login.contains("@gerente.goldfork.com")) {//3
                send += 3 + ")";
                BancoDeDados.logGeral(send);
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
    /*
     *-----CLASSES PARA GERAR LOG-----*
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
}
