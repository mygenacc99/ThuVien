package application.view.Author;

import application.model.Author;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorEditDialogController {
    @FXML
    private TextField txtfID;
    @FXML
    private TextField txtfName;
    @FXML
    private DatePicker dpBirth;
    @FXML
    private TextField txtfNation;
    @FXML
    private TextField txtfNote;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Author author;
    private int handle;

    public boolean getOkeClicked(){
        return okClicked;
    }
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    public void setAuthor(Author author,int handle){
        if(author.getId() == "0"){
            txtfID.setPromptText("ID");
            txtfName.setPromptText("Full Name");
            dpBirth.setPromptText("mm/dd/yyyy");
            txtfNation.setPromptText("Nationality");
            txtfNote.setPromptText("Note");
        }
        else {
            txtfID.setText(author.getId());
            txtfName.setText(author.getFullName());
            dpBirth.setValue(author.getBirthday());
            txtfNation.setText(author.getNationality());
            txtfNote.setText(author.getNote());
        }
        this.author = author;
        this.handle = handle;

    }



    @FXML
    void handleOK(){

    }
    @FXML
    void handleCancel(){
        dialogStage.close();
    }

}
