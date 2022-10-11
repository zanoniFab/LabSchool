package model;

public enum OpcoesMenu {
    INCLUIR_CADASTRO,
    REGISTRAR_ATENDIMENTO,
    EMITIR_RELATORIOS,
    INCLUIR_ALUNO,
    INCLUIR_PROFESSOR,
    INCLUIR_FUNCIONARIO,
    VOLTAR,
    SAIR;

    public static OpcoesMenu obterCodigo(int opcao) {
        OpcoesMenu[] listaOpcoes = OpcoesMenu.values();
        return listaOpcoes[opcao - 1];
    }
}
