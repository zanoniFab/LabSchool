package model;

public enum ExperienciaDesenvolvimento {
    FRONT_END,
    BACK_END,
    FULL_STACK;

    public static ExperienciaDesenvolvimento obterCodigo(int opcao) {
        ExperienciaDesenvolvimento[] listaOpcoes = ExperienciaDesenvolvimento.values();
        return listaOpcoes[opcao - 1];
    }
}
