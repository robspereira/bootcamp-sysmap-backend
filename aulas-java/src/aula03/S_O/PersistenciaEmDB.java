package aula03.S_O;

public class PersistenciaEmDB implements PersistenciaDaFatura {

    @Override
    public void salvar(Fatura fatura) {
        // ---> codigo que salva em banco de dados
        System.out.println("Salvando fatura em banco de dados...");
    }
}
