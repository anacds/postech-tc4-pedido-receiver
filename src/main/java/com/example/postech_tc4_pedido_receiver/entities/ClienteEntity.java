package com.example.postech_tc4_pedido_receiver.entities;

public class ClienteEntity {
    private String nome;
    private String cpf;

    public ClienteEntity(String nome, String cpf) {
        super();
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() { return cpf; }

}
