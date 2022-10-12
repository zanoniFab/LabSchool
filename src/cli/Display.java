package cli;

import model.*;
import repository.RepositorioDados;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Display {

    public void exibirMenuPrincipal() {
        System.out.printf("(Menu Principal)\n1-Incluir/Alterar Cadastro;\n2-Registrar Atendimento;\n3-Emitir Relatórios\n9-Sair;\n");
    }

    public void exibirMenuCadastro() {
        System.out.printf("(Menu Cadastro)\n4-Incluir Aluno;\n5-Incluir Professor;\n6-Incluir Funcionário;\n7-Alterar Situação Matricula Aluno;\n8-Voltar\n9-Sair\n");
    }

    public OpcoesMenu obterOpcao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a opção desejada: ");
        int opcaoInformada = scanner.nextInt();
        OpcoesMenu opcao = OpcoesMenu.obterCodigo(opcaoInformada);
        return opcao;
    }

    public Pessoa cadastrarPessoa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o telefone: ");
        Long telefone = scanner.nextLong();
        System.out.print("Informe a data de nascimento (DD/MM/AAAA): ");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDeNascimento = LocalDate.parse(scanner.next(), formatador);
        System.out.print("Informe o CPF (somente números): ");
        Long cpf = scanner.nextLong();
        return new Pessoa(nome, cpf, telefone, dataDeNascimento);
    }

    public Aluno cadastrarAluno(Pessoa pessoa) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a nota do aluno: ");
        float nota = scanner.nextFloat();
        System.out.print("Informe a situação da matrícula do aluno (1-Ativa; 2-Irregular; 3- Atendimento Pedagogico; 4-Inativo): ");
        int opcao = scanner.nextInt();
        SituacaoMatricula situacaoMatricula = SituacaoMatricula.obterCodigo(opcao);
        Aluno aluno = new Aluno(pessoa, nota, situacaoMatricula);
        System.out.printf("Cadastro realizado com sucesso! ID ALUNO: %d\n", aluno.getCodigo());
        return aluno;
    }

    public Professor cadastrarProfessor(Pessoa pessoa) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a formação academica (1-Graduação incompleta; 2-Graduação completa; 3-Mestrado; 4-Doutorado;): ");
        int opcao = scanner.nextInt();
        FormacaoAcademica formacao = FormacaoAcademica.obterCodigo(opcao);
        System.out.print("Informe a experiencia em desenvolvimento (1-Front-end; 2-Back-end; 3-Full-stack): ");
        opcao = scanner.nextInt();
        ExperienciaDesenvolvimento expDesenvolvimento = ExperienciaDesenvolvimento.obterCodigo(opcao);
        System.out.println("Informe o estado do professor Ativo/Inativo (A/I): ");
        String opcaoEstado = scanner.next().toLowerCase();
        boolean estado = false;
        if (opcaoEstado.equals("a")) {
            estado = true;
        } else if (opcaoEstado.equals("i")) {
            estado = false;
        }
        Professor professor = new Professor(pessoa, formacao, expDesenvolvimento, estado);
        System.out.printf("Cadastro realizado com sucesso! ID PROFESSOR: %d\n", professor.getCodigo());
        return professor;
    }

    public Funcionario cadastrarFuncionario(Pessoa pessoa) {
        Funcionario funcionario = new Funcionario(pessoa);
        System.out.printf("Cadastro realizado com sucesso! ID FUNCIONARIO: %d\n", funcionario.getCodigo());
        return funcionario;
    }

    public int[] receberDadosAtendimento() {
        int[] ids = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o ID do Aluno: ");
        ids[0] = scanner.nextInt();
        System.out.print("Informe o ID do Pedagogo: ");
        ids[1] = scanner.nextInt();
        return ids;
    }

    public int[] receberDadosAlteracaoMatricula() {
        int[] dados = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o ID do aluno: ");
        dados[0] = scanner.nextInt();
        System.out.println("Informe qual a situação atual da matrícula (1-Ativo; 2-Irregular; 3-Atendimento Pedagogico; 4-Inativo): ");
        dados[1] = scanner.nextInt();
        return dados;
    }

    public int exibirMenuRelatorios() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("(Menu Relatorios)\nInforme qual categoria você deseja no relatório: \n1-Alunos;\n2-Professores;\n3-Funcionarios;\n4-Todos;\n5-Voltar;\n6-Sair\n");
        int opcao = scanner.nextInt();
        return opcao;
    }
}
