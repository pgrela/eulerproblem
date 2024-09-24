package pgrela.eulerproblem.problem198;

import pgrela.eulerproblem.common.ArbitraryPair;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Maths;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AmbiguousNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(AmbiguousNumbers.class);
    }

    @Override
    public long solve() {
        int limit = 1000000;
        boolean added = true;
        int denominator = 1;
        TreeSet<LongFraction> used = new TreeSet<>();
        Set<LongFraction> uniques = new HashSet<>();
        Set<LongFraction> gens = new HashSet<>();
        while(added){
            added = false;
            for (int nominator = 0; nominator <= denominator; nominator++) {
                used.add(new LongFraction(nominator, denominator));
            }
            for (int nominator = 0; nominator <= denominator; nominator++) {
                LongFraction fraction = new LongFraction(nominator, denominator);
                if(fraction.getDenominator() != denominator)continue;
                LongFraction leftFraction = used.lower(fraction);
                if(leftFraction!=null){
                    LongFraction leftGap = fraction.substract(leftFraction);
                    int leftDenominator = denominator + 1;
                    while(denominator*leftDenominator*2<=limit) {
                        LongFraction maybeCloser = new LongFraction(fraction.getNominator() * leftDenominator / fraction.getDenominator(), leftDenominator);
                        if(maybeCloser.equals(fraction))
                            maybeCloser = new LongFraction(maybeCloser.getNominator()*leftDenominator/ maybeCloser.getDenominator()-1, leftDenominator);
                        LongFraction maybeGap = fraction.substract(maybeCloser);
                        if (maybeGap.isLessThan(leftGap)) {
                            uniques.add(fraction.substract(maybeGap.divide(2)));
                            added = true;
                            leftGap = maybeGap;
                            gens.add(fraction);gens.add(maybeCloser);
                        }
                        ++leftDenominator;
                    }
                }
                LongFraction rightFraction = used.higher(fraction);
                if(rightFraction!=null){
                    LongFraction rightGap = rightFraction.substract(fraction);
                    int rightDenominator = denominator + 1;
                    while(denominator*rightDenominator*2<=limit) {
                        LongFraction maybeCloser = new LongFraction((fraction.getNominator() * rightDenominator) / fraction.getDenominator() + 1, rightDenominator);
//                        if(maybeCloser.equals(fraction))
//                            maybeCloser = new LongFraction(maybeCloser.getNominator()+1, maybeCloser.getDenominator());
                        LongFraction maybeGap = maybeCloser.substract(fraction);
                        if (maybeGap.isLessThan(rightGap)) {
                            uniques.add(fraction.add(maybeGap.divide(2)));
                            rightGap = maybeGap;
                            added = true;
                            gens.add(fraction);gens.add(maybeCloser);
                        }
                        ++rightDenominator;
                    }
                }
            }
            ++denominator;
        }
        if(true)return uniques.size();
        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        Set<LongFraction> unique = new HashSet<>();
        TreeSet<LongFraction> fractions = new TreeSet<>();
        fractions.add(LongFraction.ZERO);
        fractions.add(LongFraction.ONE);
        int i1 = 100;
        Map<LongFraction, Set<ArbitraryPair<LongFraction>>> generators = new HashMap<>();
        Set<ArbitraryPair<Long>> pairs = new HashSet<>();
        for (int n = 1; n <= i1; n++) {
            for (int d = n + 1; d <= i1; d++) {
                if (Maths.gcd(n, d) != 1) {
                    image.setRGB(n, d, Color.RED.getRGB());
                    continue;
                }
                LongFraction fraction = new LongFraction(n, d);
                fractions.add(fraction);
                for (int bound = 1; bound < d; bound++) {
                    int theBound = bound;
                    LongFraction right = fractions.tailSet(fraction, false)
                            .stream()
                            .filter(f -> f.getDenominator() <= theBound)
                            .findFirst().get();
                    LongFraction left = fractions.headSet(fraction, false)
                            .descendingSet()
                            .stream()
                            .filter(f -> f.getDenominator() <= theBound)
                            .findFirst().get();
                    if (right.add(left).divide(2).equals(fraction)) {
                        ArbitraryPair<Long> a = new ArbitraryPair<>(left.getDenominator(), right.getDenominator());
                        ArbitraryPair<Long> b = new ArbitraryPair<>(right.getDenominator(), left.getDenominator());
                        if(!pairs.contains(b) && !pairs.contains(a)) {
                            pairs.add(a);
                        }
                        //System.out.printf("%s is between %s and %s for bound %d\n", fraction, left, right, bound)
                        Set<ArbitraryPair<LongFraction>> pairsSet = generators.computeIfAbsent(fraction, f -> new HashSet<>());
                        if(!pairsSet.contains(new ArbitraryPair<>(right, left)) && !pairsSet.contains(new ArbitraryPair<>(left, right)))
                            pairsSet.add(new ArbitraryPair<>(right, left));
                        unique.add(fraction);
                    }
                }
            }
        }
        if(false){
        for (int i = 0; i < 1000; i++) {
            image.setRGB(i, i, Color.CYAN.getRGB());
        }
        File outputfile = new File("image.png");
        for (LongFraction fraction : unique) {
            //System.out.println(fraction);
            //image.setRGB((int) fraction.getNominator(), (int) fraction.getDenominator(), Color.WHITE.getRGB());
        }
        for (LongFraction fraction : gens) {
            image.setRGB((int) fraction.getNominator(), (int) fraction.getDenominator(), Color.MAGENTA.getRGB());
        }
        generators.values().stream().map(Set::iterator).map(Iterator::next).flatMap(p-> Stream.of(p.a,p.b)).forEach(
                fraction->image.setRGB((int) fraction.getNominator(), (int) fraction.getDenominator(), Color.WHITE.getRGB())
        );
        generators.values().stream().map(Set::iterator).map(Iterator::next).flatMap(p-> Stream.of(p.a,p.b)).forEach(System.out::println);

        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
        return 0;
    }

}

