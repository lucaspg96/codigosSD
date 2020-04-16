package models;

/**
 * Mensagem representando a operação de oma.
 *O ID de sua operação é 1
 */

public class Sum extends AbstractOperationRequest {

    public Sum(int a, int b) {
        super(a,b,1);
    }

    @Override
    public String describe() {
        return op1 + " + " + op2 + " =";
    }
}