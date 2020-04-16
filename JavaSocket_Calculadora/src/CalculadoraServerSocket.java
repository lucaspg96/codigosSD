import models.Operation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

    public static void main(String[] args) {
        // inicialmente, declaramos as vari�veis que ser�o utilizadas no c�digo
        ServerSocket welcomeSocket;
        DataOutputStream socketOutput;
        DataInputStream socketInput;
        BufferedReader socketEntrada;

        // e instanciamos um objeto de Calculadora para operar as mensagens recebidas via socket
        Calculadora calc = new Calculadora();
        try {
            // iniciamos nosso socket na porta 9090
            welcomeSocket = new ServerSocket(9090);
            // e criamos um contador para o n�mero de clientes
            int i = 0; //n�mero de clientes

            System.out.println("Servidor no ar");

            // o servidor entra num loop infinito, processando toda nova conex�o que chegue
            while (true) {
				// quando uma conex�o chegar, ela ser� aceita
                Socket connectionSocket = welcomeSocket.accept();
                // e o n�mero de clientes ser� incrementado
                i++;
                System.out.println("Nova conex�o");

                //Interpretando dados do servidor
				// criamos um objeto para leitura dos dados que ser�o enviados
                socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                // todas as mensagens devem ser enviadas em uma �nica linha, portanto basta que leiamos a primeira;
                String line = socketEntrada.readLine();
                // em seguida, a mensagem � transformada em uma opera��o (ver m�todo parse(String line))
                Operation op = Operation.parse(line);

                // depois, chamamos a calculadora, informando a opera��o recebida e armazenando  oresultado da opera��o
                String result = "" + calc.run(op);

                //Enviando dados para o servidor
				// criamos ent�o um objeto para escrever no socket, permitindo que enviemos o resultado para o cliente
                socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
                // escrevemos o resultado no socket
                socketOutput.writeBytes(result + '\n');
                System.out.println(result);
                // enviamos a mensagem
                socketOutput.flush();
                //e encerramos a escrita
                socketOutput.close();
				connectionSocket.close();

            }
        } catch (IOException e) {
			// caso haja alguma exce��o de IO, ser� apresentado seu tra�o
            e.printStackTrace();
        }

    }

}
