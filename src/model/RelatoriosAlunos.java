package model;

public enum RelatoriosAlunos {
    LISTA_TODOS_ALUNOS,
    ALUNOS_SITUACAO_MATRICULA,
    ORDENADOS_TOTAL_ATENDIMENTO,
    VOLTAR,
    SAIR;
    public static RelatoriosAlunos obterCodigo(int opcao) {
        RelatoriosAlunos[] listaOpcoes = RelatoriosAlunos.values();
        return listaOpcoes[opcao - 1];
    }
}
