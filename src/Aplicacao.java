import Exceptions.LabException;
import cli.Display;
import model.*;
import repository.RepositorioDados;

import java.util.Collections;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) throws LabException {
        RepositorioDados repositorioDados = new RepositorioDados();
        repositorioDados.testes();
        Display display = new Display();
        menuPrincipal(display, repositorioDados);
    }

    private static void menuPrincipal(Display display, RepositorioDados repositorioDados) throws LabException {
        display.exibirMenuPrincipal();
        OpcoesMenu opcao = OpcoesMenu.obterCodigo(display.receberOpcaoMenuPrincipal());
        while (opcao != OpcoesMenu.SAIR) {
            switch (opcao) {
                case INCLUIR_CADASTRO:
                    menuCadastro(display, repositorioDados);
                    break;
                case REGISTRAR_ATENDIMENTO: //RF05- REALIZAÇÃO ATENDIMENTO PEDAGÓGICO
                    registraAtendimento(display, repositorioDados);
                    break;
                case EMITIR_RELATORIOS:
                    menuEmitirRelatorio(display, repositorioDados);
                    break;
                default:
                    menuPrincipal(display, repositorioDados);
                    break;
            }
            display.exibirMenuPrincipal();
            opcao = OpcoesMenu.obterCodigo(display.receberOpcaoMenuPrincipal());
        }
        System.exit(0);
    }

    private static void registraAtendimento(Display display, RepositorioDados repositorioDados) throws LabException {
        int[] dadosRecebidos = display.receberDadosAtendimento();
        Aluno aluno = repositorioDados.getAlunoPorId(dadosRecebidos[0]);
        Pedagogo pedagogo = repositorioDados.getPedagogoPorId(dadosRecebidos[1]);
        if (aluno == null){
            display.printVermelhoMensagem("Aluno com id "+ dadosRecebidos[0] +" não encontrado!");
            registraAtendimento(display,repositorioDados);
        }else if(pedagogo == null){
            display.printVermelhoMensagem("Pedagogo com id "+ dadosRecebidos[1] + " não encontrado!");
            registraAtendimento(display,repositorioDados);
        }else {
            aluno.addAtendimento();
            aluno.alterarSituacaoMatricula(SituacaoMatricula.ATENDIMENTO_PEDAGOGICO);
            pedagogo.contarAtendimento();
            display.printVerdeMensagem("Atendimento registrado com sucesso!");
            display.aguarde();
        }
    }

    private static void menuCadastro(Display display, RepositorioDados repositorioDados) throws LabException {
        display.exibirMenuCadastro();
        OpcoesMenu opcao = OpcoesMenu.obterCodigo(display.receberOpcaoMenuCadastro());
        while (opcao != OpcoesMenu.VOLTAR) {
            switch (opcao) {
                case INCLUIR_ALUNO: //RF01 - CADASTRO DE ALUNO
                    repositorioDados.addAluno(display.cadastrarAluno(display.cadastrarPessoa(repositorioDados)));
                    display.aguarde();
                    break;
                case INCLUIR_PROFESSOR: //RF03 CADASTRO DE PROFESSOR
                    repositorioDados.addProfessor(display.cadastrarProfessor(display.cadastrarPessoa(repositorioDados)));
                    display.aguarde();
                    break;
                case INCLUIR_PEDAGOGO: //RF04-CADASTRO DE PEDAGOGO
                    repositorioDados.addFuncionario(display.cadastrarPedagogo(display.cadastrarPessoa(repositorioDados)));
                    display.aguarde();
                    break;
                case ALTERAR_SITUACAO_MATRICULA_ALUNO: //RF02 - ATUALIZAÇÃO DA SITUAÇÃO DA MATRÍCULA DE ALUNO
                    chamarAlterarSituacaoMatricula(display, repositorioDados);
                    display.aguarde();
                    break;
                default:
                    menuCadastro(display, repositorioDados);
                    break;
            }
            display.exibirMenuCadastro();
            opcao = OpcoesMenu.obterCodigo(display.receberOpcaoMenuCadastro());
        }
    }

    private static void chamarAlterarSituacaoMatricula(Display display, RepositorioDados repositorioDados) {
        Aluno aluno = repositorioDados.getAlunoPorId(display.receberAlunoAlteracaoMatricula());
        if (aluno == null){
            display.printVermelhoMensagem("Aluno não encontrado!");
            chamarAlterarSituacaoMatricula(display, repositorioDados);
        }else {
            if (display.confirmaAluno(aluno).equals("S")) {
                display.confirmacaoOperacao(aluno.alterarSituacaoMatricula
                        (SituacaoMatricula.obterCodigo(display.receberOpcaoAlteracaoMatricula())));
            } else {
                chamarAlterarSituacaoMatricula(display, repositorioDados);
            }
        }
    }

    private static void menuEmitirRelatorio(Display display, RepositorioDados repositorioDados) {
        EmitirRelatorio opcaoRelatorio = EmitirRelatorio.obterCodigo(display.exibirMenuRelatorios());
        while (opcaoRelatorio != EmitirRelatorio.VOLTAR) {
            switch (opcaoRelatorio) {
                case LISTAGEM_PESSOAS: //RF06- LISTAGEM DE PESSOAS
                    menuRelatoriosPessoas(display, repositorioDados);
                    break;
                case ALUNOS_SITUACAO_MATRICULA: //RF07-RELATORIO DOS ALUNOS
                    menuRelatoriosAlunosSituacaoMatricula(display, repositorioDados);
                    break;
                case PROFESSORES_EXPERIENCIA_DESENVOLVIMENTO: //RF08-RELATORIOS DOS PROFESSORES
                    menuRelatoriosProfessoresExperiencia(display, repositorioDados);
                    break;
                case ALUNOS_ORDENADOS_ATENDIMENTOS_PEDAGOGICOS: //RF09-RELATORIO DE ALUNOS COM MAIS ATENDIMENTOS PEDAGOGICOS
                    emitirRelatorioAtendimentoAluno(repositorioDados, display);
                    display.aguarde();
                    break;
                case PEDAGOGOS_ORDENADOS_ATENDIMENTO_PEDAGOGICO: //RF10-PEDAGOGOS COM MAIS ATENDIMENTOS PEDAGOGICOS
                    emitirRelatorioAtendimentoPedagogo(repositorioDados,display);
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

    private static void menuRelatoriosPessoas(Display display, RepositorioDados repositorioDados) {
        RelatoriosPessoas opcaoRelatorioPessoas = RelatoriosPessoas.obterCodigo(display.exibirMenuRelatorioPessoas());
        while (opcaoRelatorioPessoas != RelatoriosPessoas.VOLTAR) {
            switch (opcaoRelatorioPessoas) {
                case ALUNOS:
                    List<Aluno> listaAlunos = repositorioDados.getListaAlunos();
                    if (listaAlunos.isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Aluno aluno : listaAlunos) {
                            System.out.printf("[ID: %d; Nome: %s; CPF: %s;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getCpf());
                        }
                    }
                    display.aguarde();
                    break;
                case PROFESSORES:
                    List<Professor> listaProfessores = repositorioDados.getListaProfessores();
                    if (listaProfessores.isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Professor professor : listaProfessores) {
                            System.out.printf("[ID: %d; Nome: %s; CPF: %s;]\n", professor.getCodigo(), professor.getNome(), professor.getCpf());
                        }
                    }
                    display.aguarde();
                    break;
                case PEDAGOGOS:
                    List<Pedagogo> listaPedagogos = repositorioDados.getListaPedagogo();
                    if (listaPedagogos.isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Pedagogo pedagogo : listaPedagogos) {
                            System.out.printf("[ID: %d; Nome: %s; CPF: %s;]\n", pedagogo.getCodigo(), pedagogo.getNome(), pedagogo.getCpf());
                        }
                    }
                    display.aguarde();
                    break;
                case TODOS:
                    List<Pessoa> listaPessoas = repositorioDados.getListaPessoas();
                    if (listaPessoas.isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Pessoa pessoa : listaPessoas) {
                            System.out.printf("[ID: %d; Nome: %s; CPF: %s;]\n", pessoa.getCodigo(), pessoa.getNome(), pessoa.getCpf());
                        }
                    }
                    display.aguarde();
                    break;
                case SAIR:
                    System.exit(0);
                    break;
            }
            opcaoRelatorioPessoas = RelatoriosPessoas.obterCodigo(display.exibirMenuRelatorioPessoas());
        }
    }

    private static void menuRelatoriosAlunosSituacaoMatricula(Display display, RepositorioDados repositorioDados) {
        SituacaoMatricula opcaoSituacao = SituacaoMatricula.obterCodigo(display.exibirMenuRelatorioSituacaoMatricula());
        while (opcaoSituacao != SituacaoMatricula.VOLTAR) {
            switch (opcaoSituacao) {
                case ATIVO:
                case IRREGULAR:
                case ATENDIMENTO_PEDAGOGICO:
                case INATIVO:
                    emitirRelatorioSituacaoMatricula(opcaoSituacao, repositorioDados, display);
                    display.aguarde();
                    break;
                case TODOS:
                    if (repositorioDados.getListaAlunos().isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Aluno aluno : repositorioDados.getListaAlunos()) {
                            System.out.printf("[ID: %d; Nome: %s; Nota: %.2f; Total Atendimentos Pedagogicos: %d;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
                        }
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
    }

    private static void emitirRelatorioSituacaoMatricula(SituacaoMatricula opcaoInformada, RepositorioDados repositorioDados, Display display) {
        List<Aluno> listaAlunos = repositorioDados.getListaAlunos();
        if (listaAlunos.isEmpty()) {
            display.exibirMensagem();
        } else {
            for (Aluno aluno : listaAlunos) {
                if (opcaoInformada == aluno.getSituacaoMatricula()) {
                    System.out.printf("[ID: %d; Nome: %s; Nota: %.2f; Total Atendimentos Pedagogicos: %d;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getNota(), aluno.getAtendimentosPedagogicos());
                }
            }
        }
    }

    private static void menuRelatoriosProfessoresExperiencia(Display display, RepositorioDados repositorioDados) {
        ExperienciaDesenvolvimento opcaoExperiencia = ExperienciaDesenvolvimento.obterCodigo(display.exibirMenuRelatoriosProfessores());
        while (opcaoExperiencia != ExperienciaDesenvolvimento.VOLTAR) {
            switch (opcaoExperiencia) {
                case FRONT_END:
                case BACK_END:
                case FULL_STACK:
                    emitirRelatorioExperiencia(opcaoExperiencia, repositorioDados, display);
                    display.aguarde();
                    break;
                case TODOS:
                    List<Professor> listaProfessor = repositorioDados.getListaProfessores();
                    if (listaProfessor.isEmpty()) {
                        display.exibirMensagem();
                    } else {
                        for (Professor professor : listaProfessor) {
                            System.out.printf("[ID: %d; Nome: %s; Formação acadêmica: %s; Experiência: %s; Estado: %s;]\n", professor.getCodigo(), professor.getNome(), professor.getFormacaoAcademica(), professor.getExpDesenvolvimento(), professor.getEstado());
                        }
                    }
                    display.aguarde();
                    break;
                case SAIR:
                    System.exit(0);
            }
            opcaoExperiencia = ExperienciaDesenvolvimento.obterCodigo(display.exibirMenuRelatoriosProfessores());
        }
    }

    private static void emitirRelatorioExperiencia(ExperienciaDesenvolvimento opcaoExperiencia, RepositorioDados repositorioDados, Display display) {
        List<Professor> listaProfessores = repositorioDados.getListaProfessores();
        if (listaProfessores.isEmpty()) {
            display.exibirMensagem();
        } else {
            for (Professor professor : listaProfessores) {
                if (opcaoExperiencia == professor.getExpDesenvolvimento()) {
                    System.out.printf("[ID: %d; Nome: %s; Formação acadêmica: %s; Experiência: %s; Estado: %s;]\n", professor.getCodigo(), professor.getNome(), professor.getFormacaoAcademica(), professor.getExpDesenvolvimento(), professor.getEstado());
                }
            }
        }
    }

    private static void emitirRelatorioAtendimentoAluno(RepositorioDados repositorioDados, Display display) {
        List<Aluno> listaAlunos = repositorioDados.getListaAlunos();
        if (listaAlunos.isEmpty()) {
            display.exibirMensagem();
        } else {
            Collections.sort(listaAlunos);
            for (Aluno aluno : listaAlunos) {
                System.out.printf("[ID: %d; Nome: %s; Total Atendimentos: %d;]\n", aluno.getCodigo(), aluno.getNome(), aluno.getAtendimentosPedagogicos());
            }
        }
    }

    private static void emitirRelatorioAtendimentoPedagogo(RepositorioDados repositorioDados, Display display) {
        List<Pedagogo> listaPedagogo = repositorioDados.getListaPedagogo();
        if (listaPedagogo.isEmpty()) {
            display.exibirMensagem();
        } else {
            Collections.sort(listaPedagogo);
            for (Pedagogo pedagogo : listaPedagogo) {
                System.out.printf("[ID: %d; Nome: %s; Total Atendimentos: %d;]\n", pedagogo.getCodigo(), pedagogo.getNome(), pedagogo.getTotalAtendimentos());
            }
        }
    }


}