package models;

/**
 * Essa interface foi criada para representar os tipos de mensagens que podemos enviar para o servidor Socket
 */

public interface Operation {

    /**
     * Ela contém um parser da mensagem, permitindo transformar a string enviada em uma isntancia da operação enviada
     * @param line - representação em string da mensagem
     * @return instancia da operação enviada
     */
    static Operation parse(String line){
        String[] values = line.split("\\|");
        if(values.length < 3) throw new Error("Mensagem mal formatada. Use a sintaxe `op1|op1|opSymbol`");

        double op1 = Double.parseDouble(values[0]);
        double op2 = Double.parseDouble(values[1]);
        char opSymbol = values[2].charAt(0);

        return AbstractOperation.get(op1, op2, opSymbol);
    }

    /**
     * As operações têm de serem capazes de gerar uma representação em string própria, representando a mensagem a ser enviada
     * @return mensagem que representa a operação
     */
    String asMessage();

    /**
     * Gera uma string legível por humanos representando a operação que deverá ser computada
     * @return string legível da operação
     */
    String describe();

    /**
     * Computa a operação da mensagem
     * @return resultado da operação
     */
    double eval();

}
