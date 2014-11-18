package pgrela.eulerproblem.problem96;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Sudoku implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem96/sudoku.txt";

    public static void main(String[] args) {
        printSolution(Sudoku.class);
    }

    public long solve() {
        return Stream.of(Files.getFileAsString(RESOURCE_FILE).split("Grid [0-9]{2}"))
                .filter(s -> !s.isEmpty())
                .map(this::to2DArray)
                .map(this::solveSudoku)
                //.peek(a -> Stream.of(a).peek(s->System.out.println()).forEach(v -> IntStream.of(v).forEach(System.out::print)))
                .mapToInt(a -> a[0][0] * 100 + a[0][1] * 10 + a[0][2])
                .sum();
    }

    private int[][] solveSudoku(int[][] sudoku) {
        Board board = new Board(9, sudoku);
        if (board.solve()) {
            int[][] solution = board.getSolution();
            if (board.validateSolution(solution))
                return solution;
        }

        throw new RuntimeException("SHIT!");
    }

    private int[][] to2DArray(String sudoku) {
        return Stream.of(sudoku.split("\r\n"))
                .filter(s -> !s.isEmpty())
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .toArray(int[][]::new);
    }

    public class Field {
        final Board board;
        int value;
        boolean[] options;
        int possibleOptions;
        private Collection<Group> groups = new ArrayList<>();

        public Field(Board board, int row, int column) {
            this.board = board;
            possibleOptions = board.getSize();
            options = new boolean[possibleOptions + 1];
            Arrays.fill(options, true);
        }

        public int getValue() {
            return value;
        }

        public void assign(Group group) {
            groups.add(group);
        }

        public void removeOption(int option) {
            if (options[option]) {
                possibleOptions--;
                options[option] = false;
                if (possibleOptions == 1) {
                    for (int possibleValue = 1; possibleValue <= board.getSize(); possibleValue++) {
                        if (options[possibleValue]) {
                            fixValue(possibleValue);
                            break;
                        }
                    }
                }
            }
        }

        private void fixValue(int value) {
            possibleOptions=1;
            if(this.value!=0 && this.value!=value){throw new RuntimeException();}
            this.value = value;
            board.markAsFixed(this);
        }

        public void removeOptionFromGroups() {
            for (Group group : groups) {
                group.fixField(this);
            }
        }

        public boolean hasOption(int option) {
            return options[option];
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private class Group {
        Set<Field> optionalFields = new HashSet<>();

        public void fixField(Field aField) {
            optionalFields.remove(aField);
            for (Field field : optionalFields) {
                field.removeOption(aField.getValue());
            }
        }

        public void addField(Field field) {
            optionalFields.add(field);
            field.assign(this);
        }
    }

    public class Board {
        final int size;
        Field[][] fields;
        private Set<Field> fixedFields = new HashSet<>();
        private Set<Field> optionFields = new HashSet<>();
        int optionalFields;
        int[][] solution = null;

        public Board(int size, int[][] setup) {
            this.size = size;
            optionalFields = size * size;
            fields = new Field[size][size];
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    fields[row][column] = new Field(this, row, column);
                    optionFields.add(fields[row][column]);
                }
            }
            for (int row = 0; row < size; row++) {
                Group rowGroup = new Group();
                for (int column = 0; column < size; column++) {
                    rowGroup.addField(fields[row][column]);
                }
            }
            for (int column = 0; column < size; column++) {
                Group columnGroup = new Group();
                for (int row = 0; row < size; row++) {
                    columnGroup.addField(fields[row][column]);
                }
            }
            int squares = 3;
            for (int i = 0; i < size; i += squares) {
                for (int j = 0; j < size; j += squares) {
                    Group squareGroup = new Group();
                    for (int k = 0; k < squares; k++) {
                        for (int l = 0; l < squares; l++) {
                            squareGroup.addField(fields[i + k][j + l]);
                        }
                    }
                }
            }
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    if (setup[row][column] != 0) {
                        fields[row][column].fixValue(setup[row][column]);
                    }
                }
            }
        }

        public int getSize() {
            return size;
        }

        public void markAsFixed(Field field) {
            --optionalFields;
            optionFields.remove(field);
            fixedFields.add(field);
        }

        public boolean solve() {
            while (!fixedFields.isEmpty()) {
                Field field = fixedFields.iterator().next();
                fixedFields.remove(field);
                field.removeOptionFromGroups();
            }
            if (optionalFields > 0 && !validateSolution(getSolution())) {
                return makeAGuess();
            }
            if (!validateSolution(getSolution())) return false;
            return true;
        }

        private boolean makeAGuess() {
            if (optionFields.isEmpty()) {
                return false;
            }
            Field guessingField = optionFields.iterator().next();
            for (int option = 1; option <= size; option++) {
                if (guessingField.hasOption(option)) {
                    guessingField.setValue(option);
                    Board newBoard = new Board(size, getSolution());
                    if (newBoard.solve()) {
                        int[][] solution = newBoard.getSolution();
                        if (validateSolution(solution)) {
                            this.solution = solution;
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean validateSolution(int[][] solution) {
            if (!Stream.of(solution).allMatch(a -> IntStream.of(a).filter(i -> i > 0).distinct().count() == 9))
                return false;
            for (int i = 0; i < size; i++) {
                int[] a = new int[size];
                for (int j = 0; j < size; j++) {
                    a[j] = solution[j][i];
                }
                if (IntStream.of(a).filter(t -> t > 0).distinct().count() != 9) return false;
            }
            int squares = 3;
            for (int i = 0; i < size; i += squares) {
                for (int j = 0; j < size; j += squares) {
                    ArrayList<Integer> a = new ArrayList<>();
                    for (int k = 0; k < squares; k++) {
                        for (int l = 0; l < squares; l++) {
                            a.add(solution[i + k][j + l]);
                        }
                    }
                    if (a.stream().filter(t -> t > 0).distinct().count() != 9) return false;
                }
            }
            return true;
        }

        public int[][] getSolution() {
            if (solution != null) {
                return solution;
            }
            int[][] solution = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    solution[i][j] = fields[i][j].getValue();
                }
            }
            return solution;
        }
    }
}
