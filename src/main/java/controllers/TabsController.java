package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class TabsController {

    @FXML
    private Tab tabCadastroDeAlunos;
    @FXML
    private Tab tabListaDeAlunos;
    @FXML
    private Tab tabFindAlunos;
    @FXML
    public void initialize() throws Exception {
        tabCadastroDeAlunos.setContent(FXMLLoader.load(getClass().getResource("/views/addAluno.fxml")));
        tabListaDeAlunos.setContent(FXMLLoader.load(getClass().getResource("/views/listaAluno.fxml")));
        tabFindAlunos.setContent(FXMLLoader.load(getClass().getResource("/views/findAluno.fxml")));

    }

}
