package com.example.Mercado.model;

public enum FormaPagamento {
    CARTAO("cartão"),
    PIX("pix");

    private String FormaPagamento;

    FormaPagamento(String formaPagamento) {
        this.FormaPagamento = formaPagamento;
    }
    public String getFormaPagamento() {
        return FormaPagamento;
    }
    
}
