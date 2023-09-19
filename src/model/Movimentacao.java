package model;

public class Movimentacao extends BaseValidator  {

    public Movimentacao(Integer id, Cliente cliente, String tipoMovimentacao, double valor,double saldoTotal ) {
        this.id = id;
        this.cliente = cliente;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
        this.saldoTotal = saldoTotal;
    }

    private Integer id;
    private Cliente cliente;
    private String tipoMovimentacao;
    private double valor;
    private double saldoTotal;

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    void validarMovimentacao(Movimentacao movimentacao) {

        if(movimentacao.getValor() < 0) {
            throw new RuntimeException("Valor da movimentação deve ser maior que 0");
        }
    }
}
