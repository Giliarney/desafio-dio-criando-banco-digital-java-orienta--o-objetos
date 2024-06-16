import java.util.Scanner;

public class AppBanco {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        handleUserSelection();
    }

    private static void handleUserSelection() {
        NovoUsuario novoCliente = null;
        ContaPai novaContaCorrente = null;
        ContaPai novaContaPoupanca = null;
        PaginaInicial.showInicialPagina();
        while (true) {
            PaginaInicial.showInicialPagina();
            System.out.print("Escolha uma opção: ");
            String escolhaUsuario = input.nextLine().trim();

            switch (escolhaUsuario) {
                case "1":
                    novoCliente = NovoUsuario.cadastrar(input);
                    String accountType = createAccount(novoCliente);
                    if (accountType.equals("1")) {
                        novaContaCorrente = new ContaCorrente(novoCliente);
                    } else if (accountType.equals("2")) {
                        novaContaPoupanca = new ContaPoupanca(novoCliente);
                    }
                    break;
                case "2":
                    if (novoCliente == null) {
                        System.out.println("Não existem usuários cadastrados, crie uma conta primeiro.");
                    } else {
                        handleBankOperations(novoCliente, novaContaCorrente, novaContaPoupanca);
                    }
                    break;
                case "3":
                    System.out.println("Saindo...");
                    input.close();
                    return;
                default:
                    System.out.println("Opção inválida. Verifique a opção escolhida e tente novamente!\n");
                    break;
            }
        }
    }

    private static String createAccount(NovoUsuario novoCliente) {
        System.out.print("""
                Qual conta deseja criar?
                1 - Conta Corrente
                2 - Conta Poupança
                \n""");
        System.out.print("Escolha uma opção: ");
        String escolhaConta = input.nextLine().trim();

        switch (escolhaConta) {
            case "1":
                System.out.println("Conta Corrente criada com sucesso!");
                return "1";
            case "2":
                System.out.println("Conta Poupança criada com sucesso!");
                return "2";
            default:
                System.out.println("Opção inválida. Conta não criada.");
                return "0";
        }
    }

    private static void handleBankOperations(NovoUsuario novoCliente, ContaPai novaContaCorrente, ContaPai novaContaPoupanca) {
        while (true) {
            PaginaInicialBanco.showInicialPaginaBanco();
            System.out.print("Escolha uma opção: ");
            String escolhaUsuarioLogado = input.nextLine().trim();

            switch (escolhaUsuarioLogado) {
                case "1":
                    performBankOperation(novaContaCorrente, novaContaPoupanca, "depositar");
                    break;
                case "2":
                    performBankOperation(novaContaCorrente, novaContaPoupanca, "sacar");
                    break;
                case "3":
                    performBankOperation(novaContaCorrente, novaContaPoupanca, "transferir");
                    break;
                case "4":
                    performBankOperation(novaContaCorrente, novaContaPoupanca, "exibirExtrato");
                    break;
                case "5":
                    if (novaContaCorrente == null && novaContaPoupanca == null) {
                        String accountType = createAccount(novoCliente);
                        if (accountType.equals("1")) {
                            novaContaCorrente = new ContaCorrente(novoCliente);
                        } else if (accountType.equals("2")) {
                            novaContaPoupanca = new ContaPoupanca(novoCliente);
                        }
                    }
                    break;
                case "6":
                    System.out.println("Saindo...");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }
    }

    private static void performBankOperation(ContaPai contaCorrente, ContaPai contaPoupanca, String operacao) {
        switch (operacao) {
            case "depositar":
                if (contaCorrente != null) {
                    contaCorrente.depositar();
                } else if (contaPoupanca != null) {
                    contaPoupanca.depositar();
                } else {
                    System.out.println("Não foi encontrada nenhuma conta!");
                }
                break;
            case "sacar":
                if (contaCorrente != null) {
                    contaCorrente.sacar();
                } else if (contaPoupanca != null) {
                    contaPoupanca.sacar();
                } else {
                    System.out.println("Não foi encontrada nenhuma conta!");
                }
                break;
            case "transferir":
                if (contaCorrente != null && contaPoupanca != null) {
                    contaCorrente.transferir(contaPoupanca);
                } else if (contaPoupanca != null && contaCorrente != null) {
                    contaPoupanca.transferir(contaCorrente);
                } else {
                    System.out.println("Não foi encontrada nenhuma conta!");
                }
                break;
            case "exibirExtrato":
                if (contaCorrente != null) {
                    contaCorrente.exibirExtrato();
                } else if (contaPoupanca != null) {
                    contaPoupanca.exibirExtrato();
                } else {
                    System.out.println("Não foi encontrada nenhuma conta!");
                }
                break;
        }
    }
}
