package application.view.Category;

import application.DAO.CategoryDAO;
import application.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoryEditDialogController {
    @FXML
    private TextField txtfID;
    @FXML
    private TextField txtfName;
    @FXML
    private TextField txtfNote;

    private int handle;
    private Category categr;
    private Stage dialogStage;
    private Boolean okeClicked = false;

    public boolean getOkeClicked(){
        return okeClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCategory(Category categr, int handle){
        if (categr.getID() == 0){
            txtfID.setPromptText("ID");
            txtfName.setPromptText("Name");
            txtfNote.setPromptText("Note");
        }
        else {
            txtfID.setText(String.valueOf(categr.getID()));
            txtfName.setText(categr.getName());
            txtfNote.setText(categr.getNote());
        }

        this.categr = categr;
        this.handle = handle;
    }

    public boolean isInputValid(){
        String errorMessage = "";
        if (txtfID.getText() == null || txtfID.getText().length() == 0){
            errorMessage += "No valid ID! Must be not null\n";
        }
        else{
            try{
                Integer.parseInt(txtfID.getText());
            }
            catch(NumberFormatException e) {
                errorMessage += "No valid ID! (Must be a number)!\n";
            }
        }
        if (txtfName.getText() == null || txtfName.getText().length() == 0){
                errorMessage += "No valid Name! Must be not null\n";
        }
        if (txtfNote.getText() == null || txtfNote.getText().length() == 0){
            errorMessage += "No valid Name! Must be not null\n";
        }

        if(errorMessage.length() == 0){
            if (handle != 2 && (categr.getID() != Integer.parseInt(txtfID.getText()))){
                if(CategoryDAO.searchByID(txtfID.getText())){
                    errorMessage += "The ID of category is existed! Let change ID!\n";
                }
            }
        }
        if(errorMessage.length() == 0){
            return  true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleOke(){
        if(isInputValid()){
            Category newCategr = new Category(Integer.parseInt(txtfID.getText()), txtfName.getText(), txtfNote.getText());
            if(handle == 1){
                CategoryDAO.newCategory(newCategr);

            }
            else {
                CategoryDAO.editCategory(categr, newCategr);
            }
            okeClicked = true;
            dialogStage.close();
        }
    }
    public void handleCancel(){
        dialogStage.close();
    }


}
