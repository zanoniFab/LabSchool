package model;

public enum RelatoriosFuncionarios {
    TODOS_PEDAGOGOS,
    ORDENADOS_TOTAL_ATENDIMENTOS,
    VOLTAR,
    SAIR;
    public static RelatoriosFuncionarios obterCodigo(int opcao) {
        RelatoriosFuncionarios[] listaOpcoes = RelatoriosFuncionarios.values();
        return listaOpcoes[opcao - 1];
    }
}
