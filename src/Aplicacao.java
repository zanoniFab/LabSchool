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
        menuPrincipal(display);
    }
    private static void menuPrincipal (Display display){
        display.exibirMenuPrincipal();
        switch (display.obterOpcao()){
            case INCLUIR_CADASTRO:
                menuCadastro(display);
                break;

            case SAIR:
                System.exit(0);
                break;
            default:
                menuPrincipal(display);
                break;
        }

    }
    private static void menuCadastro(Display display) {
        display.exibirMenuCadastro();
        OpcoesMenu opcao = display.obterOpcao();
        while (opcao != OpcoesMenu.VOLTAR) {
            switch (opcao) {
                case INCLUIR_ALUNO:
                    display.cadastrarAluno(display.cadastrarPessoa());
                    break;
                case INCLUIR_PROFESSOR:
                    display.cadastrarPessoa();
                    break;
                //  case INCLUIR_FUNCIONARIO:

                default:
                    menuCadastro(display);
                    break;
            }
            display.exibirMenuCadastro();
            opcao = display.obterOpcao();
        }
        menuPrincipal(display);
    }
}

