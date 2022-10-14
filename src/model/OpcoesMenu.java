package model;

public enum OpcoesMenu {
    INCLUIR_CADASTRO, //1
    REGISTRAR_ATENDIMENTO, //2
    EMITIR_RELATORIOS, //3
    INCLUIR_ALUNO, //4
    INCLUIR_PROFESSOR, //5
    INCLUIR_PEDAGOGO, //6
    ALTERAR_SITUACAO_MATRICULA_ALUNO, //7
    VOLTAR,//8
    SAIR;//9

    public static OpcoesMenu obterCodigo(int opcao) {
        OpcoesMenu[] listaOpcoes = OpcoesMenu.values();
        return listaOpcoes[opcao - 1];
    }
}
