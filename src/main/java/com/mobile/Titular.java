package com.mobile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Titular {
    private int id;
    private String nome;
    private String telefone;
    private List<Conta> contas;
    private static int contador;

    private static Map<Integer, Titular> titulares = new HashMap<>();

    public Titular(String nome, String telefone) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.telefone = telefone;
        this.contas = new ArrayList<>();
        titulares.put(id, this); 
    }

    public Titular(){
    }

    public static Titular buscarTitular(int id){
        return titulares.get(id);
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public static void excluirTitular(int id) {
        Titular titularParaExcluir = titulares.remove(id);
        if (titularParaExcluir != null) {
            List<Conta> contasParaRemover = titularParaExcluir.contas;
            for (Conta conta : contasParaRemover) {
                conta.setTitular(null);
                Conta.excluirConta(conta.getNumero());
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public static Map<Integer, Titular> getTitulares() {
        return titulares;
    }

    public static void setTitulares(Map<Integer, Titular> titulares) {
        Titular.titulares = titulares;
    }
}
