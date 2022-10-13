package model;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    private SituacaoMatricula situacaoMatricula;
    private float nota;
    private int atendimentosPedagogicos=0;
    public Aluno(Pessoa pessoa, float nota, SituacaoMatricula situacaoMatricula) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.situacaoMatricula=situacaoMatricula;
        this.nota=nota;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "situacaoMatricula=" + situacaoMatricula +
                ", nota=" + nota +
                ", atendimentosPedagogicos=" + atendimentosPedagogicos +
                "} " + super.toString();}
    public void addAtendimento(){this.atendimentosPedagogicos++;}
    public void alterarSituacaoMatricula(SituacaoMatricula situacao){
        this.situacaoMatricula=situacao;
        System.out.print("\nMatrícula atualizada com sucesso!\n");
    }
    public void incluirNota(float nota){
        this.nota=nota;
    }
    public float getNota(){
        return this.nota;
    }

    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    public int getAtendimentosPedagogicos() {
        return atendimentosPedagogicos;
    }
}
