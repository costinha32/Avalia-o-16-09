package br.com.ads;

import java.time.LocalDate;

public class ContaCorrente extends Conta{
    private static final double TAXA_MANUTENCAO = 30.00;
    public ContaCorrente() {
        super();
    }

    public ContaCorrente(String numeroConta, String agencia, LocalDate dataAbertura, double saldo) {
        super(numeroConta, agencia, dataAbertura, saldo);
    }

    public void aplicarTaxaManutencao() {
        double saldoAtual = getSaldo();
        setSaldo(saldoAtual - TAXA_MANUTENCAO);
    }
    public static double getTaxaManutencao() {
        return TAXA_MANUTENCAO;
    }
}
