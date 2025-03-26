package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Categorie;
import models.Destination;
import services.CategorieService;
import services.DestinationService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FrontOffice {
    @FXML
    private GridPane gridContainer;

    @FXML
    private ComboBox<Categorie> categorie;
    CategorieService cs = new CategorieService();
    Categorie c = new Categorie();
    DestinationService ds ;

    private int userId;

    @FXML
    private Button profileButton;

    public FrontOffice() {
        this.ds = new DestinationService();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    void initialize() {
        try {
            // Load categories into ComboBox
            ObservableList<Categorie> categories = FXCollections.observableArrayList(cs.getAll());
            categorie.setItems(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show ComboBox when hovered
        categorie.setOnMouseEntered(event -> {
            if (!categorie.isShowing()) {
                categorie.show();
            }
        });

        // Handle category selection
        categorie.setOnAction(event -> {
            Categorie selectedCategory = categorie.getValue();
            if (selectedCategory != null) {
                try {
                    int categoryId = selectedCategory.getId(); // Directly get ID from the object
                    openPartenairePage(categoryId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Custom rendering of ComboBox items
        categorie.setCellFactory(param -> new ListCell<Categorie>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (item.getLogo() != null && !item.getLogo().isEmpty()) {
                        File imageFile = new File(item.getLogo());
                        if (imageFile.exists()) {
                            Image image = new Image(imageFile.toURI().toString(), 40, 40, true, true);
                            imageView.setImage(image);
                        } else {
                            imageView.setImage(null);
                        }
                    } else {
                        imageView.setImage(null);
                    }
                    setText(item.getNom());
                    setGraphic(imageView);
                }
            }
        });

        // Define how selected item is displayed in the ComboBox
        categorie.setButtonCell(new ListCell<Categorie>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (item.getLogo() != null && !item.getLogo().isEmpty()) {
                        File imageFile = new File(item.getLogo());
                        if (imageFile.exists()) {
                            Image image = new Image(imageFile.toURI().toString(), 40, 40, true, true);
                            imageView.setImage(image);
                        } else {
                            imageView.setImage(null);
                        }
                    } else {
                        imageView.setImage(null);
                    }
                    setText(item.getNom());
                    setGraphic(imageView);
                }
            }
        });

        // Load and display destinations
        List<Destination> destinationList;
        try {
            destinationList = ds.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return; // Stop execution if there's an error
        }

        int column = 0, row = 0;
        for (Destination destination : destinationList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/DestinationCard.fxml"));
                AnchorPane card = loader.load();

                DestinationCard cardController = loader.getController();
                cardController.setDestinationData(destination);

                gridContainer.add(card, column, row);
                column++;
                if (column == 3) { // 3 cards per row
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void showWishlist(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addRecl(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Test.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void goMiss(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/missionFront.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openPartenairePage(int categoryId) {
        try {
            // Get the current stage from the current scene
            Stage currentStage = (Stage) categorie.getScene().getWindow(); // Use an appropriate reference from your current scene

            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PartenaireFront.fxml"));
            Parent root = loader.load();

            // Get controller of the new page and pass the category ID
            PartenaireListController controller = loader.getController();
            controller.setCategoryId(categoryId);

            // Open the new stage
            Stage newStage = new Stage();
            newStage.setTitle("Liste des Partenaires");
            newStage.setScene(new Scene(root));
            newStage.show();

            // Close the current stage
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void goAbon(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/abonnementFront.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goDest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void showProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserProfile.fxml"));
            Parent root = loader.load();
            
            UserProfileController controller = loader.getController();
            controller.setUserId(userId);
            
            categorie.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading profile view: " + e.getMessage());
        }
    }

    @FXML
    protected void logOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) profileButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
