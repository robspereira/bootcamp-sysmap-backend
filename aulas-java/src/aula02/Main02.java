package aula02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Main02 {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Andryev Lemes");

        Set<String> set = Set.of("Andryev Lemes", "Andryev Lemes2", "Ciclano de Tal");
        for (String s : set) {
            //System.out.println(s);
        }

        Queue<String> queue = new ArrayDeque<>();

        queue.add("Andryev Lemes");
        queue.add("Andryev Lemes2");
        queue.add("Ciclano de Tal");

        System.out.println(queue);

        //String element = queue.peek();
        System.out.println("Chamou : " + queue.poll());

        System.out.println(queue);

        System.out.println("Chamou : " + queue.poll());

        System.out.println(queue);

        System.out.println("Chamou : " + queue.poll());

        System.out.println(queue);

        System.out.println("Chamou : " + queue.poll());


        Stack p = new Stack();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
           System.out.println("Inserindo na pilha : " + p.push(random.nextInt(100)));
        }
        System.out.println("");
        System.out.println("");
        System.out.println("INICIO/FIM");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < 10; i++) {
            System.out.println("Removendo da pilha : " + p.pop());
        }

    }

}
