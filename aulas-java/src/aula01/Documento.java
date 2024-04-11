package aula01;

public class Documento {

    private String nome;
    private String tipoDocumento;


//    public Documento(String nome) {
//        if (nome == null || nome.isEmpty() || nome.isBlank()) {
//            this.nome = "Sem nome";
//        } else {
//            this.nome = nome;
//        }
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
