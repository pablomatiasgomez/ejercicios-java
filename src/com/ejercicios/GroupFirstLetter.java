package src.com.ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// ejercicio:
// Agrupar por todas las palabras que empiezan con la misma letra
// en el 1 podes hacerlo a mano, y en el 2 intentar hacerlo con el stream de java
public class GroupFirstLetter {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hola", "chau", "bien", "mal", "como", "cual", "hijo", "hija");

        Map<Character, List<String>> expectedResult = new HashMap<>();
        expectedResult.put(new Character('h'), Arrays.asList("hola", "hijo", "hija"));
        expectedResult.put(new Character('c'), Arrays.asList("chau", "como", "cual"));
        expectedResult.put(new Character('b'), Arrays.asList("bien"));
        expectedResult.put(new Character('m'), Arrays.asList("mal"));

        Map<Character, List<String>> result = groupByFirstLetter1(words);

        if (result.equals(expectedResult)) {
            System.out.println("OK!");
        } else {
            System.out.println("Mal :(");
            System.out.println("Esperado: " + expectedResult.toString());
            System.out.println("Obtenido: " + result.toString());
        }
    }

	// A mano
	private static Map<Character, List<String>> groupByFirstLetter1(List<String> words) {
		Map<Character, List<String>> collectMap = new HashMap<>();
		words.forEach(word -> {
			Character firstLetter = word.charAt(0);
			List<String> equals = collectMap.get(firstLetter);
			if(equals!=null){
				equals.add(word);
			}else{
				equals = new ArrayList<>();
				equals.add(word);
			}
			collectMap.put(firstLetter, equals);
		});
		return collectMap;

	}

	// Stream Api
	private static Map<Character, List<String>> groupByFirstLetter2(List<String> words) {
    	return words.stream().collect(Collectors.groupingBy(GroupFirstLetter::getFirstLetter));
    }

	private static Character getFirstLetter(String str) {
		return str.charAt(0);
	}
}
