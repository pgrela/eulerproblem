package pgrela.eulerproblem.problem101;

public class Equation {
    long[] parameters;

    public Equation(long... parameters) {
        this.parameters = parameters;
    }
    long calculate(long x){
        long value=0;
        for(long a : parameters){
            value=x*value+a;
        }
        return value;
    }
}
