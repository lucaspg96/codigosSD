package models;

/**
 * Mensagem representando a operação de Subtração.
 * O ID de sua operação é 2
 */

public class Subtraction extends AbstractOperationRequest {

    public Subtraction(int a, int b) {
        super(a,b,2);
    }

    @Override
    public String describe() {
        return op1 + " - " + op2 + " =";
    }

}
