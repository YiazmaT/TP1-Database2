/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesFuncionarios;

/**
 *
 * @author YiazmaT
 */
public class Caixa extends Funcionario{
    private String login;
    private String senha;

    public Caixa(String login, String senha, String nome, int id_funcionario, String telefone, String cpf, boolean ativo) {
        super(nome, id_funcionario, telefone, cpf, ativo);
        this.login = login;
        this.senha = senha;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
