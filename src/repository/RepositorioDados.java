package repository;

import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDados {
    private List<Aluno> listaAlunos = new ArrayList<>();
    private List<Professor> listaProfessores = new ArrayList<>();
    private List<Pedagogo> listaPedagogo = new ArrayList<>();
    private List<Pessoa> listaPessoas = new ArrayList<>();

    public void testes(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.listaAlunos.add(new Aluno(new Pessoa("Derick Pradie",8785508950L,51993750307L, LocalDate.parse("12/12/1992",formatador)),10,SituacaoMatricula.ATIVO));
        this.listaAlunos.add(new Aluno(new Pessoa("Fabiane Zanoni",4567508950L,5198145798L, LocalDate.parse("11/01/1992",formatador)),10,SituacaoMatricula.IRREGULAR));
        this.listaAlunos.add(new Aluno(new Pessoa("Laura Pradie",765432120L,5193235450307L, LocalDate.parse("07/06/1992",formatador)),10,SituacaoMatricula.ATENDIMENTO_PEDAGOGICO));
        this.listaAlunos.add(new Aluno(new Pessoa("Tiago Pradie",765432120L,5193235450307L, LocalDate.parse("07/06/1992",formatador)),10,SituacaoMatricula.INATIVO));
        this.listaProfessores.add(new Professor(new Pessoa("Laura Zanoni",1234120L,559999950307L, LocalDate.parse("07/06/1992",formatador)),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.BACK_END,true));
        this.listaProfessores.add(new Professor(new Pessoa("Maria Zanoni",1234120L,559999950307L, LocalDate.parse("07/06/1992",formatador)),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.FRONT_END,true));
        this.listaProfessores.add(new Professor(new Pessoa("Tamara Zanoni",1234120L,559999950307L, LocalDate.parse("07/06/1992",formatador)),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.FULL_STACK,true));
        this.listaPedagogo.add(new Pedagogo(new Pessoa("Cristiane Pradie",1234567890L,513319407L, LocalDate.parse("07/06/1992",formatador))));
        this.listaPedagogo.add(new Pedagogo(new Pessoa("Cristiane Zanoni",1234567890L,513319407L, LocalDate.parse("07/06/1992",formatador))));
        this.listaPedagogo.add(new Pedagogo(new Pessoa("Zanoni Cristiane",1234567890L,513319407L, LocalDate.parse("07/06/1992",formatador))));
        this.listaPedagogo.add(new Pedagogo(new Pessoa("Jose Laura",1234567890L,513319407L, LocalDate.parse("07/06/1992",formatador))));
        this.listaPedagogo.get(0).contarAtendimento();
        this.listaPedagogo.get(0).contarAtendimento();
        this.listaPedagogo.get(0).contarAtendimento();
        this.listaPedagogo.get(1).contarAtendimento();
        this.listaPedagogo.get(1).contarAtendimento();
        this.listaAlunos.get(0).addAtendimento();
        this.listaAlunos.get(0).addAtendimento();
        this.listaAlunos.get(0).addAtendimento();
        this.listaAlunos.get(1).addAtendimento();
        this.listaAlunos.get(1).addAtendimento();
    }
    public void addAluno(Aluno aluno) {
        this.listaPessoas.add(aluno);
        this.listaAlunos.add(aluno);
    }

    public void addProfessor(Professor professor) {
        this.listaPessoas.add(professor);
        this.listaProfessores.add(professor);
    }

    public void addFuncionario(Pedagogo pedagogo) {
        this.listaPessoas.add(pedagogo);
        this.listaPedagogo.add(pedagogo);
    }

    public Aluno getAlunoPorId(int idAluno) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getCodigo() == idAluno) {
                return aluno;
            }
        }
        return null;
    }

    public Pedagogo getPedagogoPorId(int idPedagogo) {
        for (Pedagogo pedagogo : listaPedagogo) {
            if (pedagogo.getCodigo() == idPedagogo) {
                return pedagogo;
            }
        }
        return null;
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public List<Pedagogo> getListaPedagogo() {
        return listaPedagogo;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
}