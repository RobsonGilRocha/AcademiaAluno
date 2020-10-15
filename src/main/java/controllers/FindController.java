package controllers;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.AlunoService;
import models.Aluno;

import java.sql.SQLException;
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
    AlunoService alunoService = AlunoService.getInstance();

    @FXML
    public void initialize() {
        idChoiceBoxEdit.getItems().addAll("Masculino","Feminino");
        idDatePickerEdit.valueProperty().addListener((observable, oldValue, newValue) -> {
            long age = newValue.until(LocalDate.now(), ChronoUnit.YEARS);
            if(age >= 18){
                idTextFieldEditResp.setDisable(true);
            } else {
                idTextFieldEditResp.setDisable(false);
            }
        });
    }
    public void onBusca() throws SQLException {
        System.out.println(" oi apertou o botao busca ");
        Aluno aluno = alunoService.findAlunoByNome(idTextFieldBusca.getText());
        idSexoR.setText(String.valueOf(aluno.getSexo()));
        idDataR.setText(String.valueOf(aluno.getNascimento()));
        idResponsavelR.setText(String.valueOf(aluno.getResponsavel()));

    }

    public void onRemover(ActionEvent event) throws SQLException {
        Aluno aluno = alunoService.findAlunoByNome(idTextFieldBusca.getText());
        alunoService.removerAluno(idTextFieldBusca.getText());
        idSexoR.setText("");
        idDataR.setText("");
        idResponsavelR.setText("");
        idTextFieldBusca.setText("");
        boolean success = alunoService.editAluno(idTextFieldBusca.getText(),aluno );

        if (success) {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    "Kick ass Aluno.",
                    ButtonType.OK
            );
            alert.setHeaderText("Aluno removido com sucesso");
            alert.showAndWait();
        }

        JFXPanel btnSave = null;
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();

    }
    public void onEdit(ActionEvent event) throws SQLException {
        Aluno aluno = alunoService.findAlunoByNome(idTextFieldBusca.getText());

        aluno.setNome(idTextFieldEdit.getText());
        aluno.setNascimento(idDatePickerEdit.getValue());
        aluno.setSexo(String.valueOf(idChoiceBoxEdit.getValue()).charAt(0));
        aluno.setResponsavel(idTextFieldEditResp.getText());

        boolean success = alunoService.editAluno(idTextFieldBusca.getText(),aluno );

        idSexoR.setText("");
        idDataR.setText("");
        idResponsavelR.setText("");
        idTextFieldBusca.setText("");

        if (success) {
            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    "Reborn Aluno.",
                    ButtonType.OK

            );
            alert.setHeaderText("Aluno alterado com sucesso");
            alert.showAndWait();
        }

        JFXPanel btnSave = null;
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();




    }
}
