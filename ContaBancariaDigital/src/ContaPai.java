import java.util.Scanner;

public abstract class ContaPai {

    Scanner input = new Scanner(System.in);

    private static final String AGENCIA = "067-8";
    private static int numeroConta = 1;

    protected String agenciaUsuario;
    protected int contaUsuario;
    protected double saldoUsuario;
    protected NovoUsuario cliente;

    public ContaPai(NovoUsuario cliente) {
        this.cliente = cliente;
        this.agenciaUsuario = ContaPai.AGENCIA;
        this.contaUsuario = numeroConta++;
        this.saldoUsuario = 0.0;
    }

    public void depositar() {
        System.out.print("Informe o valor do depósito: ");
        double valor_Deposito = input.nextDouble();
        saldoUsuario += valor_Deposito;
        System.out.println("Depósito realizado com sucesso! Saldo atual: R$" + saldoUsuario);
    }

    public void sacar() {
        System.out.print("Informe o valor do saque: ");
        double valor_Saque = input.nextDouble();
        if (valor_Saque <= saldoUsuario) {
            saldoUsuario -= valor_Saque;
            System.out.println("Saque realizado com sucesso! Saldo atual: R$" + saldoUsuario);
        } else {
            System.out.println("Saldo insuficiente para saque, realize um depósito primeiro.");
        }
    }

    public void transferir(ContaPai contaDestino) {
        System.out.print("Informe o valor que deseja transferir: ");
        double valor_Transferencia = input.nextDouble();
        if (valor_Transferencia <= saldoUsuario) {
            saldoUsuario -= valor_Transferencia;
            contaDestino.depositar();
            System.out.println("Transferência realizada com sucesso! Saldo atual: R$" + saldoUsuario);
        } else {
            System.out.println("Saldo insuficiente para transferência");
        }
    }

    public String getAgenciaUsuario() {
        return agenciaUsuario;
    }

    public int getContaUsuario() {
        return contaUsuario;
    }

    public double getSaldoUsuario() {
        return saldoUsuario;
    }

    protected void exibirExtrato() {
        System.out.println("======Extrato Conta Poupança=====");
        System.out.println();
        System.out.println("Agencia: " + this.agenciaUsuario);
        System.out.println("Conta: " + this.contaUsuario);
        System.out.println("Saldo: R$" + this.saldoUsuario);
        System.out.println();
    }
}
