package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponse;
import services.ReponseService;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class listReponse {
    ReponseService reponseService ;
    public listReponse() {
        reponseService = new ReponseService();
    }

    @FXML
    private ListView<Reponse> listRep;

    @FXML
    public void initialize() {
        loadReponse();
    }

    private void loadReponse() {
        try {
            List<Reponse> reclamations = reponseService.getAll();
            ObservableList<Reponse> observableList = FXCollections.observableArrayList(reclamations);
            listRep.setItems(observableList);

            // Custom cell factory with buttons
            listRep.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Reponse rep, boolean empty) {
                    super.updateItem(rep, empty);
                    if (empty || rep == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label label = new Label("contenu: " + rep.getContenu_rep() + " | Date: " + rep.getDate_rep());


                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");


                        btnModifier.setOnAction(event -> showModifyPopup(rep));
                        btnSupprimer.setOnAction(event -> deleteReclamation(rep));

                        setGraphic(new VBox(5, label, btnModifier, btnSupprimer));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void showModifyPopup(Reponse rep) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);

        TextField descField = new TextField(rep.getContenu_rep());
        java.sql.Date dateRep = (Date) rep.getDate_rep();

        // Convert java.sql.Date to LocalDate
        LocalDate localDateRep = dateRep.toLocalDate();

        DatePicker datePicker = new DatePicker(localDateRep);
        Button saveButton = new Button("Modifier");

        saveButton.setOnAction(event -> {
            rep.setContenu_rep(descField.getText());
            rep.setDate(Date.valueOf(datePicker.getValue()));

            try {
                reponseService.update(rep);
                System.out.println("reponse modifiée avec succès !");
                loadReponse();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        layout.getChildren().addAll(new Label("Modifier reponse"), descField, datePicker, saveButton);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }


    private void deleteReclamation(Reponse rep) {
        try {
            reponseService.delete(rep.getId_rep());
            System.out.println("reponse supprimée avec succès !");
            loadReponse();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            listRep.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showRec(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReclamation.fxml"));
        try {
            Parent root = loader.load();
            listRep.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showRep(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReponse.fxml"));
        try {
            Parent root = loader.load();
            listRep.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
