import cli.Display;
import model.*;
import repository.RepositorioDados;

import java.util.Collections;
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
                    System.out.print("Atendimento registrado com sucesso!");
                    display.aguarde();
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
                    display.aguarde();
                    break;
                case INCLUIR_PROFESSOR:
                    repositorioDados.addProfessor(display.cadastrarProfessor(display.cadastrarPessoa()));
                    display.aguarde();
                    break;
                case INCLUIR_FUNCIONARIO:
                    repositorioDados.addFuncionario(display.cadastrarFuncionario(display.cadastrarPessoa()));
                    display.aguarde();
                    break;
                case ALTERAR_SITUACAO_MATRICULA_ALUNO:
                    int[] dadosRecebidos = display.receberDadosAlteracaoMatricula();
                    Aluno aluno = repositorioDados.getAlunoPorId(dadosRecebidos[0]);
                    aluno.alterarSituacaoMatricula(SituacaoMatricula.obterCodigo(dadosRecebidos[1]));
                    display.aguarde();
                    break;
                    //atualizar lista pessoa
                default:
                    menuCadastro(display, repositorioDados);
                    break;
            }
            display.exibirMenuCadastro();
            opcao = display.obterOpcao();
        }
    }

    private static void menuEmitirRelatorio(Display display, RepositorioDados repositorioDados) {
        EmitirRelatorio opcaoRelatorio = EmitirRelatorio.obterCodigo(display.exibirMenuRelatorios());
        while (opcaoRelatorio != EmitirRelatorio.VOLTAR) {
            switch (opcaoRelatorio) {
                case ALUNOS:
                    menuRelatoriosAlunos(display, repositorioDados);
                    break;
                case PROFESSORES:
                    menuRelatoriosProfessores(display,repositorioDados);
                    break;
                case FUNCIONARIOS:
                    menuRelatoriosFuncionarios(display,repositorioDados);
                    display.aguarde();
                    break;
                case TODOS:
                    List<Pessoa> listaPessoas = repositorioDados.getListaPessoas();
                    for (Pessoa listaPessoa : listaPessoas) {
                        System.out.printf("[ID: %d; Nome: %s; CPF: %d;]", listaPessoa.getCodigo(), listaPessoa.getNome(), listaPessoa.getCpf());
                    }
                    display.aguarde();
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
    }

    private static void menuRelatoriosFuncionarios(Display display, RepositorioDados repositorioDados) {
        RelatoriosFuncionarios opcaoRelatorioFuncionario = RelatoriosFuncionarios.obterCodigo(display.exibirMenuRelatoriosFuncionarios());
        while (opcaoRelatorioFuncionario != RelatoriosFuncionarios.VOLTAR){
            switch (opcaoRelatorioFuncionario){
                case TODOS_PEDAGOGOS:
                    for (Funcionario listaFuncionario : repositorioDados.getListaFuncionarios()) {
                        System.out.printf("[ID: %d; Nome: %s; CPF: %d;]", listaFuncionario.getCodigo(), listaFuncionario.getNome(), listaFuncionario.getCpf());
                    }
                    display.aguarde();
                    break;
                case ORDENADOS_TOTAL_ATENDIMENTOS:
                    emitirRelatorioAtendimentoFuncionario(repositorioDados);
                    display.aguarde();
                    break;
                case SAIR:
                    System.exit(0);
                    break;
            }
            opcaoRelatorioFuncionario = RelatoriosFuncionarios.obterCodigo(display.exibirMenuRelatoriosFuncionarios());
        }
        menuEmitirRelatorio(display,repositorioDados);
    }

    private static void menuRelatoriosAlunos(Display display, RepositorioDados repositorioDados) {
        RelatoriosAlunos opcaoRelatoriosAlunos = RelatoriosAlunos.obterCodigo(display.exibirMenuRelatoriosAlunos());
        while (opcaoRelatoriosAlunos != RelatoriosAlunos.VOLTAR) {
            switch (opcaoRelatoriosAlunos) {
                case LISTA_TODOS_ALUNOS:
                    for (Aluno listaAluno : repositorioDados.getListaAlunos()) {
                        System.out.printf("[ID: %d; Nome: %s; CPF: %d;]\n", listaAluno.getCodigo(), listaAluno.getNome(), listaAluno.getCpf());
                    }
                    display.aguarde();
                    break;
                case ALUNOS_SITUACAO_MATRICULA:
                    menuRelatoriosAlunosSituacaoMatricula(display, repositorioDados);
                    display.aguarde();
                    break;
                case ORDENADOS_TOTAL_ATENDIMENTO:
                    emitirRelatorioAtendimentoAluno(repositorioDados);
                    display.aguarde();
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
                    display.aguarde();
                    break;
                case TODOS:
                    for (Aluno aluno : repositorioDados.getListaAlunos()) {
                        System.out.printf("[ID: %d; Nome: %s; Nota: %.2f; Total Atendimentos Pedagogicos: %d;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
                    }
                    display.aguarde();
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
                System.out.printf("[ID: %d; Nome: %s; Nota: %.2f; Total Atendimentos Pedagogicos: %d;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
            }
        }
    }
    private static void menuRelatoriosProfessores(Display display, RepositorioDados repositorioDados) {
        RelatoriosProfessores opcaoRelatorioProfessor = RelatoriosProfessores.obterCodigo(display.exibirMenuRelatoriosProfessores());
        while (opcaoRelatorioProfessor != RelatoriosProfessores.VOLTAR){
            switch (opcaoRelatorioProfessor){
                case TODOS_PROFESSORES:
                    List<Professor> listaProfessores = repositorioDados.getListaProfessores();
                    for (Professor listaProfessore : listaProfessores) {
                        System.out.printf("[ID: %d; Nome: %s; CPF: %d;]", listaProfessore.getCodigo(), listaProfessore.getNome(), listaProfessore.getCpf());
                    }
                    display.aguarde();
                    break;
                case EXP_DESENVOLVIMENTO:
                    menuRelatoriosProfessoresExperiencia(display,repositorioDados);
                    break;
                case SAIR:
                    System.exit(0);
                default:
                    menuRelatoriosProfessores(display, repositorioDados);
                    break;
            }
            opcaoRelatorioProfessor = RelatoriosProfessores.obterCodigo(display.exibirMenuRelatoriosProfessores());
        }
        menuEmitirRelatorio(display,repositorioDados);
    }
    private static void menuRelatoriosProfessoresExperiencia(Display display, RepositorioDados repositorioDados) {
        ExperienciaDesenvolvimento opcaoExperiencia = ExperienciaDesenvolvimento.obterCodigo(display.exibirMenuRelatoriosProfessores());
        while (opcaoExperiencia != ExperienciaDesenvolvimento.VOLTAR){
            switch (opcaoExperiencia){
                case FRONT_END:
                case BACK_END:
                case FULL_STACK:
                    emitirRelatorioExperiencia(opcaoExperiencia,repositorioDados);
                case TODOS:
                    List<Professor> listaProfessor = repositorioDados.getListaProfessores();
                    for(Professor professor : listaProfessor){
                        System.out.printf("[ID: %d; Nome: %s; Formação acadêmica: %s; Experiência: %s; Estado: %s;]\n", professor.getCodigo(),professor.getNome(),professor.getFormacaoAcademica(),professor.getExpDesenvolvimento(),professor.getEstado());
                    }
                    display.aguarde();
                    break;
                case SAIR:
                    System.exit(0);
            }
            opcaoExperiencia = ExperienciaDesenvolvimento.obterCodigo(display.exibirMenuRelatoriosProfessores());
        }
        menuRelatoriosProfessores(display,repositorioDados);

    }
    private static void emitirRelatorioExperiencia(ExperienciaDesenvolvimento opcaoExperiencia, RepositorioDados repositorioDados) {
        List<Professor> listaProfessores = repositorioDados.getListaProfessores();
        for (Professor professor : listaProfessores) {
            if (opcaoExperiencia == professor.getExpDesenvolvimento()) {
                System.out.printf("[ID: %d; Nome: %s; Formação acadêmica: %s; Experiência: %s; Estado: %s;]\n", professor.getCodigo(),professor.getNome(),professor.getFormacaoAcademica(),professor.getExpDesenvolvimento(),professor.getEstado());
            }
        }
    }
    private static void emitirRelatorioAtendimentoAluno (RepositorioDados repositorioDados){
        List<Aluno> listaAlunos = repositorioDados.getListaAlunos();
        Collections.sort(listaAlunos);
        for (Aluno aluno : listaAlunos){
            System.out.printf("[ID: %d; Nome: %s; Total Atendimentos: %d;]\n",aluno.getCodigo(),aluno.getNome(),aluno.getAtendimentosPedagogicos());
        }
    }
    private static void emitirRelatorioAtendimentoFuncionario (RepositorioDados repositorioDados){
        List<Funcionario> listaFuncionarios = repositorioDados.getListaFuncionarios();
        Collections.sort(listaFuncionarios);
        for (Funcionario funcionario : listaFuncionarios){
            System.out.printf("[ID: %d; Nome: %s; Total Atendimentos: %d;]\n",funcionario.getCodigo(),funcionario.getNome(),funcionario.getTotalAtendimentos());
        }
    }



}