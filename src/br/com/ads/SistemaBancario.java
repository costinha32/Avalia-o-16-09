package br.com.ads;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaBancario {
    private List<Pessoa> pessoas = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();

    public void cadastrarConta(Conta conta) {
        contas.add(conta);
    }

    public void cadastrarPessoa(String nome, String cpf, Conta conta) {
        if (conta instanceof ContaCorrente || conta instanceof ContaPoupanca || conta instanceof ContaSalario) {
            if (pessoas.stream().anyMatch(p -> p.getCpf().equals(cpf))) {
                System.out.println("Pessoa já cadastrada!");
            } else {
                pessoas.add(new Pessoa(nome, cpf, conta));
                System.out.println("Pessoa cadastrada com sucesso!");
            }
        } else {
            System.out.println("Não é permitido associar uma Pessoa a uma conta do tipo Conta!");
        }
    }

    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Não há pessoas cadastradas!");
            return;
        }
            for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome() + ", CPF: " + pessoa.getCpf() + ", Conta: " + pessoa.getConta().getClass().getSimpleName() + "!");
            }
    }
    public void listarContas() {
        if (contas.isEmpty()){
            System.out.println("Não há contas cadastradas!");
            return;
        }
        for (Conta conta : contas) {
            System.out.println("Número da Conta: " + conta.getNumeroConta() + ", Saldo: " + conta.getSaldo() + ", Tipo: " + conta.getClass().getSimpleName() + " Data de abertura: "+ conta.getDataAbertura() + "!");
        }
    }
    public void editarConta(String numeroConta, double novoSaldo) {
        if (contas.isEmpty()){
            System.out.println("Não há contas cadastradas!");
            return;
        }
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                conta.setSaldo(novoSaldo);
                System.out.println("Saldo atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada!");
    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Conta");
            System.out.println("2. Cadastrar Pessoa");
            System.out.println("3. Listar Pessoas");
            System.out.println("4. Listar Contas");
            System.out.println("5. Editar Conta");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o tipo da conta (ContaCorrente, ContaPoupanca, ContaSalario):");
                    String tipo = scanner.nextLine();
                    if (!tipo.equalsIgnoreCase("ContaCorrente") && !tipo.equalsIgnoreCase("ContaPoupanca") && !tipo.equalsIgnoreCase("ContaSalario")) {
                        System.out.println("Tipo de conta inválido!");
                        break;
                    }
                    System.out.println("Digite o número da conta:");
                    String numero = scanner.nextLine();
                    System.out.println("Digite a agência:");
                    String agencia = scanner.nextLine();
                    System.out.println("Digite a data de abertura (dd-mm-yyyy) ou 'h' para usar a data de hoje: ");
                    LocalDate dataAbertura;
                    if ("h".equalsIgnoreCase(scanner.nextLine())) {
                        dataAbertura = LocalDate.now();
                    } else {
                        try {
                            dataAbertura = LocalDate.parse(scanner.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida! Usando a data de hoje!");
                            dataAbertura = LocalDate.now();
                        }
                    }

                    System.out.println("Digite o saldo:");
                    double saldo = scanner.nextDouble();
                    scanner.nextLine();

                    Conta conta;
                    switch (tipo) {
                        case "ContaCorrente":
                            conta = new ContaCorrente(numero, agencia, dataAbertura, saldo);
                            break;
                        case "ContaPoupanca":
                            conta = new ContaPoupanca(numero, agencia, dataAbertura, saldo);
                            break;
                        case "ContaSalario":
                            conta = new ContaSalario(numero, agencia, dataAbertura, saldo);
                            break;
                        default:
                            System.out.println("Tipo de conta inválido!");
                            continue;
                    }
                    cadastrarConta(conta);
                    break;

                case 2:
                    System.out.println("Digite o nome da pessoa:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF da pessoa:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o número da conta associada:");
                    String numeroConta = scanner.nextLine();
                    Conta contaAssociada = contas.stream().filter(c -> c.getNumeroConta().equals(numeroConta)).findFirst().orElse(null);
                    if (contaAssociada != null) {
                        cadastrarPessoa(nome, cpf, contaAssociada);
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;

                case 3:
                    listarPessoas();
                    break;

                case 4:
                    listarContas();
                    break;

                case 5:
                    if (contas.isEmpty()){
                        System.out.println("Não há contas cadastradas!");
                        break;
                    }
                    System.out.println("Digite o número da conta a ser editada:");
                    String numeroContaEditar = scanner.nextLine();
                    System.out.println("Digite o novo saldo:");
                    double novoSaldo = scanner.nextDouble();
                    scanner.nextLine();
                    editarConta(numeroContaEditar, novoSaldo);
                    break;

                case 6:
                    System.out.println("Saindo!");
                    scanner.close();

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
