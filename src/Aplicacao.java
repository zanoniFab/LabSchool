import model.Aluno;
import model.Pessoa;

public class Aplicacao {
    public static void main(String[] args) {
        RepositorioDados repositorioDados = new RepositorioDados();
        Aluno aluno = new Aluno(01,8);
        Pessoa pessoa = aluno;

    }
}
