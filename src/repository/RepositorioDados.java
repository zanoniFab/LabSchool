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


    public void addAluno(Aluno aluno) {
        this.listaPessoas.add(aluno);
        this.listaAlunos.add(aluno);
    }

    public void addProfessor(Professor professor) {
        this.listaPessoas.add(professor);
        this.listaProfessores.add(professor);
    }

    public void addFuncionario(Funcionario funcionario) {
        this.listaPessoas.add(funcionario);
        this.listaFuncionarios.add(funcionario);
    }

    public Aluno getAlunoPorId(int idAluno) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getCodigo() == idAluno) {
                return aluno;
            }
        }
        return null;
    }

    public Funcionario getFuncionarioPorId(int idPedagogo) {
        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getCodigo() == idPedagogo) {
                return funcionario;
            }
        }
        return null;
    }

    public void somaAtendimento(Aluno aluno, Funcionario funcionario) {
        System.out.println("chegou atendimento");
        aluno.addAtendimento();
        funcionario.contarAtendimento();
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
}



// RF06 - Listagem de Pessoas: Listar todas as pessoas cadastradas, exibindo os campos
//         código, nome e CPF.
//         O usuário deverá informar quais categorias de pessoas deseja listar dentre as opções
//         abaixo:
//         ○ Alunos
//         ○ Professores
//         ○ Pedagogos
//         ○ Todos
