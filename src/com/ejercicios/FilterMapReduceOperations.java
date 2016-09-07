package src.com.ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Dada una lista de Strings y una lista de Integers,
// filtrar la lista de Strings de modo que queden solo los que tienen longitud N tal que N está 
//         incluido en la lista de enteros
// luego mapear esa lista de strings a una lista de strings donde cada string es 
//         el mismo pero en mayuscula
// luego mapear esa lista de strings para convertirla en una lista de 
//         listas de Character (List<List<Character>>) tal que cada lista de Character 
//         represente los caracteres de cada string (getStringCharacters)
// luego mapear esa lista para hacer una lista de listas de Integer (List<List<Integer>>) 
//         donde cada numero representa el valor numerico del char (getCharValue)
// luego reducir cada lista "interna" sumando todos los numeros hasta obtener un numero 
//         y por ende quedar una List<Integer>
// luego a cada uno de estos numeros, restarle 400 (si re clavado je)
// por ultimo, reducir esta ultima lista, multiplicando todos y obtener un numero final

public class FilterMapReduceOperations {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("holaaa", "chau", "bien", "mal", "roberto", "camila");
		List<Integer> numbers = Arrays.asList(4, 6);

		Integer expectedResult = 6402924;

		Integer result = execute(words, numbers);

		if (result.equals(expectedResult)) {
			System.out.println("OK!");
		} else {
			System.out.println("Mal :(");
			System.out.println("Esperado: " + expectedResult.toString());
			System.out.println("Obtenido: " + result.toString());
		}
	}

	private static List<String> wordsLongN(List<String> words, List<Integer> numbers) {

		List<String> devolver = new ArrayList();

		words.forEach(word -> {
			Integer longi = word.length();
			boolean existe = numbers.contains(longi);
			if (existe) {
				devolver.add(word);
			}
		});

		return devolver;
	}

	private static List<String> toUpper(List<String> words) {

		List<String> devolver = new ArrayList();

		words.forEach(word -> {

			String grandota = word.toUpperCase();
			devolver.add(grandota);
		});

		return devolver;
	}

	private static List<Character> getStringCharacters(String str) {
		return str.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
	}

	private static List<List<Character>> letrasEnListas(List<String> words) {

		List<List<Character>> devolver = new ArrayList();
		words.forEach(word -> {

			List<Character> letritas = word.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
			devolver.add(letritas);
		});

		return devolver;

	}
	
	private static List<List<Integer>> numDeLetras(List<List<Character>> words) {

		List<List<Integer>> devolver = new ArrayList();
		words.forEach(word -> {

			List<Integer> letritas = word.stream().map(e -> getCharValue(e)).collect(Collectors.toList());
			devolver.add(letritas);
		});

		return devolver;

	}
	
	private static List<Integer> sumaNumeros(List<List<Integer>> numbers) {

		List<Integer> devolver = new ArrayList();
		numbers.forEach(number -> {

			Integer suma = number.stream().reduce(0, (a,b) -> a+b);
			devolver.add(suma);
		});

		return devolver;
	}
	
	private static List<Integer> restarNumeroLista(List<Integer> numbers, Integer aRestar) {

		List<Integer> devolver = new ArrayList();
		numbers.forEach(number -> {

			number = number-aRestar;
			devolver.add(number);
		});

		return devolver;
	}
	
	private static Integer numsMultiplicados(List<Integer> numbers) {

		Integer producto = numbers.stream().reduce(1, (a,b) -> a*b);

		return producto;
	}

	private static Integer execute(List<String> words, List<Integer> numbers) {

		List<String> longitudesFiltradas = wordsLongN(words, numbers);
		List<String> grandotas = toUpper(longitudesFiltradas);
		List<List<Character>> palabrasSeparadas = letrasEnListas(grandotas);
		List<List<Integer>> valoresLetras = numDeLetras(palabrasSeparadas);
		List<Integer> listasSumadas = sumaNumeros(valoresLetras);
		List<Integer> sumadosMasChicos = restarNumeroLista(listasSumadas, 400);
		Integer resultado = numsMultiplicados(sumadosMasChicos);

		return resultado;
	}

	private static Integer getCharValue(Character chr) {
		return (int) chr.charValue();
	}
}
