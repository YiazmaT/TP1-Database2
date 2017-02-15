package ClassesLojas;

public class Fornecedor {
    private int id_fornecedor;
    private String nome;
    private String telefone;
    private String cnpj;
    private boolean ativo;

    public Fornecedor(int id_fornecedor, String nome, String telefone, String cnpj, boolean ativo) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.ativo = ativo;
    }

    public Fornecedor(int id_fornecedor, String nome) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
    }
    
    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
