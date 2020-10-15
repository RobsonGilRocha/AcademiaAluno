package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AlunoService;
import models.Aluno;

import java.sql.SQLException;
import java.time.LocalDate;

public class ListaController {
    @FXML
    private TableColumn<Aluno, String> idTableNome;
    @FXML
    private TableColumn<Aluno, LocalDate> idTableData;
    @FXML
    private TableColumn<Aluno, Character> idTableSexo;
    @FXML
    private TableColumn<Aluno, String> idTableResponsavel;
    @FXML
    private TableView<Aluno> idTableAluno = new TableView<>();

    private AlunoService alunoService = AlunoService.getInstance();


    @FXML
    public void initialize() throws SQLException {
        alunoService.listAlunos();



        idTableNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        idTableData.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        idTableSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        idTableResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));

        idTableAluno.setPlaceholder(new Label("Nenhum aluno cadastrado."));
        idTableAluno.setItems(alunoService.alunos);

        idTableAluno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Aluno>() {
            @Override
            public void changed(ObservableValue<? extends Aluno> observable, Aluno oldValue, Aluno newValue) {

            }
        });
    }

    

}
