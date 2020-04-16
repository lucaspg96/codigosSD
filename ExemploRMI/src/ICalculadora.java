import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que contém os métodos que devem ser implementados no srevidor RMI e que podem ser consumidos pelo cliente
 */

public interface ICalculadora extends Remote{

	public int soma(int a, int b) throws RemoteException;

	public int subtracao(int a, int b) throws RemoteException;

	public int multiplicacao(int a, int b) throws RemoteException;

	public int divisao(int a, int b) throws RemoteException;

}
