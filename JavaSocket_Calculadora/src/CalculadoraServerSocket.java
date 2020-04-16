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
        // inicialmente, declaramos as variáveis que serão utilizadas no código
        ServerSocket welcomeSocket;
        DataOutputStream socketOutput;
        DataInputStream socketInput;
        BufferedReader socketEntrada;

        // e instanciamos um objeto de Calculadora para operar as mensagens recebidas via socket
        Calculadora calc = new Calculadora();
        try {
            // iniciamos nosso socket na porta 9090
            welcomeSocket = new ServerSocket(9090);
            // e criamos um contador para o número de clientes
            int i = 0; //número de clientes

            System.out.println("Servidor no ar");

            // o servidor entra num loop infinito, processando toda nova conexão que chegue
            while (true) {
				// quando uma conexão chegar, ela será aceita
                Socket connectionSocket = welcomeSocket.accept();
                // e o número de clientes será incrementado
                i++;
                System.out.println("Nova conexão");

                //Interpretando dados do servidor
				// criamos um objeto para leitura dos dados que serão enviados
                socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                // todas as mensagens devem ser enviadas em uma única linha, portanto basta que leiamos a primeira;
                String line = socketEntrada.readLine();
                // em seguida, a mensagem é transformada em uma operação (ver método parse(String line))
                Operation op = Operation.parse(line);

                // depois, chamamos a calculadora, informando a operação recebida e armazenando  oresultado da operação
                String result = "" + calc.run(op);

                //Enviando dados para o servidor
				// criamos então um objeto para escrever no socket, permitindo que enviemos o resultado para o cliente
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
			// caso haja alguma exceção de IO, será apresentado seu traço
            e.printStackTrace();
        }

    }

}
