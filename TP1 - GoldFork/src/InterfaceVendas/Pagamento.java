/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceVendas;

/**
 *
 * @author YiazmaT
 */
public class Pagamento {
    private float valor;
    private boolean cartao;
    private int ultimos4Digitos;
    private String bandeira;
    private String tipo;

    public Pagamento(float valor, boolean cartao, int ultimos4Digitos, String bandeira, String tipo) {
        this.valor = valor;
        this.cartao = cartao;
        this.ultimos4Digitos = ultimos4Digitos;
        this.bandeira = bandeira;
        this.tipo = tipo;
    }

    public Pagamento(float valor, boolean cartao) {
        this.valor = valor;
        this.cartao = cartao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isCartao() {
        return cartao;
    }

    public void setCartao(boolean cartao) {
        this.cartao = cartao;
    }

    public int getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public void setUltimos4Digitos(int ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
