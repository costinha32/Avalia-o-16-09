package br.com.ads;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Conta {
    private String numeroConta;
    private String agencia;
    private LocalDate dataAbertura;
    private double saldo;
    public Conta() {
    }
    public Conta(String numeroConta, String agencia, LocalDate dataAbertura, double saldo) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getDataAbertura() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dataAbertura.format(formatter);
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
