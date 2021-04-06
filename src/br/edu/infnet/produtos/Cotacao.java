package br.edu.infnet.produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cotacao {

    int cod;
    Produto produto;
    String fornecedor;
    LocalDate dtCotacao;
    int validade;
    int quantidade;
    BigDecimal valor;

    public Cotacao() {
    }

    public Cotacao(int cod, Produto produto, String fornecedor, LocalDate dtCotacao, int validade, int quantidade, BigDecimal valor) {
        this.cod = cod;
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.dtCotacao = dtCotacao;
        this.validade = validade;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getDtCotacao() {
        return dtCotacao;
    }

    public void setDtCotacao(LocalDate dtCotacao) {
        this.dtCotacao = dtCotacao;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
