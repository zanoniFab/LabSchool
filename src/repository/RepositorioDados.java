package repository;

import model.Aluno;
import model.Funcionario;
import model.Pessoa;
import model.Professor;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDados {
    private List<Aluno> listaAlunos = new ArrayList<>();
    private List<Professor> listaProfessores = new ArrayList<>();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Pessoa> listaPessoas = new ArrayList<>();


    public void addAluno(Aluno aluno){
        this.listaPessoas.add(aluno);
        this.listaAlunos.add(aluno);
    }
    public void addProfessor(Professor professor){
        this.listaPessoas.add(professor);
        this.listaProfessores.add(professor);
    }
    public void addFuncionario (Funcionario funcionario){
        this.listaPessoas.add(funcionario);
        this.listaFuncionarios.add(funcionario);
    }

}
