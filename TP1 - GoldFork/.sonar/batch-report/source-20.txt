package ClassesNotas;

public class NotaDeVenda {
    private int id_nota_venda;
    private String data;
    private float valor_total;
    private int cod_caixa;
    private int cod_lanchonete;

    public NotaDeVenda(int id_nota_venda, String data, float valor_total, int cod_caixa, int cod_lanchonete) {
        this.id_nota_venda = id_nota_venda;
        this.data = data;
        this.valor_total = valor_total;
        this.cod_caixa = cod_caixa;
        this.cod_lanchonete = cod_lanchonete;
    }

    
    public int getId_nota_venda() {
        return id_nota_venda;
    }

    public void setId_nota_venda(int id_nota_venda) {
        this.id_nota_venda = id_nota_venda;
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

    public int getCod_caixa() {
        return cod_caixa;
    }

    public void setCod_caixa(int cod_caixa) {
        this.cod_caixa = cod_caixa;
    }

    public int getCod_lanchonete() {
        return cod_lanchonete;
    }

    public void setCod_lanchonete(int cod_lanchonete) {
        this.cod_lanchonete = cod_lanchonete;
    }
    
    
}
