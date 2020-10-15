package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Academia;
import models.Aluno;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FindController {
    @FXML
    private TextField idTextFieldEdit;
    @FXML
    private DatePicker idDatePickerEdit;
    @FXML
    private ChoiceBox idChoiceBoxEdit;
    @FXML
    private TextField idTextFieldEditResp;
    @FXML
    private TextField idTextFieldBusca;
    @FXML
    private Label idSexoR;
    @FXML
    private Label idDataR;
    @FXML
    private Label idResponsavelR;
    @FXML
    Academia academia = Academia.getInstance();

    @FXML
    public void initialize() {
        idChoiceBoxEdit.getItems().addAll("Masculino","Feminino");
        idDatePickerEdit.valueProperty().addListener((observable, oldValue, newValue) -> {
            long age = newValue.until(LocalDate.now(), ChronoUnit.YEARS);
            if(age >= 18){
               // idLabelResponsavel.setDisable(true);
                idTextFieldEditResp.setDisable(true);
            } else {
               // idLabelResponsavel.setDisable(false);
                idTextFieldEditResp.setDisable(false);
            }
        });

    }



    public void onBusca() {
        System.out.println(" oi apertou o botao busca ");
        Aluno aluno = academia.findAlunoByNome(idTextFieldBusca.getText());
        idSexoR.setText(String.valueOf(aluno.getSexo()));
        idDataR.setText(String.valueOf(aluno.getNascimento()));
        idResponsavelR.setText(String.valueOf(aluno.getResponsavel()));


        //System.exit(0);
    }

    public void onRemover(ActionEvent event) {
        Aluno aluno = academia.findAlunoByNome(idTextFieldBusca.getText());
        academia.removerAluno(idTextFieldBusca.getText());
        idSexoR.setText(" ");
        idDataR.setText(" ");
        idResponsavelR.setText(" ");
        idTextFieldBusca.setText(" Aluno removido ");

    }

    public void onEdit(ActionEvent event) {
        Aluno aluno = academia.findAlunoByNome(idTextFieldBusca.getText());
        academia.removerAluno(idTextFieldBusca.getText());
        idSexoR.setText(" ");
        idDataR.setText(" ");
        idResponsavelR.setText(" ");
        idTextFieldBusca.setText(" Aluno Editado ");


        aluno.setNome(idTextFieldEdit.getText());
        aluno.setNascimento(idDatePickerEdit.getValue());
        aluno.setSexo(String.valueOf(idChoiceBoxEdit.getValue()).charAt(0));
        aluno.setResponsavel(idTextFieldEditResp.getText());

        boolean success = academia.adicionarAluno(aluno);
    }
}
