package com.mobile;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private int id;
    private List<Titular> titulares;
    private List<Conta> contas;

    public Banco(int id, List<Titular> titulares, List<Conta> contas) {
        this.id = id;
        this.titulares = titulares;
        this.contas = contas;
    }

    public Banco() {
        titulares = new ArrayList<>();
        contas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarTitular(Titular titular) {
        titulares.add(titular);
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }
}
