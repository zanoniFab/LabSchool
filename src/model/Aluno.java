package model;

public class Aluno extends Pessoa implements Comparable<Aluno> {
    private SituacaoMatricula situacaoMatricula;
    private float nota;
    private Integer atendimentosPedagogicos=0;
    public Aluno(Pessoa pessoa, float nota, SituacaoMatricula situacaoMatricula) {
        super(pessoa.getCodigo(),pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.situacaoMatricula=situacaoMatricula;
        this.nota=nota;
    }
    @Override
    public int compareTo (Aluno aluno){
        return aluno.atendimentosPedagogicos.compareTo(this.atendimentosPedagogicos);
    }
    @Override
    public String toString() {
        return "Aluno{" +
                "situacaoMatricula=" + situacaoMatricula +
                ", nota=" + nota +
                ", atendimentosPedagogicos=" + atendimentosPedagogicos +
                "} " + super.toString();}
    public void addAtendimento(){this.atendimentosPedagogicos++;}
    public boolean alterarSituacaoMatricula(SituacaoMatricula situacao){
        this.situacaoMatricula=situacao;
        return true;
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
