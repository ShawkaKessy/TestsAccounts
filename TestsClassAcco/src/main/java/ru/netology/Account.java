package ru.netology;

abstract class Account implements AccountOperation {
    protected long balance;

    public Account(long initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean add(long amount) {
        if (amount < 0) return false;
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (amount < 0 || balance - amount < 0) return false;
        balance -= amount;
        return true;
    }

    @Override
    public boolean transfer(Account account, long amount) {
        if (amount < 0 || balance - amount < 0) return false;
        balance -= amount;
        account.add(amount);
        return true;
    }

    @Override
    public long getBalance() {
        return balance;
    }
}
