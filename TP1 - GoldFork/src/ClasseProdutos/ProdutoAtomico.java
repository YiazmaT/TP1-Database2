package ClasseProdutos;

public class ProdutoAtomico {
    private int idProdutoAtomico;
    private String unidade;
    private String nome;
    private float quantidade;
    private float valorUnitario;

    public ProdutoAtomico(int idProdutoAtomico, String unidade, String nome) {
        this.idProdutoAtomico = idProdutoAtomico;
        this.unidade = unidade;
        this.nome = nome;
        this.quantidade = 0.0f;
        this.valorUnitario = 0.0f;
    }

    public ProdutoAtomico(int idProdutoAtomico, String unidade, String nome, float quantidade) {
        this.idProdutoAtomico = idProdutoAtomico;
        this.unidade = unidade;
        this.nome = nome;
        this.quantidade = quantidade;
    }
    
    
    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProdutoAtomico() {
        return idProdutoAtomico;
    }

    public void setIdProdutoAtomico(int idProdutoAtomico) {
        this.idProdutoAtomico = idProdutoAtomico;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
