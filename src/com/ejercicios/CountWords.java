package com.ejercicios;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// EJEMPLO: contar palabras...
// Dado un array de strings, hay que contar cuantas veces aparece cada uno.
// Aca te muestro dos maneras de resolverlo: 1)a mano 2) con Stream
public class CountWords {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hola", "chau", "bien", "mal", "chau", "chau", "bien", "chau", "bien", "hola");

        Map<String, Long> expectedResult = new HashMap<>();
        expectedResult.put("hola", 2L);
        expectedResult.put("bien", 3L);
        expectedResult.put("chau", 4L);
        expectedResult.put("mal", 1L);

        Map<String, Long> result = countWords1(words);

        if (result.equals(expectedResult)) {
            System.out.println("OK!");
        } else {
            System.out.println("Mal :(");
            System.out.println("Esperado: " + expectedResult.toString());
            System.out.println("Obtenido: " + result.toString());
        }
    }

    // Esta es una solucion, haciendolo a mano, recorriendo todos y sumando de a 1
    private static Map<String, Long> countWords1(List<String> words) {
        Map<String, Long> countMap = new HashMap<>();
        words.forEach(word -> {
            Long count = countMap.get(word);
            count = count != null ? count + 1 : 1;
            countMap.put(word, count);
        });
        return countMap;
    }

    // Esta es otra solucion, usando los Stream de java, haciendo collect agrupado y couting para que sume de 1 por cada matcheo
    private static Map<String, Long> countWords2(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(a -> a, Collectors.counting()));
    }

}
