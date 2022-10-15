package model;

public class Pedagogo extends Pessoa implements Comparable<Pedagogo> {
    private Integer totalAtendimentos;

    public void contarAtendimento() {
        this.totalAtendimentos++;
    }

    @Override
    public int compareTo (Pedagogo pedagogo){
        return pedagogo.totalAtendimentos.compareTo(this.totalAtendimentos);
    }

    public Pedagogo(Pessoa pessoa) {
        super(pessoa.getCodigo(),pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
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
