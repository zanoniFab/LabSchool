package model;

public enum RelatoriosPessoas {
    ALUNOS,
    PROFESSORES,
    PEDAGOGOS,
    TODOS,
    VOLTAR,
    SAIR;
    public static RelatoriosPessoas obterCodigo(int opcao) {
        RelatoriosPessoas[] listaOpcoes = RelatoriosPessoas.values();
        return listaOpcoes[opcao - 1];
    }
}
