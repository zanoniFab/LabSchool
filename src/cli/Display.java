package cli;

import Exceptions.LabException;
import model.*;
import repository.RepositorioDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Display {

    public void exibirMenuPrincipal() {
        System.out.print("\n(Menu Principal)\n1-Incluir/Alterar Cadastro;\n2-Registrar Atendimento;\n3-Emitir Relatórios\n9-Sair;\n");
    }

    public void exibirMenuCadastro() {
        System.out.print("\n(Menu Cadastro)\n4-Incluir Aluno;\n5-Incluir Professor;\n6-Incluir Pedagogo;\n7-Alterar Situação Matricula Aluno;\n8-Voltar\n9-Sair\n");
    }


    public OpcoesMenu obterOpcao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a opção desejada: ");
        int opcaoInformada = scanner.nextInt();
        return OpcoesMenu.obterCodigo(opcaoInformada);
    }

    public Pessoa cadastrarPessoa(RepositorioDados repositorioDados) throws LabException {
        String nome = receberNome();
        String cpf = receberCpf();
        Long telefone = receberTelefone();
        LocalDate dataDeNascimento = receberDataNascimento();
        return new Pessoa(repositorioDados.criarCodigo(), nome, cpf, telefone, dataDeNascimento);
    }

    public Aluno cadastrarAluno(Pessoa pessoa) {
        float nota = receberNota();
        SituacaoMatricula situacaoMatricula = SituacaoMatricula.obterCodigo(receberOpcao());
        Aluno aluno = new Aluno(pessoa, nota, situacaoMatricula);
        System.out.printf("Cadastro realizado com sucesso! ID ALUNO: %d\n", aluno.getCodigo());
        return aluno;
    }

    public Professor cadastrarProfessor(Pessoa pessoa) {
        FormacaoAcademica formacao = FormacaoAcademica.obterCodigo(receberOpcaoFormacao());
        ExperienciaDesenvolvimento expDesenvolvimento = ExperienciaDesenvolvimento.obterCodigo(receberOpcaoExp());
        String opcaoEstado = receberOpcaoAtividade();
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

    public Pedagogo cadastrarPedagogo(Pessoa pessoa) {
        Pedagogo pedagogo = new Pedagogo(pessoa);
        System.out.printf("Cadastro realizado com sucesso! ID FUNCIONARIO: %d\n", pedagogo.getCodigo());
        return pedagogo;
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
    public int exibirMenuRelatorios() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n(Menu Relatorios)\nEscolha qual relat[orio você deseja emitir:\n1-Listagem com todas as pessoas (ID, NOME, CPF)\n2-Relatorio alunos por situação de matricula;\n");
        System.out.print("3-Relatório de Professores por experiencia em desenvolvimento;\n4-Relatórios de alunos por numero de atendimentos;\n5-Relatório de pedagogos por número de atendimentos;\n");
        System.out.print("6-Voltar;\n7-Sair\n");
        System.out.print("Informe a opção desejada: ");
        return scanner.nextInt();
    }

    public int exibirMenuRelatorioSituacaoMatricula() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nRelatório de Alunos por Situação de Matrícula:\n1-Ativo;\n2-Irregular;\n3-Atendimento Pedagógico;\n4-Inativo;\n5-Todos;\n6-Voltar;\n7-Sair\n");
        System.out.print("Informe a opção desejada: ");
        return scanner.nextInt();
    }

    public int exibirMenuRelatoriosProfessores() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n(Relatorios Professores EXP)\nInforme a opção desejada:\n1-FRONT-END;\n2-BACK-END;\n3-FULL-STACK;\n4-Todos;\n");
        System.out.print("5-Voltar;\n6-Sair\n");
        System.out.print("Informe a opção desejada: ");
        return scanner.nextInt();
    }

    public void aguarde() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPressione a tecla enter para continuar...\n");
        scanner.nextLine();
    }

    public int exibirMenuRelatorioPessoas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n(Relatorios Pessoas)\nQual categoria você deseja no relatório?\n1-Alunos;\n2-Professores;\n3-Pedagogos;\n4-Todos;\n5-Voltar;\n6-Sair;\n");
        System.out.print("Informe a opção desejada: ");
        return scanner.nextInt();
    }
    public void exibirMensagem(){
        System.out.print("Não há nada para mostrar.");
    }

    private String receberNome() {
        try {
            String nome = validarNome();
            return nome;
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberNome();
        }
    }

    private static String validarNome() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o nome: ");
        String nomeInformado = scanner.nextLine();
        if (nomeInformado.matches("[a-zA-Z\\s]+")) {
            return nomeInformado;
        } else {
            throw new LabException("Nome inválido! Informe somente letras.\n");
        }
    }

    private static String receberCpf() {
        try {
            return validarCpf();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberCpf();
        }
    }

    private static String validarCpf() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o CPF (somente números): ");
        String cpfInformado = scanner.nextLine();
        if (cpfInformado.length() == 11 && cpfInformado.matches("^\\d+$")) {
            return cpfInformado;
        } else {
            throw new LabException("CPF inválido! Tente novamente.\n");
        }
    }

    private Long receberTelefone() {
        try {
            String telefoneInformado = validarTelefone();
            return Long.valueOf(telefoneInformado);
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberTelefone();
        }
    }

    private static String validarTelefone() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o telefone (somente números): ");
        String telefoneInformado = scanner.nextLine();
        if (telefoneInformado.matches("^\\d+$")) {
            return telefoneInformado;
        } else {
            throw new LabException("Número inválido! Informe somente números.\n");
        }
    }

    private static LocalDate receberDataNascimento() throws LabException {
        try {
            String dataNascimento = validarDataNascimento();
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataNascimento, formatador);

        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberDataNascimento();
        }
    }

    private static String validarDataNascimento() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a data de nascimento (DD/MM/AAAA): ");
        String dataInformada = scanner.nextLine();
        if (dataInformada.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            return dataInformada;
        } else {
            throw new LabException("Informe somente números separados por /\n");
        }
    }

    private static float receberNota() {
        try {
            return validarNota();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberNota();
        }

    }

    private static float validarNota() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a nota do aluno (n.n): ");
        String nota = scanner.nextLine();
        if (nota.matches("([\\d.]+\\d)$") && Float.parseFloat(nota) <= 10.0) {
            return Float.parseFloat(nota);
        } else {
            throw new LabException("Nota inválida! Informe uma nota de 0 a 10 no formato \"n.n\".\n");
        }
    }

    private static int receberOpcao() {
        try {
            return validarOpcao();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcao();
        }
    }

    private static int validarOpcao() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a situação da matrícula do aluno (1-Ativa; 2-Irregular; 3- Atendimento Pedagogico; 4-Inativo): ");
        String opcao = scanner.nextLine();
        if (opcao.matches("1|2|3|4")) {
            return Integer.parseInt(opcao);
        } else {
            throw new LabException("Código inválido!\n");
        }
    }

    private int receberOpcaoFormacao() {
        try {
            return validarOpcaoFormacao();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoFormacao();
        }
    }

    private int validarOpcaoFormacao() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a formação academica (1-Graduação incompleta; 2-Graduação completa; 3-Mestrado; 4-Doutorado;): ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("1|2|3|4")) {
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException("Opção inválida!\n");
        }
    }

    private int receberOpcaoExp() {
        try {
            return validarOpcaoExp();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoExp();
        }
    }

    private int validarOpcaoExp() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe a experiencia em desenvolvimento (1-Front-end; 2-Back-end; 3-Full-stack): ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("1|2|3")) {
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException("Opção inválida!");
        }
    }

    private String receberOpcaoAtividade() {
        try {
            return validarOpcaoAtividade();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoAtividade();
        }
    }

    private String validarOpcaoAtividade() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o estado do professor Ativo/Inativo (A/I): ");
        String opcaoInformada = scanner.nextLine().toLowerCase();
        if (opcaoInformada.matches("a|i")) {
            return opcaoInformada;
        } else {
            throw new LabException("Opção inválida! Informe A para ativo ou I para Inativo.");
        }
    }

    public void confirmacaoOperacao(boolean executou) {
        if (executou) {
            System.out.print("Operação realizada com sucesso!");
        } else {
            System.out.print("Algo de errado aconteceu...");
        }
    }

    public int receberOpcaoMenuPrincipal() {
        try {
            return validarOpcaoMenuPrincipal();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoMenuPrincipal();
        }
    }

    private static int validarOpcaoMenuPrincipal() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a opção desejada: ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("1|2|3|9")) {
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException("Opção inválida! Tente novamente.");
        }
    }

    public int receberOpcaoMenuCadastro() {
        try {
            return validarOpcaoMenuCadastro();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoMenuCadastro();
        }

    }

    private static int validarOpcaoMenuCadastro() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a opção desejada: ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("4|5|6|7|8|9")) {
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException("Opção inválida! Tente novamente.");
        }
    }
    public int receberAlunoAlteracaoMatricula() {
        try {
            return validarIdAluno();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberAlunoAlteracaoMatricula();
        }
    }

    private int validarIdAluno() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o ID do aluno: ");
        String idAluno = scanner.nextLine();
        if (idAluno.matches("[0-9]")) {
            return Integer.parseInt(idAluno);
        } else {
            throw new LabException("ID inválido. Informe somente números.\n");
        }
    }

    public String confirmaAluno(Aluno aluno) {
        System.out.printf("O aluno informado foi:\n[ID: %d; Nome: %s; CPF: %s; Situação Matrícula: %s]\n",aluno.getCodigo(),aluno.getNome(),aluno.getCpf(),aluno.getSituacaoMatricula());
        return receberOpcaoConfirmacao();
    }

    private static String receberOpcaoConfirmacao() {
        try {
            return validarOpcaoConfirmacao();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoConfirmacao();
        }
    }

    private static String validarOpcaoConfirmacao() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nConfirma alteração [S/N]? ");
        String opcaoInformada = scanner.nextLine().toUpperCase();
        if (opcaoInformada.matches("S|N")) {
            return opcaoInformada;
        } else {
            throw new LabException("Opção inválida! Pressione S para confirmar alteração ou N para alterar o aluno.");
        }

    }

    public int receberOpcaoAlteracaoMatricula() {
        try {
            return validarOpcaoAlteracaoMatricula();
        } catch (LabException e){
            System.out.print(e.getMessage());
            return receberOpcaoAlteracaoMatricula();
        }
    }
    private int validarOpcaoAlteracaoMatricula() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPara qual situação você deseja alterar:\n");
        System.out.print("1- ATIVO;\n2-IRREGULAR;\n3-ATENDIMENTO PEDAGOGICO;\n4-INATIVO\nInforme a opção desejada: ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("1|2|3|4")){
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException("Opção Inválida! Informe um número de 1 a 4.\n");
        }
    }
}
