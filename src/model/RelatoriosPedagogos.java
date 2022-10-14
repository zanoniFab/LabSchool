package model;

public enum RelatoriosPedagogos {
    TODOS_PEDAGOGOS,
    ORDENADOS_TOTAL_ATENDIMENTOS,
    VOLTAR,
    SAIR;
    public static RelatoriosPedagogos obterCodigo(int opcao) {
        RelatoriosPedagogos[] listaOpcoes = RelatoriosPedagogos.values();
        return listaOpcoes[opcao - 1];
    }
}
