package model;

import java.time.LocalDate;

public class Pessoa {
    private int codigo;
    private String nome;
    private Long cpf;
    private Long telefone;
    private LocalDate dataNascimento;



    public Pessoa(String nome,  Long cpf, Long telefone, LocalDate dataNascimento ) {

        this.codigo = (int) (Math.random()*10);
        this.nome=nome;
        this.cpf=cpf;
        this.dataNascimento=dataNascimento;
    }
    public String getNome() {return nome;}
    public Long getTelefone() {return telefone;}
    public LocalDate getDataNascimento() {return dataNascimento;}
    public Long getCpf() {return cpf;}
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
