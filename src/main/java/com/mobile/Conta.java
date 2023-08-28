package com.mobile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Conta {
    private int id;
    private double valor;
    private Titular titular;
    private static String agencia = "0796";
    private String numero;

    private static int contador;

    private static Map<String, Conta> contas = new HashMap<>();

    public Conta(double valor, Titular titular) {
        contador++;
        this.id = contador;
        this.numero = this.gerarNumero(id);
        this.valor = valor;
        this.titular = titular;
        this.id = contador;
        contas.put(numero, this);
    }

    private static String gerarNumero(int id){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dataFormatada = dataAtual.format(formatador);
        String numero = id + dataFormatada.toString();
        return numero;
    }

    public static Conta buscarContaPorId(String id) {
        return contas.get(id);
    }

    public Conta() {
    }

    public String verificarSaldo(){
        return "Saldo atual: " + this.valor + "\nData: " + LocalDate.now();
    }

    public void depositar(double valor){
        this.valor += valor;
    }

    public String sacar(double valorSaque){
        if(this.valor >= valorSaque){
            this.valor -= valorSaque;
            return "Saque realizado com sucesso";
        }else{
            return "Saldo insuficiente";
        }
    }

    public void transferir(Conta destinatario, double valorTransferencia) {
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

    public void listarContas(Map<Integer, Conta> contas) {
        System.out.println("Lista de Contas:");
        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            int id = entry.getKey();
            Conta conta = entry.getValue();
            System.out.println("ID: " + id + ", Nome do Titular: " + conta.getTitular().getNome() + ", Saldo: " + conta.getValor());
        }
    }

    public static void excluirConta(String id) {
        contas.remove(id);
    }

    @Override
    public String toString() {
        return "Conta [valor=" + valor + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public static Map<String, Conta> getContas() {
        return contas;
    }

    public static void setContas(Map<String, Conta> contas) {
        Conta.contas = contas;
    }

    public static String getAgencia() {
        return agencia;
    }

    public static void setAgencia(String agencia) {
        Conta.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Conta.contador = contador;
    }
}
