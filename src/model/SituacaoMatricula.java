package model;

public enum SituacaoMatricula {
    ATIVO,
    IRREGULAR,
    ATENDIMENTO_PEDAGOGICO,
    INATIVO;

    public static SituacaoMatricula obterCodigo(int opcao) {
        SituacaoMatricula[] listaOpcoes = SituacaoMatricula.values();
        return listaOpcoes[opcao - 1];
    }
}