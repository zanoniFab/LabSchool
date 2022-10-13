import cli.Display;
import model.*;
import repository.RepositorioDados;

import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        RepositorioDados repositorioDados = new RepositorioDados();
        Display display = new Display();
        menuPrincipal(display, repositorioDados);
    }

    private static void menuPrincipal(Display display, RepositorioDados repositorioDados) {
        display.exibirMenuPrincipal();
        OpcoesMenu opcao = display.obterOpcao();
        while (opcao != OpcoesMenu.SAIR) {
            switch (opcao) {
                case INCLUIR_CADASTRO:
                    menuCadastro(display, repositorioDados);
                    break;
                case REGISTRAR_ATENDIMENTO:
                    int[] dadosRecebidos = display.receberDadosAtendimento();
                    repositorioDados.getAlunoPorId(dadosRecebidos[0]).addAtendimento();
                    repositorioDados.getFuncionarioPorId(dadosRecebidos[1]).contarAtendimento();
                    //atualizar lista Pessoas
                    System.out.println("Atendimento registrado com sucesso!");
                    break;
                case EMITIR_RELATORIOS:
                    menuEmitirRelatorio(display, repositorioDados);
                    break;
                case SAIR:
                    System.exit(0);
                    break;
                default:
                    menuPrincipal(display, repositorioDados);
                    break;
            }
            display.exibirMenuPrincipal();
            opcao = display.obterOpcao();

        }
    }

    private static void menuCadastro(Display display, RepositorioDados repositorioDados) {
        display.exibirMenuCadastro();
        OpcoesMenu opcao = display.obterOpcao();
        while (opcao != OpcoesMenu.VOLTAR) {
            switch (opcao) {
                case INCLUIR_ALUNO:
                    repositorioDados.addAluno(display.cadastrarAluno(display.cadastrarPessoa()));
                    break;
                case INCLUIR_PROFESSOR:
                    repositorioDados.addProfessor(display.cadastrarProfessor(display.cadastrarPessoa()));
                    break;
                case INCLUIR_FUNCIONARIO:
                    repositorioDados.addFuncionario(display.cadastrarFuncionario(display.cadastrarPessoa()));
                    break;
                case ALTERAR_SITUACAO_MATRICULA_ALUNO:
                    int[] dadosRecebidos = display.receberDadosAlteracaoMatricula();
                    Aluno aluno = repositorioDados.getAlunoPorId(dadosRecebidos[0]);
                    aluno.alterarSituacaoMatricula(SituacaoMatricula.obterCodigo(dadosRecebidos[1]));
                default:
                    menuCadastro(display, repositorioDados);
                    break;
            }
            display.exibirMenuCadastro();
            opcao = display.obterOpcao();
        }
        menuPrincipal(display, repositorioDados);
    }

    private static void menuEmitirRelatorio(Display display, RepositorioDados repositorioDados) {
        EmitirRelatorio opcaoRelatorio = EmitirRelatorio.obterCodigo(display.exibirMenuRelatorios());
        while (opcaoRelatorio != EmitirRelatorio.VOLTAR) {
            switch (opcaoRelatorio) {
                case ALUNOS:
                    menuRelatoriosAlunos(display, repositorioDados);

                    break;
                case PROFESSORES:
                    List<Professor> listaProfessores = repositorioDados.getListaProfessores();
                    for (Professor listaProfessore : listaProfessores) {
                        System.out.printf("ID: %d; Nome: %s; CPF: %d;", listaProfessore.getCodigo(), listaProfessore.getNome(), listaProfessore.getCpf());
                    }
                    break;
                case FUNCIONARIOS:
                    List<Funcionario> listaFuncionarios = repositorioDados.getListaFuncionarios();
                    for (Funcionario listaFuncionario : listaFuncionarios) {
                        System.out.printf("ID: %d; Nome: %s; CPF: %d;", listaFuncionario.getCodigo(), listaFuncionario.getNome(), listaFuncionario.getCpf());
                    }
                    break;
                case TODOS:
                    List<Pessoa> listaPessoas = repositorioDados.getListaPessoas();
                    for (Pessoa listaPessoa : listaPessoas) {
                        System.out.printf("ID: %d; Nome: %s; CPF: %d;", listaPessoa.getCodigo(), listaPessoa.getNome(), listaPessoa.getCpf());
                    }
                    break;
                case SAIR:
                    System.exit(0);
                    break;
                default:
                    menuEmitirRelatorio(display, repositorioDados);
                    break;
            }
            opcaoRelatorio = EmitirRelatorio.obterCodigo(display.exibirMenuRelatorios());
        }
        menuPrincipal(display, repositorioDados);

    }

    private static void menuRelatoriosAlunos(Display display, RepositorioDados repositorioDados) {
        RelatoriosAlunos opcaoRelatoriosAlunos = RelatoriosAlunos.obterCodigo(display.exibirMenuRelatoriosAlunos());
        while (opcaoRelatoriosAlunos != RelatoriosAlunos.VOLTAR) {
            switch (opcaoRelatoriosAlunos) {
                case LISTA_TODOS_ALUNOS:
                    for (Aluno listaAluno : repositorioDados.getListaAlunos()) {
                        System.out.printf("ID: %d; Nome: %s; CPF: %d;\n", listaAluno.getCodigo(), listaAluno.getNome(), listaAluno.getCpf());
                    }
                    break;
                case ALUNOS_SITUACAO_MATRICULA:
                    menuRelatoriosAlunosSituacaoMatricula(display, repositorioDados);
                    break;
                case ORDENADOS_TOTAL_ATENDIMENTO:

                    break;
                case SAIR:
                    System.exit(0);
                    break;
                default:
                    menuRelatoriosAlunos(display,repositorioDados);
                    break;
            }
            opcaoRelatoriosAlunos = RelatoriosAlunos.obterCodigo(display.exibirMenuRelatoriosAlunos());
        }
        menuEmitirRelatorio(display, repositorioDados);
    }

    private static void menuRelatoriosAlunosSituacaoMatricula(Display display, RepositorioDados repositorioDados) {
        SituacaoMatricula opcaoSituacao= SituacaoMatricula.obterCodigo(display.exibirMenuRelatorioSituacaoMatricula());
        while (opcaoSituacao != SituacaoMatricula.VOLTAR) {
            switch (opcaoSituacao) {
                case ATIVO:
                case IRREGULAR:
                case ATENDIMENTO_PEDAGOGICO:
                case INATIVO:
                    emitirRelatorioSituacaoMatricula(opcaoSituacao, repositorioDados);
                    break;
                case TODOS:
                    for (Aluno aluno : repositorioDados.getListaAlunos()) {
                        System.out.printf("ID: %d; Nome: %s; Nota: %f; Total Atendimentos Pedagogicos: %d;\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
                    }
                    break;
                case SAIR:
                    System.exit(0);
                    break;
                default:
                    menuRelatoriosAlunosSituacaoMatricula(display, repositorioDados);
                    break;
            }
            opcaoSituacao = SituacaoMatricula.obterCodigo(display.exibirMenuRelatorioSituacaoMatricula());
        }
        menuRelatoriosAlunos(display, repositorioDados);
    }

    private static void emitirRelatorioSituacaoMatricula(SituacaoMatricula opcaoInformada, RepositorioDados repositorioDados) {
        List<Aluno> listaAlunos = repositorioDados.getListaAlunos();
        for (Aluno aluno : listaAlunos) {
            if (opcaoInformada == aluno.getSituacaoMatricula()) {
                System.out.printf("ID: %d; Nome: %s; Nota: %f; Total Atendimentos Pedagogicos: %d;\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
            }
        }
    }
}





