package Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ReclamationService;
import models.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarController {
    @FXML private Label monthYearLabel;
    @FXML private GridPane calendarGrid;
    @FXML private Button prevButton;
    @FXML private Button nextButton;
    @FXML private Label selectedDateRangeLabel;
    @FXML private Button clearFilterButton;
    @FXML private ListView<Reclamation> reclamationListView;

    private LocalDate currentDate = LocalDate.now();
    private YearMonth currentYearMonth;
    private LocalDate startDate = null;
    private LocalDate endDate = null;

    private ReclamationService reclamationService = new ReclamationService();
    private ObservableList<Reclamation> reclamationData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        currentYearMonth = YearMonth.from(currentDate);
        updateCalendar();
        loadReclamations();  // Load reclamations on startup

        prevButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            updateCalendar();
            updateMonthYearLabel();
        });

        nextButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            updateCalendar();
            updateMonthYearLabel();
        });

        clearFilterButton.setOnAction(e -> clearDateFilter());
        updateSelectedDateRangeLabel();
        updateMonthYearLabel();

        // Configuration de la ListView
        reclamationListView.setCellFactory(lv -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation reclamation, boolean empty) {
                super.updateItem(reclamation, empty);
                if (empty || reclamation == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox container = new HBox(15);
                    container.setAlignment(Pos.CENTER_LEFT);
                    container.setPrefHeight(40);
                    container.setPadding(new Insets(5, 10, 5, 10));
                    container.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");
                    
                    Label typeLabel = new Label(reclamation.getType());
                    typeLabel.setPrefWidth(100);
                    
                    Label descLabel = new Label(reclamation.getDescription());
                    descLabel.setPrefWidth(200);
                    
                    Label dateLabel = new Label(reclamation.getDate().toString());
                    dateLabel.setPrefWidth(150);
                    
                    Label etatLabel = new Label(reclamation.getEtat());
                    etatLabel.setPrefWidth(100);
                    
                    container.getChildren().addAll(typeLabel, descLabel, dateLabel, etatLabel);
                    
                    container.setOnMouseEntered(e -> 
                        container.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;")
                    );
                    container.setOnMouseExited(e -> 
                        container.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;")
                    );
                    
                    setGraphic(container);
                }
            }
        });
    }

    private void loadReclamations() {
        try {
            List<Reclamation> allReclamations = reclamationService.getAll();
            reclamationData.setAll(allReclamations);
            reclamationListView.setItems(reclamationData);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger les réclamations.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clearDateFilter() {
        startDate = null;
        endDate = null;
        updateSelectedDateRangeLabel();
        updateCalendar();
        reclamationListView.setItems(reclamationData);
        System.out.println("Filtre de date réinitialisé.");
    }

    private void updateSelectedDateRangeLabel() {
        if (startDate == null && endDate == null) {
            selectedDateRangeLabel.setText("Aucun filtre de date");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        if (startDate != null && endDate != null) {
            selectedDateRangeLabel.setText(String.format("Du %s au %s",
                    startDate.format(formatter), endDate.format(formatter)));
        } else if (startDate != null) {
            selectedDateRangeLabel.setText(startDate.format(formatter));
        }
    }

    private void updateCalendar() {
        monthYearLabel.setText(currentYearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        calendarGrid.getChildren().clear();

        String[] dayNames = {"Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(dayNames[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #666666;");
            dayLabel.setAlignment(Pos.CENTER);
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstOfMonth = currentYearMonth.atDay(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;

        for (int i = 1; i <= currentYearMonth.lengthOfMonth(); i++) {
            LocalDate date = currentYearMonth.atDay(i);
            Button dateBtn = new Button(String.valueOf(i));
            dateBtn.setMaxWidth(Double.MAX_VALUE);
            dateBtn.setMaxHeight(Double.MAX_VALUE);

            updateDateButtonStyle(dateBtn, date);
            dateBtn.setOnAction(e -> handleDateSelection(date));

            calendarGrid.add(dateBtn, (dayOfWeek + i - 1) % 7, (dayOfWeek + i - 1) / 7 + 1);
        }
    }

    private void handleDateSelection(LocalDate selectedDate) {
        if (startDate == null || (endDate != null || selectedDate.isBefore(startDate))) {
            startDate = selectedDate;
            endDate = null;
        } else {
            if (selectedDate.isAfter(startDate)) {
                endDate = selectedDate;
            } else {
                endDate = startDate;
                startDate = selectedDate;
            }
        }

        updateSelectedDateRangeLabel();
        updateCalendar();
        filterReclamationsByDate(startDate, endDate);
    }

    private void filterReclamationsByDate(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            reclamationListView.setItems(reclamationData);
            return;
        }

        ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
        for (Reclamation rec : reclamationData) {
            LocalDate recDate = rec.getDate().toLocalDateTime().toLocalDate();
            if ((recDate.isEqual(start) || recDate.isAfter(start)) && (recDate.isEqual(end) || recDate.isBefore(end))) {
                filteredList.add(rec);
            }
        }

        reclamationListView.setItems(filteredList);
    }

    private void updateDateButtonStyle(Button dateBtn, LocalDate date) {
        boolean isSelected = (startDate != null && date.equals(startDate)) ||
                (endDate != null && date.equals(endDate));
        boolean isInRange = startDate != null && endDate != null &&
                date.isAfter(startDate) && date.isBefore(endDate);
        boolean isToday = date.equals(LocalDate.now());

        final String baseStyle = "-fx-background-radius: 5; " +
                (isSelected ? "-fx-background-color: #FFE5EC; -fx-text-fill: #333333; -fx-font-weight: bold;" :
                        isInRange ? "-fx-background-color: #FFF5F7; -fx-text-fill: #333333;" :
                                isToday ? "-fx-background-color: #E6E6E6; -fx-text-fill: #333333;" :
                                        "-fx-background-color: transparent; -fx-text-fill: #333333;");

        dateBtn.setStyle(baseStyle);

        dateBtn.setOnMouseEntered(e -> {
            if (!isSelected && !isInRange) {
                dateBtn.setStyle(baseStyle + "-fx-background-color: #f0f0f0;");
            }
        });

        dateBtn.setOnMouseExited(e -> {
            dateBtn.setStyle(baseStyle);
        });
    }

    private void updateMonthYearLabel() {
        if (monthYearLabel != null) {
            String monthYear = currentYearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            monthYearLabel.setText(monthYear);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageReclamation.fxml"));
            Parent root = loader.load();
            Scene scene = ((Button) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur", "Erreur lors du retour à la page des réclamations: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
