package model;

public enum EmitirRelatorio {
    ALUNOS,
    PROFESSORES,
    FUNCIONARIOS,
    TODOS,
    VOLTAR,
    SAIR;

    public static EmitirRelatorio obterCodigo(int opcao) {
        EmitirRelatorio[] listaOpcoes = EmitirRelatorio.values();
        return listaOpcoes[opcao - 1];
    }
}
