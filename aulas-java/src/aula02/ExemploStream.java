package aula02;

import java.util.List;
import java.util.stream.Stream;

public class ExemploStream {

    public static void main(String[] args) {

        List<String> list = List.of("Andryev Lemes", "Carlos Lemes2", "Ciclano de Tal", "Fulano de Tal");
        System.out.println("1" + list);

        List<String> listFilteredNames = list.stream()
                .filter(item -> item.startsWith("C"))
                .toList();

        Stream<String> listFilteredNamesStream = list.stream()
                .filter(item -> item.startsWith("C"));

        Stream<String> test =  listFilteredNamesStream.filter(item -> item.endsWith("2"));

        test.forEach(System.out::println);



//        System.out.println(listFilteredNames);

    }
}
