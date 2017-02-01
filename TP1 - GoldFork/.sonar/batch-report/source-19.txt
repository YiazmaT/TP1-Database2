package ClassesNotas;

public class NotaDeCompra {
    private int id_nota;
    private String data;
    float valor_total;
    private int cod_fornecedor;
    private int cod_lanchonete;

    public NotaDeCompra(int id_nota, String data, float valor_total, int cod_fornecedor, int cod_lanchonete) {
        this.id_nota = id_nota;
        this.data = data;
        this.valor_total = valor_total;
        this.cod_fornecedor = cod_fornecedor;
        this.cod_lanchonete = cod_lanchonete;
    }
    
    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public int getCod_fornecedor() {
        return cod_fornecedor;
    }

    public void setCod_fornecedor(int cod_fornecedor) {
        this.cod_fornecedor = cod_fornecedor;
    }

    public int getCod_lanchonete() {
        return cod_lanchonete;
    }

    public void setCod_lanchonete(int cod_lanchonete) {
        this.cod_lanchonete = cod_lanchonete;
    }
    
    
}
