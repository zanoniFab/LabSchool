package model;

import java.time.LocalDate;

public class Pessoa {
    private int codigo;
    private String nome;
    private String cpf;
    private Long telefone;
    private LocalDate dataNascimento;



    public Pessoa(int codigo, String nome,  String cpf, Long telefone, LocalDate dataNascimento ) {

        this.codigo = codigo;
        this.nome=nome;
        this.cpf=cpf;
        this.dataNascimento=dataNascimento;
    }
    public String getNome() {return nome;}
    public Long getTelefone() {return telefone;}
    public LocalDate getDataNascimento() {return dataNascimento;}
    public String getCpf() {return cpf;}
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
