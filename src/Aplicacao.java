import cli.Display;
import model.Aluno;
import model.Funcionario;
import model.OpcoesMenu;
import model.Pessoa;
import repository.RepositorioDados;

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
                    System.out.println(repositorioDados.getAlunoPorId(dadosRecebidos[0]));
                    System.out.println(repositorioDados.getFuncionarioPorId(dadosRecebidos[1]));
                    repositorioDados.getAlunoPorId(dadosRecebidos[0]).addAtendimento();
                    repositorioDados.getFuncionarioPorId(dadosRecebidos[1]).contarAtendimento();
                    System.out.println("Atendimento registrado com sucesso!");
                    System.out.println(repositorioDados.getAlunoPorId(dadosRecebidos[0]));
                    System.out.println(repositorioDados.getFuncionarioPorId(dadosRecebidos[1]));

                case SAIR:
                    System.exit(0);
                    break;
                default:
                    menuPrincipal(display, repositorioDados);
                    break;
            }
            opcao = display.obterOpcao();
            display.exibirMenuPrincipal();
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
                default:
                    menuCadastro(display, repositorioDados);
                    break;
            }
            display.exibirMenuCadastro();
            opcao = display.obterOpcao();
        }
        menuPrincipal(display, repositorioDados);
    }

}

