package br.edu.infnet.produtos;

public class Produto {

    int cod;
    String nome;
    String fabricante;
    String descricao;
    String classificacao;

    public Produto() {

    }

    public Produto(int cod, String nome, String fabricante, String descricao, String classificacao) {
        this.cod = cod;
        this.nome = nome;
        this.fabricante = fabricante;
        this.descricao = descricao;
        this.classificacao = classificacao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
