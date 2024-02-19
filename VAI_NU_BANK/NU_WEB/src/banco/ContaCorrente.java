package banco;

public class ContaCorrente extends Conta {
    private double limiteCredito;

    public ContaCorrente(String numero, String agencia, String nomeTitular, String cpf, double saldo, double limiteCredito) {
        super(numero, agencia, nomeTitular, cpf, saldo);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void sacar(double valor) {
        if (getSaldo() + limiteCredito >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque realizado. Novo saldo: R$" + getSaldo());
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
