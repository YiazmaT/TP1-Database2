package ClassesFuncionarios;

public class UserLogado {
    private int id_user;
    private String nome;
    private int permissao;
    private int id_loja_que_trabalha;

    public UserLogado(int id_user, String nome, int permissao, int id_loja_que_trabalha) {
        this.id_user = id_user;
        this.nome = nome;
        this.permissao = permissao;
        this.id_loja_que_trabalha = id_loja_que_trabalha;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }

    public int getId_loja_que_trabalha() {
        return id_loja_que_trabalha;
    }

    public void setId_loja_que_trabalha(int id_loja_que_trabalha) {
        this.id_loja_que_trabalha = id_loja_que_trabalha;
    }
    
    
}
