package application.view.Category;

import application.DAO.CategoryDAO;
import application.MainApp;
import application.model.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryManagerController {
    @FXML
    private  TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Number> idColumn;
    @FXML
    private TableColumn<Category, String> nameColumn;
    @FXML
    private TableColumn<Category, String> noteColumn;
    @FXML
    private Label lbAmount;

    private Stage categoryStage;


    // Contructor
    public CategoryManagerController() {
    }

    @FXML
    private void initialize (){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().IDProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        refreshCategory();
    }

    public void refreshCategory(){
        try {
            ObservableList<Category> list = CategoryDAO.getCategoryList();
            categoryTable.setItems(list);
            lbAmount.setText("Quantity is "+categoryTable.getItems().size()+".");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleNewCategory() {
        boolean okClick = showCategoryEditDialog((new Category(0,null,null)), 1);
        if(okClick){
            refreshCategory();
        }
    }


    @FXML
    private void handleEditCategory() {
        Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            boolean okClick = showCategoryEditDialog(selectedCategory, 2);
            if (okClick){
                refreshCategory();
            }
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Category Selected");
            alert.setContentText("Please select a category in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelCategory(){
        int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0) {
            Category selectedCategory = categoryTable.getItems().remove(selectedIndex);
            CategoryDAO.delCategory(selectedCategory);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Category Selected");
            alert.setContentText("Please select a category in the table.");

            alert.showAndWait();
        }
    }

    public boolean showCategoryEditDialog(Category category, int handle){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Category/CategoryEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            if (handle == 1){
                dialogStage.setTitle("New Category");
            } else{
                dialogStage.setTitle("Edit Category");
            }

            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the Category into the controller.
            CategoryEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCategory(category, handle);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.getOkeClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
