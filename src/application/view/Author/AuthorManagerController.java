package application.view.Author;

import application.DAO.AuthorDAO;
import application.MainApp;
import application.model.Author;
import application.util.DateUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AuthorManagerController {
    @FXML
    private TableView<Author> authorTable;
    @FXML
    private TableColumn<Author, String> idColumn;
    @FXML
    private TableColumn<Author, String> nameColumn;
    @FXML
    private TableColumn<Author, LocalDate> birthColumn;
    @FXML
    private TableColumn<Author, String>  nationColumn;
    @FXML
    private TableColumn<Author, String> noteColumn;
    @FXML
    private Label lbAmount;

    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        nationColumn.setCellValueFactory(cellData -> cellData.getValue().nationalityProperty());
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        birthColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        birthColumn.setCellFactory(column -> {
             return new TableCell<Author, LocalDate>(){
                @Override
                protected void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(DateUtil.format(item));
                    }
                }
            };
        } );

        refreshAuthor();
    }

    public void refreshAuthor(){
        try {
            ObservableList<Author> list = AuthorDAO.getAuthorList();
            lbAmount.setText("Quantity is "+ list.size());
            authorTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleNewAuthor() {
        boolean okClick = showAuthorEditDialog((new Author("0","","0000-00-00","","")), 1);
        if(okClick){
            refreshAuthor();
        }
    }

    @FXML
    public void handleEditAuthor(){
        Author selectedAuthor = authorTable.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            boolean okClick = showAuthorEditDialog(selectedAuthor, 2);
            if (okClick){
                refreshAuthor();
            }
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Author Selected");
            alert.setContentText("Please select an author in the table.");

            alert.showAndWait();
        }
    }

    public boolean showAuthorEditDialog(Author author, int handle) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Author/AuthorEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            if (handle == 1) {
                dialogStage.setTitle("New Author");
            } else {
                dialogStage.setTitle("Edit Author");
            }

            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AuthorEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAuthor(author, handle);

            dialogStage.showAndWait();
            return controller.getOkeClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
