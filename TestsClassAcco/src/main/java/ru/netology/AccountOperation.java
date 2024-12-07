package ru.netology;

public interface AccountOperation {
    boolean add(long amount);
    boolean pay(long amount);
    boolean transfer(Account account, long amount);
    long getBalance();
}
