package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Student;
import util.CrudUtil;

import java.sql.SQLException;

public class StudentFromController {
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField ttContact;
    public TextField txtAddress;
    public TextField txtNic;
    public TableView tblStudent;
    public TableColumn tblStudentId;
    public TableColumn tblStudentName;
    public TableColumn tblEmail;
    public TableColumn tblContact;
    public TableColumn tblAddress;
    public TableColumn tblNic;
    public TextField txtContact;

    public void UpdateOnAction(ActionEvent actionEvent) {
        Student student =new Student(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                txtNic.getText()
        );
        try {
            if (CrudUtil.execute("UPDATE student SET student_name=?,email=?,contact=?,address=?,nic=? WHERE student_id=?", student.getStudent_name(),student.getEmail(),student.getContact(),student.getAddress(),student.getNic(),student.getStudent_id())){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Student student=new Student(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                txtNic.getText()
        );
        try {
            if(CrudUtil.execute("INSERT INTO student VALUES (?,?,?,?,?,?)", student.getStudent_id(),student.getStudent_name(),student.getEmail(),student.getContact(),student.getAddress(),student.getNic())){
                new Alert(Alert.AlertType.CONFIRMATION,"Student Saved!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

    }

    public void serachOnAction(ActionEvent actionEvent) {
    }
}
