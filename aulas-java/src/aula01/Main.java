package aula01;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    // primitivos
    int a = 10;
    char b = 'a';
    double c = 10.5;
    boolean d = true;
    byte e = 10;
    short f = 10;
    float  g = 10.5f;
    long h = 1000000000;



    // no primitivos
    private Integer i = 10;
    private BigDecimal  j = new BigDecimal(10.5);
    private String k = "Andryev Lemes";
    private Boolean aBoolean  = true;
    private Byte aByte = 10;
    private Short aShort = 10;
    private Float aFloat = 10.5f;
    private Long aLong = 1000000000L;



    public static void main(String[] args) {



        //Documento documento = new Documento("Imposto De Renda");
       // Documento documento = new Documento();
        Contrato documento = new Contrato();
        documento.setNome("Imposto de Renda");
        documento.setTipoDocumento("PDF");

        List<String> assinaturas = new ArrayList<>();
        assinaturas.add("Andryev Lemes");
        assinaturas.add("Fulano de Tal");
        assinaturas.add("Ciclano de Tal");
        documento.setAssinaturas(assinaturas);

        Map<Integer, String> testemunhas = Map.of(1, "Testemunha 1", 2, "Testemunha 2");

        Map<Integer, String> testemunhas2 = new HashMap<>();
        testemunhas2.put(1, "Testemunha 1");
        testemunhas2.put(2, "Testemunha 2");

        documento.setTestemunhas(testemunhas2);

        System.out.println(documento.getNome());
        System.out.println(documento.getTipoDocumento());


        for (int i = 0; i < documento.getAssinaturas().size(); i++) {
            System.out.println(documento.getAssinaturas().get(i));
        }

//        documento.getAssinaturas().forEach(item -> {
//            System.out.println(item);
//        });


        //System.out.println(documento.getAssinaturas());


        for (Map.Entry<Integer, String> entry : documento.getTestemunhas().entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}