package model;

public enum FormacaoAcademica {
    GRAD_INCOMPLETA,
    GRAD_COMPLETA,
    MESTRADO,
    DOUTORADO;

    public static OpcoesMenu obterCodigo(int opcao) {
        OpcoesMenu[] listaOpcoes = OpcoesMenu.values();
        return listaOpcoes[opcao - 1];
    }
}
