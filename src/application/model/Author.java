package application.model;

import application.util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class Author {
    private StringProperty id;
    private StringProperty fullName;
    private ObjectProperty<LocalDate> birthday;
    private StringProperty nationality;
    private StringProperty note;

    public Author(String id, String fullName, String birthday, String nationality, String note) {
        this.id = new SimpleStringProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.birthday = new SimpleObjectProperty<LocalDate>(DateUtil.parse_LD(birthday));
        this.nationality = new SimpleStringProperty(nationality);
        this.note = new SimpleStringProperty(note);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
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
        return "Authur{" +
                "id=" + id +
                ", fullName=" + fullName +
                ", birthday=" + birthday +
                ", nationality=" + nationality +
                ", note=" + note +
                '}';
    }
}
