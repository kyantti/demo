package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.robot.Robot;

public class PrimaryController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<Integer> cmbColumns;
    @FXML
    private ComboBox<Integer> cmbRows;

    @FXML
    private void start() throws IOException, InterruptedException {        
        App.setRoot("secondary");

        Game.getInstance().board.boxes = new Box[cmbRows.getSelectionModel().getSelectedItem()][cmbColumns.getSelectionModel().getSelectedItem()];
        Game.getInstance().board.setBoxesProperties();
        
        Thread addPlayerThread = new Thread(new PlayerIn(Game.getInstance()));
        addPlayerThread.start();
        addPlayerThread.join();

        for (int i = 0; i < Game.getInstance().players.size(); i++) {
            Game.getInstance().players.get(i).setColor(Color.values()[i]);
        }
            
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hola");
        ObservableList<Integer> rowsAndColumns = FXCollections.observableArrayList();
        rowsAndColumns.addAll(5, 6, 7, 8, 9, 10);
        cmbRows.setItems(rowsAndColumns);
        cmbColumns.setItems(rowsAndColumns);
        cmbRows.getSelectionModel().selectFirst();
        cmbColumns.getSelectionModel().selectFirst();

    }
}
