package com.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco();

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Account> accounts = new HashMap<>();
        Map<Integer, Holder> holders = new HashMap<>();

        int escolha;
        double valor;

        do{
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
                    createdHolder(holders,banco);
                    break;
                case 2:
                    listarContas(accounts);
                    createdAccount(accounts,banco);
                    break;
                case 3:
                    menuConta(accounts);
                    break;
                case 4:
                    System.out.println("Saindo do programa. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }while (escolha != 4);

    }



    private static void menuConta(Map<Integer, Account> accountsModel){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Numero da conta:");
        int number = scanner.nextInt();
        
        int escolha;
        Map<Integer, Account> accounts = accountsModel;

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
                    realizarOperacao(accounts, number, Operacao.VERIFICAR_SALDO);
                    break;
                case 2:
                    realizarOperacao(accounts, number, Operacao.DEPOSITAR);
                    break;
                case 3:
                    realizarOperacao(accounts, number, Operacao.SACAR);
                    break;
                case 4:
                    realizarOperacao(accounts, number, Operacao.TRANSFERIR);
                    break;
                case 0:
                    System.out.println("Saindo do programa. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (escolha != 0);
    }
    
    private static void realizarOperacao(Map<Integer, Account> accounts,int idConta, Operacao operacao) {
        Scanner scanner = new Scanner(System.in);
        Account conta = accounts.get(idConta);

        if (conta != null) {
            switch (operacao) {
                case VERIFICAR_SALDO:
                    System.out.println(conta.verSaldo());
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
                    System.out.print("Digite o ID da conta de destino: ");
                    int idContaDestino = scanner.nextInt();
                    Account contaDestino = accounts.get(idContaDestino);
                
                    if (contaDestino != null) {
                        System.out.print("Digite o valor a ser transferido: ");
                        double valorTransferencia = scanner.nextDouble();
                        conta.transferir(contaDestino, valorTransferencia);
                        System.out.println("Saldo atual: " + conta.getValor());
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                    break;
                }
            } else {
                System.out.println("Conta não encontrada.");
            }


    }

    private static void createdAccount(Map<Integer, Account> accounts,Banco banco) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID do Titular:");
        int idHolder = scanner.nextInt();

        Holder holder = new Holder();
        holder = holder.searchHolder(idHolder);

        if (holder != null) {
            Account novaConta = new Account(0,holder);
            accounts.put(novaConta.getId(), novaConta);
            banco.adicionarConta(novaConta);

            System.out.println("Deseja fazer um depósito inicial? (S/N)");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Digite o valor do depósito inicial: ");
                double valorDeposito = scanner.nextDouble();
                novaConta.depositar(valorDeposito);
                System.out.println("Depósito realizado. Saldo atual: " + novaConta.getValor());
            }

            System.out.println("Conta criada com sucesso. ID: " + novaConta.getId());
        } else {
            System.out.println("Titular não encontrado.Cadastre ou insira um ID válido.");
        }
    }

    private static void createdHolder(Map<Integer, Holder> holders, Banco banco){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Telefone de contato:");
        String phone = scanner.next();
        Holder newHolder = new Holder(nome, phone);
        holders.put(newHolder.getId(), newHolder);
        banco.adicionarTitular(newHolder);
        System.out.println("Titular cadastrado com sucesso!");

    }

    private static void listarContas(Map<Integer, Account> accounts) {
        System.out.println(" ----------------\n Lista de Contas:\n ----------------");
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            int id = entry.getKey();
            Account conta = entry.getValue();
            System.out.println("\n Nome: " + conta.getHolder().getName() + ", Numero: " + conta.getNumber() +", Agencia: "+ Account.getAgency()+"\n------------------------\n");
        }
    }
    private enum Operacao {
        VERIFICAR_SALDO,
        DEPOSITAR,
        SACAR,
        TRANSFERIR
    }
}
