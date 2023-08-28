package com.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco();

        Scanner scanner = new Scanner(System.in);
        Map<String, Conta> contas = new HashMap<>();
        Map<Integer, Titular> titulares = new HashMap<>();

        int escolha;
        double valor;

        do {
            System.out.println("============");
            System.out.println("Menu Inicial");
            System.out.println("============");
            System.out.println("1 - Cadastrar Titular");
            System.out.println("2 - Cadastrar Conta");
            System.out.println("3 - Operar uma conta");
            System.out.println("4 - Sair");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    criarTitular(titulares, banco);
                    break;
                case 2:
                    listarContas(contas);
                    criarConta(contas, banco);
                    break;
                case 3:
                    menuConta(contas);
                    break;
                case 4:
                    scanner.close();
                    System.out.println("Saindo do programa. Obrigado!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (escolha != 4);

    }

    private static void menuConta(Map<String, Conta> contasModel) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Número da conta:");
        String numero = scanner.nextLine();

        int escolha;
        Map<String, Conta> contas = contasModel;

        do {
            System.out.println("Menu do Banco");
            System.out.println("1. Verificar Saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    realizarOperacao(contas, numero, Operacao.VERIFICAR_SALDO);
                    break;
                case 2:
                    realizarOperacao(contas, numero, Operacao.DEPOSITAR);
                    break;
                case 3:
                    realizarOperacao(contas, numero, Operacao.SACAR);
                    break;
                case 4:
                    realizarOperacao(contas, numero, Operacao.TRANSFERIR);
                    break;
                case 5:
                    realizarOperacao(contas, numero, Operacao.DELETAR);
                    break;
                case 0:
                    scanner.close();
                    System.out.println("Saindo do programa. Obrigado!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (escolha != 0);
    }

    private static void realizarOperacao(Map<String, Conta> contas, String numeroConta, Operacao operacao) {
        Scanner scanner = new Scanner(System.in);
        Conta conta = contas.get(numeroConta);

        if (conta != null) {
            switch (operacao) {
                case VERIFICAR_SALDO:
                    System.out.println(conta.getValor());
                    break;
                case DEPOSITAR:
                    System.out.print("Digite o valor a ser depositado: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado. Saldo atual: " + conta.getValor());
                    break;
                case SACAR:
                    System.out.print("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    String resultadoSaque = conta.sacar(valorSaque);
                    System.out.println(resultadoSaque);
                    System.out.println("Saldo atual: " + conta.getValor());
                    break;
                case TRANSFERIR:
                    System.out.print("Digite o número da conta de destino: ");
                    String numeroContaDestino = scanner.nextLine();
                    Conta contaDestino = contas.get(numeroContaDestino);

                    if (contaDestino != null) {
                        System.out.print("Digite o valor a ser transferido: ");
                        double valorTransferencia = scanner.nextDouble();
                        conta.transferir(contaDestino, valorTransferencia);
                        System.out.println("Saldo atual: " + conta.getValor());
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                    break;
                case DELETAR:
                    deletarConta(conta);
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void deletarConta(Conta conta){
        Conta contaModel = conta;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja realmenete apagar a conta? (S/N)");
        String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("S")) {
                conta.excluirConta(conta.getNumero());
            }
    }
    private static void criarConta(Map<String, Conta> contas, Banco banco) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID do Titular:");
        int idTitular = scanner.nextInt();

        Titular titular = new Titular();
        titular = titular.buscarTitular(idTitular);

        if (titular != null) {
            Conta novaConta = new Conta(0, titular);
            contas.put(novaConta.getNumero(), novaConta);
            banco.adicionarConta(novaConta);

            System.out.println("Deseja fazer um depósito inicial? (S/N)");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Digite o valor do depósito inicial: ");
                double valorDeposito = scanner.nextDouble();
                novaConta.depositar(valorDeposito);
                System.out.println("Depósito realizado. Saldo atual: " + novaConta.getValor());
            }

            System.out.println("Conta criada com sucesso. Número: " + novaConta.getNumero());
        } else {
            System.out.println("Titular não encontrado. Cadastre ou insira um ID válido.");
        }
    }

    private static void criarTitular(Map<Integer, Titular> titulares, Banco banco) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Telefone de contato:");
        String telefone = scanner.next();
        Titular novoTitular = new Titular(nome, telefone);
        titulares.put(novoTitular.getId(), novoTitular);
        banco.adicionarTitular(novoTitular);
        System.out.println("Titular cadastrado com sucesso!");
    }

    private static void listarContas(Map<String, Conta> contas) {
        System.out.println(" ----------------\n Lista de Contas:\n ----------------");
        for (Map.Entry<String, Conta> entry : contas.entrySet()) {
            String numero = entry.getKey();
            Conta conta = entry.getValue();
            System.out.println("\n Nome: " + conta.getTitular().getNome() + ", Número: " + conta.getNumero() + ", Agência: " + Conta.getAgencia() + "\n------------------------\n");
        }
    }

    private enum Operacao {
        VERIFICAR_SALDO,
        DEPOSITAR,
        SACAR,
        TRANSFERIR,
        DELETAR
    }
}
