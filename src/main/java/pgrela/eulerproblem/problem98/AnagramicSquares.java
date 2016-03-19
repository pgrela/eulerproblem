package pgrela.eulerproblem.problem98;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.of;
import static pgrela.eulerproblem.common.Files.getFileAsString;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AnagramicSquares implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem98/words.txt";

    public static void main(String[] args) {
        printSolution(AnagramicSquares.class);
    }

    public long solve() {
        String[] words = getFileAsString(RESOURCE_FILE).replace("\"", "").split(",");
        Map<String, Set<String>> wordsAnagramGroups = of(words)
                .collect(groupingBy(this::sortCharacters, HashMap::new, mapping(i -> i, toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int maxLength = wordsAnagramGroups.keySet().stream().parallel().mapToInt(String::length).max().getAsInt();

        int maxNumber = Maths.intSqrt(Maths.pow(10, 1 + maxLength));
        String[] squaresAsStrings = IntStream.rangeClosed(1, maxNumber).map(i -> i * i).mapToObj(String::valueOf).toArray(String[]::new);

        Map<String, Set<String>> squaresByOrderSig = of(squaresAsStrings)
                .collect(groupingBy(this::orderSig, HashMap::new, mapping(i -> i, toSet())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        Set<String> anagramSquares = new HashSet<>();
        wordsAnagramGroups.values().stream().forEach(
                anagrams -> {
                    Map<String, String> substitutions = new HashMap<>();
                    anagrams.stream().forEach(
                            anagram -> {
                                String anagramOrderSig = orderSig(anagram);
                                Set<String> possibleSquares = squaresByOrderSig.get(anagramOrderSig);
                                possibleSquares.stream().forEach(square -> {
                                    String substitution = encodeSubstitution(anagram, square);
                                    if(substitutions.containsKey(substitution)){
                                        anagramSquares.add(square);
                                        anagramSquares.add(substitutions.get(substitution));
                                    }
                                    substitutions.put(substitution, square);
                                });
                            }
                    );
                }
        );


        return anagramSquares.stream().mapToInt(Integer::valueOf).max().getAsInt();
    }

    private String encodeSubstitution(String anagram, String square) {
        SortedSet<String> pairs = new TreeSet<>();
        char[] digits = square.toCharArray();
        char[] letters = anagram.toCharArray();
        for (int i = 0; i < square.length(); i++) {
            pairs.add("" + letters[i] + digits[i]);
        }
        return pairs.stream().reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append).toString();
    }

    private String sortCharacters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    private String orderSig(String word) {
        Map<Character, Character> map = new HashMap<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], (char) ('0' + map.size()));
            }
            chars[i] = map.get(chars[i]);
        }
        return String.valueOf(chars);
    }


}
