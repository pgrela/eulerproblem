package pgrela.eulerproblem.problem59;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.Files.getFileAsString;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Set;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;

public class XorDecryption implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem59/cipher.txt";

    public static void main(String[] args) {
        printSolution(XorDecryption.class);
    }

    public long solve() {
        Set<Integer> allowedChars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890- '`\".,!$%&*()_-[]';:?><"
                .chars()
                .boxed()
                .collect(toSet());

        int[] encrypted = Stream.of(getFileAsString(RESOURCE_FILE).split(","))
                .mapToInt(Integer::valueOf).toArray();

        int[] password = range(0, 3).map(passwordLetter ->
                        rangeClosed('a', 'z').filter(
                                letter -> range(0, encrypted.length).filter(i -> i % 3 == passwordLetter).map(
                                        i -> encrypted[i] ^ letter).allMatch(allowedChars::contains)
                        ).findFirst().getAsInt()
        ).toArray();

        of(password).forEach(i -> System.out.print((char) i));
        System.out.println("");

        for (int i = 0; i < encrypted.length; i++) {
            encrypted[i] ^= password[i % 3];
        }

        of(encrypted).forEach(i -> System.out.print((char) i));
        System.out.println("");
        return of(encrypted).sum();
    }
}
