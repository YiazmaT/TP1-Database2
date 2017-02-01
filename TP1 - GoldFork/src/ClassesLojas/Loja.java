package ClassesLojas;

public class Loja {
    private int id_lanchonete;
    private String telefone;
    private String nome;
    private String cnpj;
    private String endereco_cidade;
    private String endereco_bairro;
    private String endereco_estado;
    private String endereco_rua;
    private String endereco_cep;
    private int endereco_numero;

    public Loja(int id_lanchonete, String telefone, String nome, String endereco_cidade, String endereco_bairro, String endereco_estado, String endereco_rua, String endereco_cep, int endereco_numero, String cnpj) {
        this.id_lanchonete = id_lanchonete;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco_cidade = endereco_cidade;
        this.endereco_bairro = endereco_bairro;
        this.endereco_estado = endereco_estado;
        this.endereco_rua = endereco_rua;
        this.endereco_cep = endereco_cep;
        this.endereco_numero = endereco_numero;
        this.cnpj = cnpj;
    }
    
    public Loja(int id_lanchonete, String nome) {
        this.id_lanchonete = id_lanchonete;
        this.nome = nome;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public int getId_lanchonete() {
        return id_lanchonete;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }
    
    public String getEndereco_cidade() {
        return endereco_cidade;
    }

    public String getEndereco_bairro() {
        return endereco_bairro;
    }

    public String getEndereco_estado() {
        return endereco_estado;
    }

    public String getEndereco_rua() {
        return endereco_rua;
    }

    public String getEndereco_cep() {
        return endereco_cep;
    }

    public int getEndereco_numero() {
        return endereco_numero;
    }

    public void setId_lanchonete(int id_lanchonete) {
        this.id_lanchonete = id_lanchonete;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco_cidade(String endereco_cidade) {
        this.endereco_cidade = endereco_cidade;
    }

    public void setEndereco_bairro(String endereco_bairro) {
        this.endereco_bairro = endereco_bairro;
    }

    public void setEndereco_estado(String endereco_estado) {
        this.endereco_estado = endereco_estado;
    }

    public void setEndereco_rua(String endereco_rua) {
        this.endereco_rua = endereco_rua;
    }

    public void setEndereco_cep(String endereco_cep) {
        this.endereco_cep = endereco_cep;
    }

    public void setEndereco_numero(int endereco_numero) {
        this.endereco_numero = endereco_numero;
    }
    
    
}
