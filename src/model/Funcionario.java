package model;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {
    private Integer totalAtendimentos;

    public void contarAtendimento() {
        this.totalAtendimentos++;
    }

    @Override
    public int compareTo (Funcionario funcionario){
        return this.totalAtendimentos.compareTo(funcionario.totalAtendimentos);
    }

    public Funcionario(Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.totalAtendimentos = 0;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "totalAtendimentos=" + totalAtendimentos +
                "} " + super.toString();
    }

    public int getTotalAtendimentos() {
        return this.totalAtendimentos;
    }
}
