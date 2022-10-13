package model;

public enum RelatoriosProfessores {
    TODOS_PROFESSORES,
    EXP_DESENVOLVIMENTO,
    VOLTAR,
    SAIR;
    public static RelatoriosProfessores obterCodigo(int opcao) {
        RelatoriosProfessores[] listaOpcoes = RelatoriosProfessores.values();
        return listaOpcoes[opcao - 1];
    }
}
