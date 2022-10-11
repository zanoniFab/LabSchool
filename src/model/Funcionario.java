package model;

public class Funcionario extends Pessoa {
    private int totalAtendimentos;

    public void contarAtendimento() {
        this.totalAtendimentos++;
    }

    public Funcionario(Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.totalAtendimentos = 0;
    }

    public int getTotalAtendimentos() {
        return this.totalAtendimentos;
    }
}
