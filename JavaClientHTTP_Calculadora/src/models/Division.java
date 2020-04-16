package models;

/**
 * Mensagem representando a operação de Divisão.
 * O ID de sua operação é 4
 */
public class Division extends AbstractOperationRequest {

    public Division(int a, int b) {
        super(a,b,4);
    }

    @Override
    public String describe() {
        return op1 + " / " + op2 + " =";
    }
}
