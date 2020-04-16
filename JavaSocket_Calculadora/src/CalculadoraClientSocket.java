import models.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

	public static void main(String[] args) {

        /**
         * Esta fun��o main gera uma lista de 4 opera��es, uma de cada tipo (soma, subtra��o, multiplica��o e divis�o)
         * e envia essa opera��o via socket para ser executada
         */

        Operation[] ops = {
                new Sum(1,2),
                new Subtraction(10,2),
                new Multiplication(3,12),
                new Division(15,3)
        };

        for(Operation op : ops) runOperation(op);

	}

	private static void runOperation(Operation op) {
        String result="";
        try {

            //Conex�o com o Servidor
            // inicialmente nos conectamos com o servidor
            Socket clientSocket = new Socket("localhost", 9090);
            // e criamos um objeto para escrever mensagens no socket
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviando os dados
            // em seguida, geramos a mensagem da opera��o a ser enviada e escrevemos no socket
            socketSaidaServer.writeBytes(op.asMessage()+"\n");
            // e enviamos para o servidor
            socketSaidaServer.flush();

            //Recebendo a resposta
            // criamos ent�o um objeto para ler a mensagem de resposta do socket
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            // e armazenamos ela
            result=messageFromServer.readLine();

            // apresentamos, ent�o, a opera��o enviada e o resultado dela
            // PS: o m�todo describe() gera uma string leg�vel da opera��o, para ser apresentada no console
            System.out.println(op.describe() + " " + result);

            // por fim, fechamos o socket
            clientSocket.close();

        } catch (IOException e) {
            // caso haja alguma exce��o de IO, ser� apresentado seu tra�o
            e.printStackTrace();
        }
    }

}
