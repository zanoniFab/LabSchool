package model;

public class Professor extends Pessoa {
    private FormacaoAcademica formacaoAcademica;
    private ExperienciaDesenvolvimento expDesenvolvimento;
    private boolean estado;

    public Professor(Pessoa pessoa, FormacaoAcademica formacao, ExperienciaDesenvolvimento expDesenvolvimento) {
        super(pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getDataNascimento());
        this.formacaoAcademica = formacao;
        this.expDesenvolvimento = expDesenvolvimento;
    }

    public FormacaoAcademica getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public ExperienciaDesenvolvimento getExpDesenvolvimento() {
        return expDesenvolvimento;
    }

    public void setExpDesenvolvimento(ExperienciaDesenvolvimento expDesenvolvimento) {
        this.expDesenvolvimento = expDesenvolvimento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
