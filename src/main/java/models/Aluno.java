package models;

import java.time.LocalDate;

public class Aluno {
    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String responsavel;

    public Aluno(){
    }

    public String getNome() {
        return nome;
    }

    public Aluno setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Aluno setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public char getSexo() {
        return sexo;
    }

    public Aluno setSexo(char sexo) {
        this.sexo = sexo;
        return this;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Aluno setResponsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }
    /*@Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", responsavel='" + responsavel +
                '}';
    }*/

}
