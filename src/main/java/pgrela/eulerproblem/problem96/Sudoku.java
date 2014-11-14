package pgrela.eulerproblem.problem96;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;

public class Sudoku implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem96/sudoku.txt";

    public static void main(String[] args) {
        printSolution(Sudoku.class);
    }

    public long solve() {
        return Stream.of(Files.getFileAsString(RESOURCE_FILE).split("Grid [0-9]{2}"))
                .filter(s->!s.isEmpty()).skip(1)
                .map(this::to2DArray)
                .map(this::solveSudoku)
                .mapToInt(a->a[0][6]*100+a[0][7]*10+a[0][8])
                .sum();
    }

    private int[][] solveSudoku(int[][] sudoku) {
        Collection<Integer> from1To9 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Set<Integer>>> options = new ArrayList<>();
        int[][] solutions = new int[9][9];
        for (int i = 0; i < sudoku.length; i++) {
            int[] row = sudoku[i];
            options.add(new ArrayList<>());
            for (int j = 0; j < row.length; j++) {
                options.get(i).add(new HashSet<>());
                solutions[i][j]=row[j];
                if(row[j]==0){
                    options.get(i).get(j).addAll(from1To9);
                }else{
                    options.get(i).get(j).add(row[j]);
                }
            }
        }
        boolean improved=true;
        while(improved){
            improved=false;
            for (int i = 0; i < options.size(); i++) {
                for (int j = 0; j < options.get(i).size(); j++) {
                    if(options.get(i).get(j).size()==1){
                        int value = options.get(i).get(j).iterator().next();
                        solutions[i][j]=value;
                        int smallSquareFirstRow = i/3*3;
                        int smallSquareFirstColumn = j/3*3;
                        for (int k = 0; k < 9; k++) {
                            if(i!=k) {
                                improved |= options.get(k).get(j).remove(value);
                            }
                            if(j!=k) {
                                improved |= options.get(i).get(k).remove(value);
                            }
                        }
                        for (int k = smallSquareFirstRow; k < smallSquareFirstRow+3; k++) {
                            for (int l = smallSquareFirstColumn; l < smallSquareFirstColumn+3; l++) {
                                if(k!=i||l!=j){
                                    improved |= options.get(k).get(l).remove(value);
                                }
                            }
                        }
                    }
                }
            }
            //isSingularInRow
            for (int i = 0; i < 9; i++) {
                for (int digit = 1; digit < 10; digit++) {
                    int occurences = 0;
                    for (int j = 0; j < 9; j++) {
                        if(options.get(i).get(j).contains(digit)) {
                            occurences++;
                        }
                    }
                    if(occurences==1){
                        for (int j = 0; j < 9; j++) {
                            if(options.get(i).get(j).contains(digit) && options.get(i).get(j).size()!=1) {
                                improved=true;
                                options.get(i).get(j).clear();
                                options.get(i).get(j).add(digit);
                                solutions[i][j]=digit;
                            }
                        }
                    }
                }
            }
            //isSingularInColumn
            for (int i = 0; i < 9; i++) {
                for (int digit = 1; digit < 10; digit++) {
                    int occurences = 0;
                    for (int j = 0; j < 9; j++) {
                        if(options.get(j).get(i).contains(digit)) {
                            occurences++;
                        }
                    }
                    if(occurences==1){
                        for (int j = 0; j < 9; j++) {
                            if(options.get(j).get(i).contains(digit) && options.get(j).get(i).size()!=1) {
                                improved=true;
                                options.get(j).get(i).clear();
                                options.get(j).get(i).add(digit);
                                solutions[i][j]=digit;
                            }
                        }
                    }
                }
            }
            //isSingularInSmallSquare
            for (int i = 0; i < 9; i+=3) {
                for (int j = 0; j < 9; j+=3) {
                    for (int digit = 1; digit < 10; digit++) {
                        int occurences = 0;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if(options.get(i+k).get(j+l).contains(digit)) {
                                    occurences++;
                                }
                            }
                        }
                        if(occurences==1){
                            for (int k = 0; k < 3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    if(options.get(i+k).get(j+l).contains(digit) && options.get(i+k).get(j+l).size()!=1) {
                                        improved=true;
                                        options.get(i+k).get(j+l).clear();
                                        options.get(i+k).get(j+l).add(digit);
                                        solutions[i+k][j+l]=digit;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return solutions;
    }

    private int[][] to2DArray(String sudoku) {
        return Stream.of(sudoku.split("\n"))
                .filter(s -> !s.isEmpty())
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .toArray(int[][]::new);
    }
}
