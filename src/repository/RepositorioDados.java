package repository;

import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDados {
    private int proximoCodigo = 0;
    private List<Aluno> listaAlunos = new ArrayList<>();
    private List<Professor> listaProfessores = new ArrayList<>();
    private List<Pedagogo> listaPedagogo = new ArrayList<>();
    private List<Pessoa> listaPessoas = new ArrayList<>();

    public int criarCodigo(){
        int auxiliar = this.proximoCodigo;
        this.proximoCodigo++;
        return auxiliar;
    }

    public void testes(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.listaPessoas.add(new Pessoa(0,"Derick Pradie","08785508950",51993750307L, LocalDate.parse("12/12/1992",formatador)));
        this.listaAlunos.add(new Aluno(this.listaPessoas.get(0),10,SituacaoMatricula.ATIVO));
        this.listaPessoas.add(new Pessoa(1,"Fabiane Zanoni","84706066034",5198145798L, LocalDate.parse("11/01/1992",formatador)));
        this.listaAlunos.add(new Aluno(this.listaPessoas.get(1),10,SituacaoMatricula.IRREGULAR));
        this.listaPessoas.add(new Pessoa(2,"Laura Pradie","45857970091",5193235450307L, LocalDate.parse("07/06/1992",formatador)));
        this.listaAlunos.add(new Aluno(this.listaPessoas.get(2),10,SituacaoMatricula.ATENDIMENTO_PEDAGOGICO));
        this.listaPessoas.add(new Pessoa(3,"Tiago Pradie","12345678901",5193235450307L, LocalDate.parse("07/06/1992",formatador)));
        this.listaAlunos.add(new Aluno(this.listaPessoas.get(3),10,SituacaoMatricula.INATIVO));
        this.listaPessoas.add(new Pessoa(4,"Laura Zanoni","12341234120",559999950307L, LocalDate.parse("07/06/1992",formatador)));
        this.listaProfessores.add(new Professor (this.listaPessoas.get(4),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.BACK_END,true));
        this.listaPessoas.add(new Pessoa(5,"Maria Zanoni","09876543213",559999950307L, LocalDate.parse("07/06/1992",formatador)));
        this.listaProfessores.add(new Professor(this.listaPessoas.get(5),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.FRONT_END,true));
        this.listaPessoas.add(new Pessoa(6,"Tamara Zanoni","12341245640",559999950307L, LocalDate.parse("07/06/1992",formatador)));
        this.listaProfessores.add(new Professor(this.listaPessoas.get(6),FormacaoAcademica.DOUTORADO,ExperienciaDesenvolvimento.FULL_STACK,true));
        this.listaPessoas.add(new Pessoa(7,"Cristiane Pradie","12345678901",513319407L, LocalDate.parse("07/06/1992",formatador)));
        this.listaPedagogo.add(new Pedagogo(this.listaPessoas.get(7)));
        this.listaPessoas.add(new Pessoa(8,"Cristiane Zanoni","09876543210",513319407L, LocalDate.parse("07/06/1992",formatador)));
        this.listaPedagogo.add(new Pedagogo(this.listaPessoas.get(8)));
        this.listaPessoas.add(new Pessoa(9,"Zanoni Cristiane","65748392109",513319407L, LocalDate.parse("07/06/1992",formatador)));
        this.listaPedagogo.add(new Pedagogo(this.listaPessoas.get(9)));
        this.listaPessoas.add(new Pessoa(11,"Jose Laura","10293847566",513319407L, LocalDate.parse("07/06/1992",formatador)));
        this.listaPedagogo.add(new Pedagogo(this.listaPessoas.get(10)));
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
        this.proximoCodigo=12;
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