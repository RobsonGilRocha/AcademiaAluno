package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Academia {

    private static Academia instance;
    public ObservableList<Aluno> alunos;

    private Academia() {
        this.alunos = FXCollections.observableArrayList();
    }

    public static synchronized Academia getInstance(){
        if (instance == null) {
            instance = new Academia();
        }
        return instance;
    }

    public boolean adicionarAluno(Aluno aluno){
        return alunos.add(aluno);
    }
    public boolean removerAluno(String nome) {
        Aluno aluno = findAlunoByNome((nome));
        return alunos.remove(aluno);
    }
    public Aluno findAlunoByNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }

        return null;
    }
    public boolean editAluno(String nome, Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNome().equalsIgnoreCase(nome)) {
                alunos.remove(i);
                alunos.add(i, aluno);
                return true;
            }
        }
        return false;
    }


    /*Aluno
- nome (String)
- data de nascimento (LocalDate)
- sexo (char)
- nome do responsável (String) (se for menor que 18 anos)

    O sistema tem uma lista de alunos (ObservableList)

    O sistema deve cadastrar e listar os alunos

    Para o cadastro
    Voces já sabem

    Para a lista
    Usar TableView */
}
