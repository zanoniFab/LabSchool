package model;

public enum EmitirRelatorio {
    ALUNOS,
    PROFESSORES,
    PEDAGOGOS,
    TODOS;
    public static EmitirRelatorio obterCodigo(int opcao) {
        EmitirRelatorio[] listaOpcoes = EmitirRelatorio.values();
        return listaOpcoes[opcao - 1];
    }
}
