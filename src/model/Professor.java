package model;

public class Professor extends Pessoa {
    private FormacaoAcademica formacaoAcademica;
    private ExperienciaDesenvolvimento expDesenvolvimento;
    private boolean estado;

    public Professor(Pessoa pessoa, FormacaoAcademica formacao, ExperienciaDesenvolvimento expDesenvolvimento, boolean estado) {
        super(pessoa.getCodigo(),pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.formacaoAcademica = formacao;
        this.expDesenvolvimento = expDesenvolvimento;
        this.estado=estado;
    }

    public FormacaoAcademica getFormacaoAcademica() {
        return formacaoAcademica;
    }
    public ExperienciaDesenvolvimento getExpDesenvolvimento() {
        return this.expDesenvolvimento;
    }
    public String getEstado() {return String.valueOf(this.estado);}
}
