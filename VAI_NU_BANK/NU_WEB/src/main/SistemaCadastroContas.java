package main;

import banco.Conta;
import banco.ContaCorrente;
import banco.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCadastroContas {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarContaCorrente();
                    break;
                case 2:
                    criarContaPoupanca();
                    break;
                case 3:
                    realizarOperacao();
                    break;
                case 4:
                    exibirInformacoesContas();
                    break;
                case 5:
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para exibir o menu principal
    private static void exibirMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1. Criar conta corrente");
        System.out.println("2. Criar conta poupança");
        System.out.println("3. Realizar operação (saque, depósito, transferência)");
        System.out.println("4. Exibir informações das contas");
        System.out.println("5. Encerrar sistema");
        System.out.print("Escolha uma opção: ");
    }

    // Método para criar uma conta corrente
    private static void criarContaCorrente() {
        System.out.println("Digite o número da conta corrente (6 dígitos):");
        String numeroConta = scanner.next();

        // Verificar se o número da conta tem exatamente 6 dígitos
        if (!numeroConta.matches("\\d{6}")) {
            System.out.println("Número da conta corrente inválido. Digite exatamente 6 dígitos.");
            return;
        }

        System.out.println("Digite a agência (4 dígitos):");
        String agencia = scanner.next();

        // Verificar se a agência tem exatamente 4 dígitos
        if (!agencia.matches("\\d{4}")) {
            System.out.println("Agência inválida. Digite exatamente 4 dígitos.");
            return;
        }

        System.out.println("Digite o nome do titular:");
        String nomeTitular = scanner.next();
        System.out.println("Digite o CPF do titular:");
        String cpf = scanner.next();
        System.out.println("Digite o saldo inicial:");
        double saldoInicial = scanner.nextDouble();
        System.out.println("Digite o limite de crédito:");
        double limiteCredito = scanner.nextDouble();

        // Criar a conta corrente e adicioná-la à lista de contas
        ContaCorrente contaCorrente = new ContaCorrente(numeroConta, agencia, nomeTitular, cpf, saldoInicial, limiteCredito);
        contas.add(contaCorrente);

        System.out.println("Conta corrente criada com sucesso.");
    }

    // Método para criar uma conta poupança
    private static void criarContaPoupanca() {
        System.out.println("Digite o número da conta poupança (no formato XXXXX-X):");
        String numeroConta = scanner.next();

        // Verificar se o número da conta tem o formato XXXXX-X
        if (!numeroConta.matches("\\d{5}-\\d")) {
            System.out.println("Número da conta poupança inválido. Use o formato XXXXX-X.");
            return;
        }

        System.out.println("Digite a agência (4 dígitos):");
        String agencia = scanner.next();

        // Verificar se a agência tem exatamente 4 dígitos
        if (!agencia.matches("\\d{4}")) {
            System.out.println("Agência inválida. Digite exatamente 4 dígitos.");
            return;
        }

        System.out.println("Digite o nome do titular:");
        String nomeTitular = scanner.next();
        System.out.println("Digite o CPF do titular:");
        String cpf = scanner.next();
        System.out.println("Digite o saldo inicial:");
        double saldoInicial = scanner.nextDouble();
        System.out.println("Digite o dia de aniversário da conta (no formato DD/MM):");
        int diaAniversario = scanner.nextInt();

        // Criar a conta poupança e adicioná-la à lista de contas
        ContaPoupanca contaPoupanca = new ContaPoupanca(numeroConta, agencia, nomeTitular, cpf, saldoInicial, diaAniversario);
        contas.add(contaPoupanca);

        System.out.println("Conta poupança criada com sucesso.");
    }

    // Método para realizar operações (depósito, saque, transferência)
    private static void realizarOperacao() {
        System.out.println("Digite o número da sua conta:");
        String numeroConta = scanner.next();

        // Encontrar a conta com o número fornecido
        Conta conta = encontrarContaPorNumero(numeroConta);

        if (conta != null) {
            exibirMenuOperacao();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor para depósito:");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.println("Digite o valor para saque:");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 3:
                    System.out.println("Digite o número da conta de destino:");
                    String numeroDestino = scanner.next();
                    Conta contaDestino = encontrarContaPorNumero(numeroDestino);

                    if (contaDestino != null) {
                        System.out.println("Digite o valor para transferência:");
                        double valorTransferencia = scanner.nextDouble();
                        conta.transferir(contaDestino, valorTransferencia);
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    // Método para exibir informações de todas as contas
    private static void exibirInformacoesContas() {
        for (Conta conta : contas) {
            System.out.println("----------------------------");
            System.out.println("Número da Conta: " + conta.getNumero());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Nome do Titular: " + conta.getNomeTitular());
            System.out.println("CPF do Titular: " + conta.getCpf());
            System.out.println("Saldo: R$" + conta.getSaldo());

            if (conta instanceof ContaCorrente) {
                ContaCorrente cc = (ContaCorrente) conta;
                System.out.println("Tipo de Conta: Corrente");
                System.out.println("Limite de Crédito: R$" + cc.getLimiteCredito());
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca cp = (ContaPoupanca) conta;
                System.out.println("Tipo de Conta: Poupança");
                System.out.println("Dia de Aniversário: " + cp.getDiaAniversario());
            }

            System.out.println("----------------------------");
        }

        // Agora, após exibir as informações, aguarda a confirmação do usuário antes de voltar ao menu
        System.out.println("Pressione Enter para voltar ao menu.");
        scanner.nextLine(); // Limpar o buffer do teclado
        scanner.nextLine(); // Aguardar a entrada do usuário
    }

    // Método para encontrar uma conta pelo número
    private static Conta encontrarContaPorNumero(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    // Método para exibir o menu de operações
    private static void exibirMenuOperacao() {
        System.out.println("----- Operações -----");
        System.out.println("1. Depósito");
        System.out.println("2. Saque");
        System.out.println("3. Transferência");
        System.out.print("Escolha uma operação: ");
    }
}
