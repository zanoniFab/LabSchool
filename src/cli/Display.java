package cli;

import model.Aluno;
import model.OpcoesMenu;
import model.Pessoa;
import model.SituacaoMatricula;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Display {

    public void exibirMenuPrincipal() {
        System.out.printf("1-Incluir/Alterar Cadastro;\n2-Registrar Atendimento;\n3-Emitir Relatórios\n8-Sair;\n");
    }

    public void exibirMenuCadastro() {
        System.out.printf("\n4-Incluir/Alterar Aluno;\n5-Incluir Professor;\n6-Incluir Funcionário;\n7-Voltar\n");
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
        System.out.println("Informe o nome: ");
        String nome = scanner.nextLine();
        System.out.println("Informe o telefone: ");
        Long telefone = scanner.nextLong();
        System.out.println("Informe a data de nascimento (DD/MM/AAAA): ");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDeNascimento = LocalDate.parse(scanner.next(), formatador);
        System.out.println("Informe o CPF (somente números): ");
        Long cpf = scanner.nextLong();
        return new Pessoa(nome, cpf, telefone, dataDeNascimento);
    }

    public Aluno cadastrarAluno(Pessoa pessoa) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a nota do aluno: ");
        float nota = scanner.nextFloat();
        System.out.println("Informe a situação da matrícula do aluno: \n1-Ativa;\n2- Irregular;\n3- Atendimento Pedagogico;\n4-Inativo;");
        int opcao = scanner.nextInt();
        SituacaoMatricula situacaoMatricula = SituacaoMatricula.obterCodigo(opcao);
        System.out.println("Cadastro realizado com sucesso");

        return new Aluno(pessoa, nota, situacaoMatricula);

    }


}
// cadastrar um atendimento: deve perguntar qual pedagogo e qual aluno envolvido no atendimento