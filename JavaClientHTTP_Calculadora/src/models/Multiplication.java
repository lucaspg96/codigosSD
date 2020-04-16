package models;

/**
 * Mensagem representando a operação de Multiplicação.
 * O ID de sua operação é 3
 */

public class Multiplication extends AbstractOperationRequest {

    public Multiplication(int a, int b) {
        super(a,b,3);
    }

    @Override
    public String describe() {
        return op1 + " x " + op2 + " =";
    }

}
