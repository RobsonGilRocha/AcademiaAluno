package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Aluno;

import java.sql.*;

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


    public boolean addAluno(Aluno aluno) throws SQLException {
        database = new Database();

        try (PreparedStatement statement =
                     database.connection.prepareStatement("INSERT INTO alunos (nome, nascimento, sexo, responsavel) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, aluno.getNome());
            statement.setDate( 2, Date.valueOf(aluno.getNascimento()));
            statement.setString(3, String.valueOf(aluno.getSexo()));
            statement.setString(4, aluno.getResponsavel());

            statement.executeUpdate();
//            alunos.add(aluno);
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }


    public boolean editAluno(String nome , Aluno aluno ) throws SQLException {
        database = new Database();
        try (PreparedStatement statement = database.connection.prepareStatement( "UPDATE alunos SET nome = ?, nascimento = ?, sexo = ?, responsavel = ?  where nome = ?")) {
            statement.setString(1, aluno.getNome());
            statement.setDate( 2, Date.valueOf(aluno.getNascimento()));
            statement.setString(3, String.valueOf(aluno.getSexo()));
            statement.setString(4, aluno.getResponsavel());
            statement.setString(5, nome);

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            return false;
        }

    }
/*
    public boolean removerAluno(String nome) {
        Aluno aluno = findAlunoByNome((nome));
        return alunos.remove(aluno);
    } */
    public boolean removerAluno(String nome) throws SQLException {
        database = new Database();
        Aluno aluno = findAlunoByNome((nome));


        try (PreparedStatement statement = database.connection.prepareStatement("DELETE FROM alunos where nome = ?")) {
            statement.setString(1, nome);

            statement.executeUpdate();
           // return true;
            return alunos.remove(aluno);
        } catch (SQLException sqlException) {
            return false;
        }
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
    /*
    public boolean editAluno(String nome, Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNome().equalsIgnoreCase(nome)) {
                alunos.remove(i);
                alunos.add(i, aluno);
                return true;
            }
        }
        return false;
    }*/


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
