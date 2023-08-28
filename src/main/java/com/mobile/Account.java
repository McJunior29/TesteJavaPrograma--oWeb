package com.mobile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private int id;
    private double valor;
    private Holder holder;
    private static String agency = "0796";
    private String number;

    private static int count;

    private static Map<Integer, Account> accounts = new HashMap<>();

    public Account(double valor, Holder holder) {
        count ++;
        this.id = count;
        this.number = this.generateNumber(id);
        this.valor = valor;
        this.holder = holder;
        this.id = count;
    }

    private static String generateNumber(int id){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        String number = id+formattedDate.toString();
        return number;
    }

    public static Account buscarContaPorId(int id) {
        return accounts.get(id);
    }

    public Account() {
    }

    public String verSaldo(){
        return "Saldo atual:" + this.valor+"\n Data: "+ LocalDate.now();
    }

    public void depositar(double valor){
        this.valor += valor;

    }

    public String sacar(double valorSaque){
        if(this.valor >= valorSaque){
            this.valor -= valorSaque;
        }
        return "Saldo insuficiente";
    }

    public void transferir(Account destinatario, double valorTransferencia) {
        if (this.valor >= valorTransferencia) {
            this.valor -= valorTransferencia;
            destinatario.depositar(valorTransferencia);
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }
    
    
    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }

    public void listarContas(Map<Integer, Account> accounts) {
        System.out.println("Lista de Contas:");
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            int id = entry.getKey();
            Account conta = entry.getValue();
            System.out.println("ID: " + id + ", Nome: " + conta.getHolder().getName() + ", Saldo: " + conta.getValor());
        }
    }

    public static void deleteAccount(int id) {
        accounts.remove(id);
    }
    

    @Override
    public String toString() {
        return "Account [valor=" + valor + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public static Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(Map<Integer, Account> accounts) {
        Account.accounts = accounts;
    }

    public static String getAgency() {
        return agency;
    }

    public static void setAgency(String agency) {
        Account.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Account.count = count;
    }

    

}
