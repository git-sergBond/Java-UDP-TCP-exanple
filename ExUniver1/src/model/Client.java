package model;

import java.math.BigDecimal;

public class Client {
    // ФИО
    private String fullName;

    //Номер счета
    private int accountId;

    //Тип счета
    private AccountType accountType;

    //Количество средств на счете
    private BigDecimal amount;

    public Client(String fullName, int accountId, AccountType accountType, BigDecimal amount) {
        this.fullName = fullName;
        this.accountId = accountId;
        this.accountType = accountType;
        this.amount = amount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
