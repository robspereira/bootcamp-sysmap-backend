package aula03.I;

public interface EstacionamentoPago  extends Estacionamento {

    double calcularTaxa(Carro carro);
    void pagar(Carro carro) throws Exception;

}
