package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFromController {
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField ttContact;
    public TextField txtAddress;
    public TextField txtNic;
    public TableView<Student> tblStudent;
    public TableColumn tblStudentId;
    public TableColumn tblStudentName;
    public TableColumn tblEmail;
    public TableColumn tblContact;
    public TableColumn tblAddress;
    public TableColumn tblNic;
    public TextField txtContact;

    public void initialize(){
        tblStudentId.setCellValueFactory(new PropertyValueFactory("student_id"));
        tblStudentName.setCellValueFactory(new PropertyValueFactory("student_name"));
        tblEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tblContact.setCellValueFactory(new PropertyValueFactory("contact"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblNic.setCellValueFactory(new PropertyValueFactory("nic"));

        try {
            LoadAllStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        try {
            if (CrudUtil.execute("DELETE FROM student WHERE student_id=?", txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serachOnAction(ActionEvent actionEvent) {
        try {
            ResultSet rst= CrudUtil.execute("SELECT * FROM student WHERE student_id=?", txtSearch.getText());
            if (rst.next()) {
                txtId.setText(rst.getString(1));
                txtName.setText(rst.getString(2));
                txtEmail.setText(rst.getString(3));
                txtContact.setText(rst.getString(4));
                txtAddress.setText(rst.getString(5));
                txtNic.setText(rst.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void LoadAllStudent() throws SQLException, ClassNotFoundException {
            ResultSet rst = CrudUtil.execute("SELECT * FROM student"  );
        System.out.println(rst);
            ObservableList<Student> oblist= FXCollections.observableArrayList();
            while (rst.next()){
                oblist.add(
                        new Student(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getString(3),
                                rst.getString(4),
                                rst.getString(5),
                                rst.getString(6)
                        )
                );
            }
            tblStudent.setItems(oblist);
    }
}
