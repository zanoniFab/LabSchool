package model;

public enum FormacaoAcademica {
    GRAD_INCOMPLETA,
    GRAD_COMPLETA,
    MESTRADO,
    DOUTORADO;

    public static FormacaoAcademica obterCodigo(int opcao) {
        FormacaoAcademica[] listaOpcoes = FormacaoAcademica.values();
        return listaOpcoes[opcao - 1];
    }
}
