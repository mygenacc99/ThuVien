package application.model;

import javafx.beans.property.*;

public class Category {
    private IntegerProperty ID;
    private StringProperty name;
    private StringProperty note;

    public Category(int ID, String name, String note) {
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.note = new SimpleStringProperty(note);
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "ID=" + ID +
                ", name=" + name +
                ", note=" + note +
                '}';
    }
}
