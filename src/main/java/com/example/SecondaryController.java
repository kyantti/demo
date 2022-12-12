package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SecondaryController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane historico;

    @FXML
    private Pane pane;

    @FXML
    private TableView<?> players;


    @FXML
    void play(ActionEvent event) {
        System.out.println("Filas = " + Board.getBoardInstance().rows());
        System.out.println("Columnas = " + Board.getBoardInstance().columns());
        mostrarTablero();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.getStyleClass().add("mainPane");
        grid.getStyleClass().add("gridPane");
        
    }

    public void mostrarTablero() {
        int rows = Board.getBoardInstance().rows();
        int columns = Board.getBoardInstance().columns();
        for (int i = 0; i <rows; i++) {
            for (int j = 0; j < columns; j++) {
                Box box = Game.getInstance().board.boxes[i][j];
                Button button = new Button(box.getValue());
                if (Boolean.TRUE.equals(box.getOccupied())) {
                    switch (box.getPlayer().getColor()) {
                        case RED:
                            button.setStyle("-fx-background-color: #de2a2a;");
                            break;
                        case BLUE:
                            button.setStyle("-fx-background-color: #2a3cde;");
                            break;
                        case ORANGE:
                            button.setStyle("-fx-background-color: #de8a2a;");
                            break;
                        case GREEN:
                            button.setStyle("-fx-background-color: #5dbf30;");
                            break;
                        case PURPLE:
                            button.setStyle("-fx-background-color: #a82ade;");
                            break;
                        case YELLOW:
                            button.setStyle("-fx-background-color: #decc2a;");
                            break;
                        default:
                            break;
                    }
                }
                button.setPrefWidth(120);
                button.setPrefHeight(40);
                button.setAlignment(Pos.CENTER);
                grid.add(button, i, j);
            }
        }
    }
}