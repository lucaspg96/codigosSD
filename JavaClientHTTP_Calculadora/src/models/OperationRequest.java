package models;

/**
 * Essa interface foi criada para representar os tipos de mensagens que podemos enviar para o servidor HTTP
 */
public interface OperationRequest {

    /**
     * Gera a string que representa a requisição, a qual será enviada pela conexão
     * @return string da requisição
     */
    String toStringRequest();

    /**
     * Gera uma string legível por humanos representando a operação que deverá ser computada
     * @return string legível da operação
     */
    String describe();

}
