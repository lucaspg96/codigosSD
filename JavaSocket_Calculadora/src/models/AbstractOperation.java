package models;

/**
 * Essa classe representa a base de todas operações
 */

public abstract class AbstractOperation implements Operation {

    // todas as operações, por serem binárias, possuem 2 operandos
    protected double op1, op2;

    public AbstractOperation(double op1, double op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    /**
     * Uma vez que sabemos os operandos e o caractere da operação, podemos obter uma instância da operação
     * @param op1 - operando 1
     * @param op2 - operando 2
     * @param operation - símbolo da operação
     * @return instância da operação
     */
    static AbstractOperation get(double op1, double op2, char operation) {
        switch (operation) {
            case '+':
                return new Sum(op1, op2);

            case '-':
                return new Subtraction(op1, op2);

            case 'x':
                return new Multiplication(op1, op2);

            case '/':
                return new Division(op1, op2);
        }

        throw new Error("Operação `"+operation+"` não conhecida");
    }
}
