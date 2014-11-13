package pgrela.eulerproblem.problem93;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

public class ArithmeticExpressions implements EulerSolver {

    public static final char MINUS = '-';
    public static final char PLUS = '+';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';

    public static void main(String[] args) {
        printSolution(ArithmeticExpressions.class);
    }

    public long solve() {
        List<String> tripleOperators = new ArrayList<>();
        char[] operators = new char[]{MINUS, PLUS, MULTIPLY, DIVIDE};
        for (char operator1 : operators) {
            for (char operator2 : operators) {
                for (char operator3 : operators) {
                    tripleOperators.add("" + operator1 + operator2 + operator3);
                }
            }
        }
        Map<Integer, Set<Integer>> present = new HashMap<>();
        IntStream.range(1000, 10000)
                .mapToObj(Integers::toDigitArray)
                .filter(this::isUnique)
                .filter(this::doesNotContainZero)
                .map(Integers::fromDigitArray)
                .forEach(digits -> {
                    int signature = signature(digits);
                    if (!present.containsKey(signature)) present.put(signature, new HashSet<>());
                    tripleOperators.stream()
                            .flatMap(threeOperators -> Stream.of(digits + threeOperators,
                                    digits.toString().substring(0, 2)
                                            + threeOperators.charAt(0)
                                            + digits.toString().substring(2)
                                            + threeOperators.substring(1)))
                            .mapToDouble(this::processONP)
                            .filter(d -> d > 0)
                            .filter(this::isInt)
                            .mapToInt(d -> (int) (d + 0.00001))
                            .forEach(i -> present.get(signature(digits)).add(i));
                });
        int max=0;
        int maxI=0;
        for(Map.Entry<Integer,Set<Integer>> entry:present.entrySet()){
            int i=1;
            while(entry.getValue().contains(i))++i;
            if(maxI<i){
                max=entry.getKey();
                maxI=i;
            }
        }
        return max;
    }

    private boolean doesNotContainZero(int[] array) {
        for (int anInt : array) {
            if(anInt==0)return false;
        }
        return true;
    }

    private int signature(Integer number) {
        int[] digits = Integers.toDigitArray(number);
        Arrays.sort(digits);
        return Integers.fromDigitArray(digits);
    }

    private boolean isInt(double value) {
        return value + 0.000001 - ((int) value) < 0.00001;
    }

    private boolean isUnique(int[] array) {
        return IntStream.of(array).distinct().count() == array.length;
    }

    private double processONP(String onp) {
        Stack<Double> stack = new Stack<>();
        for (char a : onp.toCharArray()) {
            switch (a) {
                case PLUS:
                    stack.add(stack.pop() + stack.pop());
                    break;
                case MINUS:
                    stack.add(stack.pop() - stack.pop());
                    break;
                case DIVIDE:
                    stack.add(stack.pop() / stack.pop());
                    break;
                case MULTIPLY:
                    stack.add(stack.pop() * stack.pop());
                    break;
                default:
                    stack.add((double) a - '0');
            }
        }
        return stack.pop();
    }
}
