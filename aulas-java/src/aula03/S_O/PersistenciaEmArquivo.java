package aula03.S_O;

public class PersistenciaEmArquivo implements PersistenciaDaFatura {

    @Override
    public void salvar(Fatura fatura) {
        // ---> codigo que salva em arquivo
        System.out.println("Salvando fatura em arquivo...");
    }
}
