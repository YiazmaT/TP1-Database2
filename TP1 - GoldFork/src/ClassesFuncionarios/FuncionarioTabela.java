package ClassesFuncionarios;

public class FuncionarioTabela {
    private int id;
    private String tipo;
    private String nome;

    public FuncionarioTabela(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public FuncionarioTabela(int id, String tipo, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
