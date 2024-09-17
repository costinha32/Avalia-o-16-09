package br.com.ads;

import java.time.LocalDate;

public class ContaPoupanca extends Conta{
    private static final double RENDIMENTO = 0.15;
    public ContaPoupanca() {
        super();
    }
    public ContaPoupanca(String numeroConta, String agencia, LocalDate dataAbertura, double saldo) {
        super(numeroConta, agencia, dataAbertura, saldo);
    }
    public void aplicarRendimento() {
        double saldoAtual = getSaldo();
        double novoSaldo = saldoAtual + (saldoAtual * RENDIMENTO);
        setSaldo(novoSaldo);
    }
}
