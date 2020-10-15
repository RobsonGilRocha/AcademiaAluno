package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.AlunoService;
import models.Aluno;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AddController {
    @FXML
    private ChoiceBox idChoiceBoxSexo;
    @FXML
    private DatePicker idDataPicker;
    @FXML
    private TextField idTextNome;
    @FXML
    private TextField idTextResponsavel;
    @FXML
    private Label idLabelResponsavel;
    @FXML
    public Label idMsg;


    private final AlunoService alunoService = AlunoService.getInstance();



    @FXML
    public void initialize(){
        idChoiceBoxSexo.getItems().addAll("Masculino","Feminino");

        idDataPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            long age = newValue.until(LocalDate.now(), ChronoUnit.YEARS);
            if(age >= 18){
                idLabelResponsavel.setDisable(true);
                idTextResponsavel.setDisable(true);
            } else {
                idLabelResponsavel.setDisable(false);
                idTextResponsavel.setDisable(false);
            }
        });
    }

    public void onBotaoA() {
        Alert alert;

         try{
             if(idTextNome.getText().isEmpty()){
                 idMsg.setText("sem nome");
                 throw new Exception("Sem nome");
             }

             if (idChoiceBoxSexo.getValue() == null){
                 idMsg.setText("sem sexo");
                 throw new Exception("sem sexo");
             }
             if (idDataPicker.getValue() == null){
                 idMsg.setText("sem Data");
                 throw new Exception("sem Data");
             }
             if(!idTextResponsavel.isDisabled() && idTextResponsavel.getText().isEmpty()) {
                 idMsg.setText("sem Responsavel");
                 throw new Exception("sem Responsavel");
             }

            Aluno aluno = new Aluno();
            aluno.setNome(idTextNome.getText());
            aluno.setNascimento(idDataPicker.getValue());
            aluno.setSexo(String.valueOf(idChoiceBoxSexo.getValue()).charAt(0));
            aluno.setResponsavel(idTextResponsavel.getText());

            boolean success = alunoService.addAluno(aluno);

            if(success) {
                clearFields();

                alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Aluno Cadastrado com Sucesso.",
                        ButtonType.OK
                );
                alert.setHeaderText("Cadastro");
                alert.showAndWait();
                idMsg.setText("Aluno Cadastrado com Sucesso");
            }
         } catch (Exception e){
             alert = new Alert(
                     Alert.AlertType.ERROR,
                     e.getMessage(),
                     ButtonType.OK
             );
             alert.setHeaderText("Erro");
             alert.showAndWait();
             idMsg.setText("Erro no programa");
         }






    }
    public void clearFields() {
        idTextNome.setText("");
        idDataPicker.getEditor().clear();
        idChoiceBoxSexo.setValue(null);
        idTextResponsavel.setText("");

    }

    public void onBotaoB() {
        clearFields();
        idMsg.setText("Campos  limpos com Sucesso");
    }
}
