package aula03.S_O;

public class Livro {

    private String nome;
    private String nomeAutor;
    private Integer ano;
    private Integer preco;

    public Livro(String nome, String nomeAutor, Integer ano, Integer preco) {
        this.nome = nome;
        this.nomeAutor = nomeAutor;
        this.ano = ano;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }
}
