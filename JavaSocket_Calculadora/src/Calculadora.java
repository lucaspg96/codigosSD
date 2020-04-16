import models.Operation;

public class Calculadora {
    public String sayHello(String nome, String sobrenome) {
        return "Fala "+ nome + " " + sobrenome;
    }
    // utilizando o polimorfismo, uma vez que a operação foi reconstruída a partir da mensagem do socket,
    // posso chamar um método para computá-la.
    public double run(Operation op) { return op.eval();}
}