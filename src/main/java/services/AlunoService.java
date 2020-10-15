package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Aluno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoService {

    private static AlunoService instance;
    Database database;
    public ObservableList<Aluno> alunos;

    private AlunoService() {
        this.alunos = FXCollections.observableArrayList();
    }

    public static synchronized AlunoService getInstance(){
        if (instance == null) {
            instance = new AlunoService();
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

    public ObservableList<Aluno> listAlunos() throws SQLException {
        database = new Database();

        try (
                Statement statement = database.connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from alunos")
                ) {
                    while (rs.next()) {
                        Aluno aluno = new Aluno();
                        aluno.setNome(rs.getString("nome"));
                        aluno.setNascimento(rs.getDate("nascimento").toLocalDate());
                        aluno.setSexo(rs.getString("sexo").charAt(0));
                        aluno.setResponsavel(rs.getString("responsavel"));

                        alunos.add(aluno);
                    }
                    database.shutdown();
                    return alunos;
        }
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
