package aula03.L;

public class Quadrado extends Retangulo {

    public Quadrado() {
    }

    public Quadrado(int tamanhos) {
        largura = altura = tamanhos;
    }

    @Override
    public void setLargura(int largura) {
        super.setLargura(largura);
        super.setAltura(largura);
    }

    @Override
    public void setAltura(int altura) {
        super.setAltura(altura);
        super.setLargura(altura);
    }
}
