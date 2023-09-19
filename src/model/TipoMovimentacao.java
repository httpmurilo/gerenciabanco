package model;

public enum TipoMovimentacao {
    CREDITO("Crédito"),
    DEBITO("Débito");

    private final String tipoMovimentacao;

    TipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }
}
