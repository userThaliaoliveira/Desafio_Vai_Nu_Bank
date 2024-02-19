package banco;

public abstract class Conta {
    private String numero;
    private String agencia;
    private String nomeTitular;
    private String cpf;
    private double saldo;

    public Conta(String numero, String agencia, String nomeTitular, String cpf, double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado. Novo saldo: R$" + saldo);
    }

    public abstract void sacar(double valor);

    public void transferir(Conta destino, double valor) {
        if (saldo >= valor) {
            sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }
}
