package aula03.L;

public class Main {

    static void getAreaTest(Retangulo  retangulo) {
        int largura = retangulo.getLargura();
        retangulo.setAltura(10);
        System.out.println("Area esperada de : " + (largura * 10) +
                " obteve " + retangulo.calcularArea());
    }

    public static void main(String[] args) {

        Retangulo retangulo = new Retangulo(2, 3);
        getAreaTest(retangulo);

        Retangulo retangulo1 = new Quadrado();
        retangulo1.setLargura(5);
        getAreaTest(retangulo1);

    }
}
