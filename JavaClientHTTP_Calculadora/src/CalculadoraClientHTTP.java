import models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {

        /**
         * Esta função main gera uma lista de 4 operações, uma de cada tipo (soma, subtração, multiplicação e divisão)
         * e executa essa operação utilizando o modelo requisição-resposta.
         */

	    OperationRequest[] ops = {
	            new Sum(1,2),
                new Subtraction(10,2),
                new Multiplication(3,12),
                new Division(15,3)
	    };

        for(OperationRequest op : ops) runOperation(op);

	}

	public static void runOperation(OperationRequest op) {
        try {
            String result="";

            // inicialmente é criado um objeto URL com o endereço do servidor
            URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
            // a partir dessa URL, é recuperada a conexão HTTP com seu servidor
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // alguns parametros são definidos para a conexão, como:
            // timeout de leitura
            conn.setReadTimeout(10000);
            // timeout de conexão
            conn.setConnectTimeout(15000);
            // método HTTP utilizado (no caso, o servidor está configurado para usar POST)
            conn.setRequestMethod("POST");
            // permitindo que dados sejam enviados pela conexão
            conn.setDoInput(true);
            // permitindo que dados sejam recebidos pela conexão
            conn.setDoOutput(true);

            //ENVIO DOS PARAMETROS
            // é recuperado uma stream para escrita na conexão (por isso OutputStream)
            OutputStream os = conn.getOutputStream();
            // em seguida, é criado um objeto para escrever nessa stream
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            // a operação é então transformada em uma string representando a requisição que a representa
            // (ver método toStringRequest)
            writer.write(op.toStringRequest()); //1-somar 2-subtrair 3-multiplicar 4-dividir
            // os dados são enviados pela conexão
            writer.flush();
            // e o objeto de escrita e finalizado
            writer.close();
            // bem como a stream de entrada
            os.close();

            // é recuperado o código da resposta da conexão, para saber se houve algum erro
            int responseCode=conn.getResponseCode();
            // caso seja o codigo 200 (ok), podemos recuperar a resposta da requisição
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                //RECBIMENTO DOS PARAMETROS
                // criamos um objeto de leitura para ler a mensagem enviada pelo servidor por meio da conexão
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                // inicializamos a resposta como uma string vazia. Como serão feitas operações de concatenação, é mais
                // eficiente utilizar um StringBuilder
                StringBuilder response = new StringBuilder();
                // criamos uma variável auxiliar para armazenar a linha de conteúdo lido na conexão
                String responseLine = null;
                // enquanto a conexão retornar conteúdo
                while ((responseLine = br.readLine()) != null) {
                    // ele será adicionado à variável resposta
                    response.append(responseLine.trim());
                }
                // por fim, a String da resposta é construída
                result = response.toString();
                // e podemos obter o resultado da operação
                // PS: o método describe() gera uma string legível da operação, para ser apresentada no console
                System.out.println(op.describe()+" "+result);
            }
        } catch (IOException e) {
            //caso haja alguma exceção de IO, será apresentado seu traço
            e.printStackTrace();
        }
    }
}
