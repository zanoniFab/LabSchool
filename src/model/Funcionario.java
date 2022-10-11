package model;

public class Funcionario extends Pessoa {
    int totalAtendimentos = 0;

    public void contarAtendimento() {
        this.totalAtendimentos++;
    }

    public Funcionario(Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
    }

    public int getTotalAtendimentos() {
        return this.totalAtendimentos;
    }
}
