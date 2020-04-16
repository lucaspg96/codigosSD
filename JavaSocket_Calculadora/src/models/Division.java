package models;

public class Division extends AbstractOperation {

    public Division(double a, double b) {
        super(a,b);
    }

    @Override
    public String describe() {
        return op1 + " / " + op2 + " =";
    }

    @Override
    public String asMessage() {
        return op1+"|"+op2+"|/";
    }

    @Override
    public double eval() {
        return op1 / op2;
    }
}
