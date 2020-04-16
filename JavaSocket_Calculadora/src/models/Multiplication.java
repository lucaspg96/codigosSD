package models;

public class Multiplication extends AbstractOperation {

    public Multiplication(double a, double b) {
        super(a,b);
    }

    @Override
    public String describe() {
        return op1 + " x " + op2 + " =";
    }

    @Override
    public String asMessage() {
        return op1+"|"+op2+"|x";
    }

    @Override
    public double eval() {
        return op1 * op2;
    }

}
