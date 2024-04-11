package aula03.S_O;

public class ImpressaoDeFatura extends Impressora {

    private Fatura fatura;

    public ImpressaoDeFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public void imprimirFatura() {
        System.out.println("Nome do livro: " + fatura.getLivro().getNome());
        System.out.println("Nome do autor: " + fatura.getLivro().getNomeAutor());
        System.out.println("Ano: " + fatura.getLivro().getAno());
        System.out.println("Preço: " + fatura.getLivro().getPreco());
        System.out.println("Quantidade: " + fatura.getQuantidade());
        System.out.println("Total: " + fatura.getTotal());
    }
}
