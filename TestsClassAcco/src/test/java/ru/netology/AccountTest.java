package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

    private SimpleAccount account1;
    private SimpleAccount account2;

    @BeforeEach
    void setUp() {
        account1 = new SimpleAccount(1000); // Аккаунт с балансом 1000
        account2 = new SimpleAccount(500);  // Аккаунт с балансом 500
    }

    @Test
    void testAdd_validAmount_success() {
        // given
        long initialBalance = account1.getBalance();
        long amountToAdd = 200;

        // when
        boolean result = account1.add(amountToAdd);

        // then
        Assertions.assertTrue(result, "Добавление положительной суммы должно вернуть true");
        Assertions.assertEquals(initialBalance + amountToAdd, account1.getBalance(),
                "Баланс должен увеличиться на добавленную сумму");
    }

    @Test
    void testPay_validAmount_success() {
        // given
        long initialBalance = account1.getBalance();
        long amountToPay = 300;

        // when
        boolean result = account1.pay(amountToPay);

        // then
        Assertions.assertTrue(result, "Оплата допустимой суммы должна вернуть true");
        Assertions.assertEquals(initialBalance - amountToPay, account1.getBalance(),
                "Баланс должен уменьшиться на сумму списания");
    }

    @Test
    void testTransfer_validAmount_success() {
        // given
        long senderInitialBalance = account1.getBalance();
        long receiverInitialBalance = account2.getBalance();
        long amountToTransfer = 200;

        // when
        boolean result = account1.transfer(account2, amountToTransfer);

        // then
        Assertions.assertTrue(result, "Перевод положительной суммы должен вернуть true");
        Assertions.assertEquals(senderInitialBalance - amountToTransfer, account1.getBalance(),
                "Баланс отправителя должен уменьшиться на сумму перевода");
        Assertions.assertEquals(receiverInitialBalance + amountToTransfer, account2.getBalance(),
                "Баланс получателя должен увеличиться на сумму перевода");

    }
    @Test
    void testAdd_negativeAmount_fail() {
        // given
        long initialBalance = account1.getBalance();
        long negativeAmount = -100;

        // when
        boolean result = account1.add(negativeAmount);

        // then
        Assertions.assertFalse(result, "Пополнение отрицательной суммой должно вернуть false");
        Assertions.assertEquals(initialBalance, account1.getBalance(),
                "Баланс не должен измениться при попытке пополнить отрицательной суммой");
    }

    @Test
    void testPay_insufficientFunds_fail() {
        // given
        long initialBalance = account1.getBalance();
        long amountToPay = 2000;

        // when
        boolean result = account1.pay(amountToPay);

        // then
        Assertions.assertFalse(result, "Списание суммы, превышающей баланс, должно вернуть false");
        Assertions.assertEquals(initialBalance, account1.getBalance(),
                "Баланс не должен измениться при попытке списать сумму, превышающую баланс");
    }

    @Test
    void testTransfer_insufficientFunds_fail() {
        // given
        long senderInitialBalance = account1.getBalance();
        long receiverInitialBalance = account2.getBalance();
        long amountToTransfer = 1500;

        // when
        boolean result = account1.transfer(account2, amountToTransfer);

        // then
        Assertions.assertFalse(result, "Перевод суммы, превышающей баланс отправителя, должен вернуть false");
        Assertions.assertEquals(senderInitialBalance, account1.getBalance(),
                "Баланс отправителя не должен измениться при неудачном переводе");
        Assertions.assertEquals(receiverInitialBalance, account2.getBalance(),
                "Баланс получателя не должен измениться при неудачном переводе");
    }

}
