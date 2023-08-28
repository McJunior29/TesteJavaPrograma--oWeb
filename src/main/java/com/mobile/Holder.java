package com.mobile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Holder {
    private int id;
    private String name;
    private String phone;
    private List<Account> accounts;
    private static int count;

    private static Map<Integer, Holder> holders = new HashMap<>();

    public Holder(String name, String phone) {
        count++;
        this.id = count;
        this.name = name;
        this.phone = phone;
        this.accounts = new ArrayList<>();
        holders.put(id, this); 
    }

    public Holder(){
    }

    public static Holder searchHolder(int id){
        return holders.get(id);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public static void deleteHolder(int id) {
        Holder holderToDelete = holders.remove(id);
        if (holderToDelete != null) {
            List<Account> accountsToRemove = holderToDelete.accounts;
            for (Account account : accountsToRemove) {
                account.setHolder(null);
                Account.deleteAccount(account.getId());
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static Map<Integer, Holder> getHolders() {
        return holders;
    }

    public static void setHolders(Map<Integer, Holder> holders) {
        Holder.holders = holders;
    }


    







    
}
