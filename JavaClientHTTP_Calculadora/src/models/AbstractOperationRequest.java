package models;

/**
 * Essa classe representa a base de todas operações que são processadas pelo servidor
 */
public abstract class AbstractOperationRequest implements OperationRequest {

    // todas as operações, por serem binárias, possuem 2 operandos
    protected int op1, op2;
    // e um operador
    protected int operationId;

    public AbstractOperationRequest(int op1, int op2, int operationId) {
        this.op1 = op1;
        this.op2 = op2;
        this.operationId = operationId;
    }

    /**
     * Todas as operações terão a sua string de requisição seguindo o formato abaixo,
     * variando apenas nos valores dos operandos e no ID do operador
     * @return
     */
    @Override
    public String toStringRequest() {
        return "oper1="+op1+"&oper2="+op2+"&operacao="+operationId;
    }
}
