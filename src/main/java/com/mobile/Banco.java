package com.mobile;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private int id;
    private List<Holder> holders;
    private List<Account> accounts;


    public Banco(int id, List<Holder> holders, List<Account> accounts) {
        this.id = id;
        this.holders = holders;
        this.accounts = accounts;
    }

    public Banco() {
        holders = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Holder> getHolders() {
        return holders;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void adicionarTitular(Holder titular) {
        holders.add(titular);
    }

    public void adicionarConta(Account conta) {
        accounts.add(conta);
    }
    

    

}
