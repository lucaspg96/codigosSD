import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Instância da interface ICalculadora, implementando todos os métodos nela definidos
 */
public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;

	/**
	 * Todos os métodos são implementados da mesma maneira:
	 * - eles recebem 2 operandos
	 * - incrementam o número de chamadas realizadas
	 * - retornam o valor daquela operação
	 * @param a - operando 1
	 * @param b - operando 2
	 * @return resultado da operação
	 * @throws RemoteException
	 */

	public int soma(int a, int b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}

	/**
	 * Todos os métodos são implementados da mesma maneira:
	 * - eles recebem 2 operandos
	 * - incrementam o número de chamadas realizadas
	 * - retornam o valor daquela operação
	 * @param a - operando 1
	 * @param b - operando 2
	 * @return resultado da operação
	 * @throws RemoteException
	 */

	public int subtracao(int a, int b) throws RemoteException {
		System.out.println("Método subtração chamado " + chamadas++);
		return a - b;
	}

	/**
	 * Todos os métodos são implementados da mesma maneira:
	 * - eles recebem 2 operandos
	 * - incrementam o número de chamadas realizadas
	 * - retornam o valor daquela operação
	 * @param a - operando 1
	 * @param b - operando 2
	 * @return resultado da operação
	 * @throws RemoteException
	 */

	public int multiplicacao(int a, int b) throws RemoteException {
		System.out.println("Método multiplicacao chamado " + chamadas++);
		return a * b;
	}

	/**
	 * Todos os métodos são implementados da mesma maneira:
	 * - eles recebem 2 operandos
	 * - incrementam o número de chamadas realizadas
	 * - retornam o valor daquela operação
	 * @param a - operando 1
	 * @param b - operando 2
	 * @return resultado da operação
	 * @throws RemoteException
	 */

	public int divisao(int a, int b) throws RemoteException {
		System.out.println("Método divisao chamado " + chamadas++);
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		// inicialmente, criamos uma instância da calculadora que será disponibilizada para chamadas remotas
		Calculadora calculadora = new Calculadora();		
		// e geramos a exportação do objeto
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);

		Registry reg = null;
		try {
			System.out.println("Creating registry...");
			// em seguida, criamos o registro na porta 1099
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				// caso não funcione, tentamos de novo
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				// se o erro persistir, desistimos :p
				System.exit(0);
			}
		}

		// quando der certo, fazemos o link do objeto exportado no registro
		reg.rebind("calculadora", stub);
	}
}
