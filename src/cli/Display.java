package cli;

import Exceptions.LabException;
import com.sun.source.tree.TryTree;
import model.*;
import repository.RepositorioDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Display {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void exibirMenuPrincipal() {
        System.out.print("\n(Menu Principal)\n1-Incluir/Alterar Cadastro;\n2-Registrar Atendimento;\n3-Emitir Relatórios\n9-Sair;\n");
    }

    public void exibirMenuCadastro() {
        System.out.print("\n(Menu Cadastro)\n4-Incluir Aluno;\n5-Incluir Professor;\n6-Incluir Pedagogo;\n7-Alterar Situação Matricula Aluno;\n8-Voltar\n9-Sair\n");
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
        System.out.printf(ANSI_GREEN + "Cadastro realizado com sucesso! ID ALUNO: %d\n" + ANSI_RESET, aluno.getCodigo());
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
        System.out.printf(ANSI_GREEN + "Cadastro realizado com sucesso! ID PROFESSOR: %d\n" + ANSI_RESET, professor.getCodigo());
        return professor;
    }

    public Pedagogo cadastrarPedagogo(Pessoa pessoa) {
        Pedagogo pedagogo = new Pedagogo(pessoa);
        System.out.printf("Cadastro realizado com sucesso! ID FUNCIONARIO: %d\n", pedagogo.getCodigo());
        return pedagogo;
    }

    public int[] receberDadosAtendimento() throws LabException {
        int[] ids = new int[2];
        try {
            System.out.print("\nInforme o ID do Aluno: ");
            ids[0] = recebeNumero("[0-9]+");
            System.out.print("\nInforme o ID do Pedagogo: ");
            ids[1] = recebeNumero("[0-9]+");
        }catch (LabException e){
            System.out.print(e.getMessage());
            return receberDadosAtendimento();
        }
        return ids;
    }
    public static int recebeNumero(String regex) throws LabException {
        Scanner scanner = new Scanner(System.in);
        String numero = scanner.nextLine();
        if(numero.matches(regex)){
            return Integer.parseInt(numero);
        }else{
            throw new LabException(ANSI_RED + "São aceitos apenas números entre "+regex+"!" + ANSI_RESET);
        }
    }
    public void printAmareloMensagem(String msg){
        System.out.println(ANSI_YELLOW  + msg + ANSI_RESET);
    }
    public void printVermelhoMensagem(String msg){
        System.out.println(ANSI_RED  + msg + ANSI_RESET);
    }
    public void printVerdeMensagem(String msg){
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    public int exibirMenuRelatorios() {
        try{
            System.out.print("\n(Menu Relatorios)\nEscolha qual relat[orio você deseja emitir:\n1-Listagem com todas as pessoas (ID, NOME, CPF)\n2-Relatorio alunos por situação de matricula;\n");
            System.out.print("3-Relatório de Professores por experiencia em desenvolvimento;\n4-Relatórios de alunos por numero de atendimentos;\n5-Relatório de pedagogos por número de atendimentos;\n");
            System.out.print("6-Voltar;\n7-Sair\n");
            System.out.print("Informe a opção desejada: ");
            return recebeNumero("[1-7]");
        }catch (LabException e){
            System.out.println(e.getMessage());
            return exibirMenuRelatorios();
        }
    }

    public int exibirMenuRelatorioSituacaoMatricula() {
        try{
            System.out.print("\nRelatório de Alunos por Situação de Matrícula:\n1-Ativo;\n2-Irregular;\n3-Atendimento Pedagógico;\n4-Inativo;\n5-Todos;\n6-Voltar;\n7-Sair\n");
            System.out.print("Informe a opção desejada: ");
            return recebeNumero("[1-7]");
        }catch (LabException e){
            System.out.println(e.getMessage());
            return exibirMenuRelatorioSituacaoMatricula();
        }
    }

    public int exibirMenuRelatoriosProfessores() {
        try{
            System.out.print("\n(Relatorios Professores EXP)\nInforme a opção desejada:\n1-FRONT-END;\n2-BACK-END;\n3-FULL-STACK;\n4-Todos;\n");
            System.out.print("5-Voltar;\n6-Sair\n");
            System.out.print("Informe a opção desejada: ");
            return recebeNumero("[1-6]");
        }catch (LabException e){
            System.out.println(e.getMessage());
            return exibirMenuRelatoriosProfessores();
        }
    }

    public void aguarde() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "\nPressione a tecla enter para continuar...\n" + ANSI_RESET);
        scanner.nextLine();
    }

    public int exibirMenuRelatorioPessoas() {
        try {
            System.out.print("\n(Relatorios Pessoas)\nQual categoria você deseja no relatório?\n1-Alunos;\n2-Professores;\n3-Pedagogos;\n4-Todos;\n5-Voltar;\n6-Sair;\n");
            System.out.print("Informe a opção desejada: ");
            return recebeNumero("[1-6]");
        }catch (LabException e){
            System.out.println(e.getMessage());
            return exibirMenuRelatorioPessoas();
        }
    }

    public void exibirMensagem() {
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
            throw new LabException(ANSI_RED + "Nome inválido! Informe somente letras.\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "CPF inválido! Tente novamente.\n" + ANSI_RESET);
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
        if (telefoneInformado.matches("^\\d+$") && telefoneInformado.length() > 8) {
            return telefoneInformado;
        } else {
            throw new LabException(ANSI_RED + "Número inválido! Informe somente números.\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Data inválida! Informe somente números separados por \"/\".\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Nota inválida! Informe uma nota de 0 a 10 no formato \"n.n\".\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Opção inválida!\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Opção inválida!\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Opção inválida!\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Opção inválida! Informe A para ativo ou I para Inativo.\n" + ANSI_RESET);
        }
    }

    public void confirmacaoOperacao(boolean executou) {
        if (executou) {
            System.out.print(ANSI_GREEN + "Operação realizada com sucesso!\n" + ANSI_RESET);
        } else {
            System.out.print(ANSI_RED + "Algo de errado aconteceu...\n" + ANSI_RESET);
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
            throw new LabException(ANSI_RED + "Opção inválida! Tente novamente.\n" + ANSI_RESET);
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
        System.out.print("\nInforme o ID do aluno: ");
        String idAluno = scanner.nextLine();
        if (idAluno.matches("[0-9]")) {
            return Integer.parseInt(idAluno);
        } else {
            throw new LabException(ANSI_RED + "ID inválido. Informe somente números.\n" + ANSI_RESET);
        }
    }

    public String confirmaAluno(Aluno aluno) {
        System.out.printf("O aluno informado foi:\n[ID: %d; Nome: %s; CPF: %s; Situação Matrícula: %s]\n", aluno.getCodigo(), aluno.getNome(), aluno.getCpf(), aluno.getSituacaoMatricula());
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
        System.out.print(ANSI_YELLOW + "\nConfirma alteração [S/N]? " + ANSI_RESET);
        String opcaoInformada = scanner.nextLine().toUpperCase();
        if (opcaoInformada.matches("S|N")) {
            return opcaoInformada;
        } else {
            throw new LabException(ANSI_RED + "Opção inválida! Pressione S para confirmar alteração ou N para alterar o aluno.\n" + ANSI_RESET);
        }

    }

    public int receberOpcaoAlteracaoMatricula() {
        try {
            return validarOpcaoAlteracaoMatricula();
        } catch (LabException e) {
            System.out.print(e.getMessage());
            return receberOpcaoAlteracaoMatricula();
        }
    }

    private int validarOpcaoAlteracaoMatricula() throws LabException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPara qual situação você deseja alterar:\n");
        System.out.print("1- ATIVO;\n2-IRREGULAR;\n3-ATENDIMENTO PEDAGOGICO;\n4-INATIVO\nInforme a opção desejada: ");
        String opcaoInformada = scanner.nextLine();
        if (opcaoInformada.matches("1|2|3|4")) {
            return Integer.parseInt(opcaoInformada);
        } else {
            throw new LabException(ANSI_RED + "Opção Inválida! Informe um número de 1 a 4.\n" + ANSI_RESET);
        }
    }
}
