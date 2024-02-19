package banco;

public class ContaPoupanca extends Conta {
    private int diaAniversario;
    private static final double TAXA_JUROS = 0.02;

    public ContaPoupanca(String numero, String agencia, String nomeTitular, String cpf, double saldo, int diaAniversario) {
        super(numero, agencia, nomeTitular, cpf, saldo);
        this.diaAniversario = diaAniversario;
    }

    @Override
    public void sacar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            double juros = valor * TAXA_JUROS;
            System.out.println("Saque realizado. Juros de R$" + juros + " aplicados. Novo saldo: R$" + getSaldo());
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }
}
