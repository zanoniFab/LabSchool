package model;

public class Aluno extends Pessoa {
    private float nota;
    public Aluno(int codigo, float nota) {
        super(codigo);
        this.nota=nota;
    }
    public void incluirNota(float nota){
        this.nota=nota;
    }
    public float getNota(){
        return this.nota;
    }
}
