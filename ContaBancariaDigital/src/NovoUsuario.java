import java.util.Scanner;

public class NovoUsuario extends Usuario {
    public NovoUsuario(String nome, String sobrenome, int idade, String cpf) {
        super(nome, sobrenome, idade, cpf);
    }

    public static NovoUsuario cadastrar(Scanner input) {
        System.out.print("Informe seu nome: ");
        String nome = input.nextLine();

        System.out.print("Informe seu sobrenome: ");
        String sobrenome = input.nextLine();

        System.out.print("Informe sua idade: ");
        int idade = input.nextInt();
        input.nextLine();

        System.out.print("Informe seu CPF (Apenas números): ");
        String cpf = input.nextLine();

        NovoUsuario cliente = new NovoUsuario(nome, sobrenome, idade, cpf);

        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println("Seja bem-vindo ao banco dio, " + nome);

        return cliente;
    }
}
