package br.com.ads;

import java.time.LocalDate;

public class ContaSalario extends Conta{
    private static final double TAXA_MANUTENCAO = 10.00;
    private int transacoesNoMes;
    public ContaSalario() {
        super();
        this.transacoesNoMes = 0;
    }
    public ContaSalario(String numeroConta, String agencia, LocalDate dataAbertura, double saldo) {
        super(numeroConta, agencia, dataAbertura, saldo);
        this.transacoesNoMes = 0;
    }
    public void registrarTransacao() {
        transacoesNoMes++;
        if (transacoesNoMes > 2) {
            aplicarTaxaManutencao();
        }
    }
    private void aplicarTaxaManutencao() {
        double saldoAtual = getSaldo();
        setSaldo(saldoAtual - TAXA_MANUTENCAO);
    }
    public int getTransacoesNoMes() {
        return transacoesNoMes;
    }
    public void setTransacoesNoMes(int transacoesNoMes) {
        this.transacoesNoMes = transacoesNoMes;
    }
}
