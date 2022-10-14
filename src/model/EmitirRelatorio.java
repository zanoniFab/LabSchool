package model;

public enum EmitirRelatorio {
    LISTAGEM_PESSOAS,
    ALUNOS_SITUACAO_MATRICULA,
    PROFESSORES_EXPERIENCIA_DESENVOLVIMENTO,
    ALUNOS_ORDENADOS_ATENDIMENTOS_PEDAGOGICOS,
    PEDAGOGOS_ORDENADOS_ATENDIMENTO_PEDAGOGICO,
    VOLTAR,
    SAIR;

    public static EmitirRelatorio obterCodigo(int opcao) {
        EmitirRelatorio[] listaOpcoes = EmitirRelatorio.values();
        return listaOpcoes[opcao - 1];
    }
}
