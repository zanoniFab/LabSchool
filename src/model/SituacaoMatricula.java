package model;

public enum SituacaoMatricula {
    ATIVO,
    IRREGULAR,
    ATENDIMENTO_PEDAGOGICO,
    INATIVO,
    TODOS,
    VOLTAR,
    SAIR;

    public static SituacaoMatricula obterCodigo(int opcao) {
        SituacaoMatricula[] listaOpcoes = SituacaoMatricula.values();
        return listaOpcoes[opcao - 1];
    }

}