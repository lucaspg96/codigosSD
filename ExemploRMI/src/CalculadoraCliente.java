import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Cliente que irá consumir a calculadora disponibilizada no registro via RMI
 */
public class CalculadoraCliente {
	
	public static void main(String[] args) {
		// primeiramente, declaramos as variáveis a serem utilizadas
		Registry reg;
		ICalculadora calc;		
		try {
			// em seguida, tentamos nos conectar ao registro
			reg = LocateRegistry.getRegistry(1099);
			// em seguida, obtemos o acesso à instância da calculadora disponível
			calc = (ICalculadora) reg.lookup("calculadora");
			// por fim, executamos as operações disponíveis
			System.out.println(calc.soma(3,2));
			System.out.println(calc.subtracao(3,2));
			System.out.println(calc.multiplicacao(3,2));
			System.out.println(calc.divisao(10,2));
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
