package Part1;

import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableRow;

public class View extends Application {
    Controller controller;
    Stage window;

    Scene scene_Login,
            sceneStudentMainMenu,
            sceneTeacherMainMenu,
            sceneAdminMainMenu,
            sceneCreateUser,
            sceneCreateCourse,
            sceneViewAllMembers,
            assignCoursesGui,
            sceneTeacherViewSubjects,
            viewMyStudents,
            sceneViewMyCourse,
            sceneStudentViewSubjects,
            sceneViewCourseTable,
            sceneViewAdminCourseTable,
            viewviewAdminStudents,
            sceneViewAddCourse,
            sceneDeleteCourse;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.controller = new Controller(this);
        logInMenu();
    }

    public void logInMenu() {
        window.setTitle("Log in");

        // LogIn Page Gui

        GridPane loginGridPane = new GridPane();
        loginGridPane.setPadding(new Insets(10, 10, 10, 10));
        loginGridPane.setVgap(8);
        loginGridPane.setHgap(10);

        // lable name
        Label nameLable = new Label("ID Number");
        GridPane.setConstraints(nameLable, 2, 0);

        // name imput

        TextField namTextField = new TextField();
        namTextField.setPromptText("Eg: 12334567890");
        GridPane.setConstraints(namTextField, 2, 1);

        // Password name
        Label passLable = new Label("Password");
        GridPane.setConstraints(passLable, 2, 3);

        // Password imput

        TextField passTextField = new TextField();
        GridPane.setConstraints(passTextField, 2, 4);

        // Log In Button

        Button loginButton = new Button("Log in");
        loginButton.setOnAction(e -> controller.isCredentialValid(passTextField.getText(), namTextField.getText()));

        GridPane.setConstraints(loginButton, 2, 5);

        // Passing the Object to the Scene

        loginGridPane.getChildren().addAll(nameLable, namTextField, passLable, passTextField, loginButton);

        Scene scene_Login = new Scene(loginGridPane, 400, 200);

        window.setScene(scene_Login);

        window.show();

    }

    // Student MainMenu

    public void setStudentMainMenuScene() {

        GridPane studentGrid = new GridPane();
        studentGrid.setPadding(new Insets(10, 10, 10, 10));
        studentGrid.setVgap(20);
        studentGrid.setHgap(20);

        Label welcome = new Label("Welcome Student");

        Button button1 = new Button("View My Course");
        button1.setOnAction(e -> viewStudentCourse());

        Button button2 = new Button("Add a Class");
        button2.setOnAction(e -> addStudentCourse());

        Button button4 = new Button("Log Out");
        button4.setOnAction(e -> logInMenu());

        GridPane.setConstraints(welcome, 2, 0);

        GridPane.setConstraints(button1, 2, 1);
        GridPane.setConstraints(button2, 2, 2);

        GridPane.setConstraints(button4, 2, 4);

        studentGrid.getChildren().addAll(button1, button2, button4, welcome);

        sceneStudentMainMenu = new Scene(studentGrid, 400, 250);
        window.setScene(sceneStudentMainMenu);
    }

    // Teacher MainMenu Gui

    public void setTeacherMainMenuScene() {

        GridPane teacherGrid = new GridPane();
        teacherGrid.setPadding(new Insets(10, 10, 10, 10));
        teacherGrid.setVgap(20);
        teacherGrid.setHgap(20);

        Label welcome = new Label("Welcome Teacher");

        Button button1 = new Button("View My Subjects");
        button1.setOnAction(e -> teacherViewMySubjects());

        Button button2 = new Button("View my Students");
        button2.setOnAction(e -> viewCourseTable());

        Button button4 = new Button("Log Out");
        button4.setOnAction(e -> logInMenu());

        GridPane.setConstraints(welcome, 2, 0);

        GridPane.setConstraints(button1, 2, 1);
        GridPane.setConstraints(button2, 2, 2);
        GridPane.setConstraints(button4, 2, 4);

        teacherGrid.getChildren().addAll(button1, button2, button4, welcome);

        sceneTeacherMainMenu = new Scene(teacherGrid, 400, 250);
        window.setScene(sceneTeacherMainMenu);
    }

    // Admin MainMenu Gui

    public void setAdminMainMenuScene() {

        GridPane adminGrid = new GridPane();
        adminGrid.setPadding(new Insets(10, 10, 10, 10));
        adminGrid.setVgap(20);
        adminGrid.setHgap(20);

        Label welcome = new Label("Welcome Admin");

        Button button1 = new Button("Create a User");
        button1.setOnAction(e -> createUserGui());

        Button button2 = new Button("Create Courses");
        button2.setOnAction(e -> createCoursesGui());

        Button button3 = new Button("Assign Courses");
        button3.setOnAction(e -> assignCoursesGui());

        Button button4 = new Button("Unasign Course");
        button4.setOnAction(e -> deleteCoursesGui());

        Button button5 = new Button("View All");
        button5.setOnAction(e -> viewAllMembers());

        Button button6 = new Button("Log out");
        button6.setOnAction(e -> logInMenu());

        GridPane.setConstraints(welcome, 2, 0);

        GridPane.setConstraints(button1, 2, 1);
        GridPane.setConstraints(button2, 2, 2);
        GridPane.setConstraints(button3, 2, 3);
        GridPane.setConstraints(button4, 2, 4);
        GridPane.setConstraints(button5, 2, 5);
        GridPane.setConstraints(button6, 2, 6);

        adminGrid.getChildren().addAll(button1, button2, button5, button6, welcome);

        sceneAdminMainMenu = new Scene(adminGrid, 400, 350);
        window.setScene(sceneAdminMainMenu);
    }

    // Error Messages to be called
    public void errorMessenge(String text, String title) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label errorMessenge = new Label(text);

        Button button1 = new Button("Close");
        button1.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorMessenge, button1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public void createUserGui() {

        GridPane createUserGrid = new GridPane();
        createUserGrid.setPadding(new Insets(10, 10, 10, 10));
        createUserGrid.setVgap(20);
        createUserGrid.setHgap(20);

        Label contexLabel = new Label("Create User");
        Label label2 = new Label("Name");
        Label label3 = new Label("Id");
        Label lable4 = new Label("Password");
        Label lable5 = new Label("User Type");

        TextField name = new TextField();
        TextField id = new TextField();
        TextField password = new TextField();

        CheckBox studentCheckBox = new CheckBox("Student");
        CheckBox teacherCheckBox = new CheckBox("Lecturer");

        Button createUserButton = new Button("Create User");
        createUserButton.setOnAction(e -> controller.createUser(name.getText(), id.getText(), password.getText(),
                teacherCheckBox, studentCheckBox));

        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setAdminMainMenuScene());

        GridPane.setConstraints(contexLabel, 2, 0);

        GridPane.setConstraints(label2, 2, 1);
        GridPane.setConstraints(name, 2, 2);

        GridPane.setConstraints(label3, 2, 3);
        GridPane.setConstraints(id, 2, 4);

        GridPane.setConstraints(lable4, 2, 5);
        GridPane.setConstraints(password, 2, 6);

        GridPane.setConstraints(lable5, 2, 7);

        GridPane.setConstraints(studentCheckBox, 2, 8);
        GridPane.setConstraints(teacherCheckBox, 3, 8);

        GridPane.setConstraints(createUserButton, 2, 9);
        GridPane.setConstraints(button2, 3, 9);

        createUserGrid.getChildren().addAll(
                contexLabel,
                label2,
                label3,
                lable4,
                lable5,
                createUserButton,
                button2,
                name,
                id,
                password,
                studentCheckBox,
                teacherCheckBox);

        sceneCreateUser = new Scene(createUserGrid, 400, 450);
        window.setScene(sceneCreateUser);
    }

    public void createCoursesGui() {
        String[] names = controller.populateLecureChoiceBox();

        GridPane createCourseGrid = new GridPane();
        createCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        createCourseGrid.setVgap(20);
        createCourseGrid.setHgap(20);

        Label label1 = new Label("Create Course");
        Label label2 = new Label("Course Code");
        Label label3 = new Label("Credit Hour");
        Label label4 = new Label("Pre-Requsite");
        Label label5 = new Label("Lecturer");

        TextField subjectCode = new TextField();

        ChoiceBox<String> subjectsRequired = new ChoiceBox<>();
        ChoiceBox<Integer> creditHour = new ChoiceBox<>();
        ChoiceBox<String> assignedLecture = new ChoiceBox<>();

        Button button1 = new Button("Create a Courses");
        button1.setOnAction(e -> controller.createCourse(subjectCode.getText(), assignedLecture));

        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setAdminMainMenuScene());

        // // Will change when Sql implement
        // subjectsRequired.getItems().addAll("test1", "Test2");
        // creditHour.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assignedLecture.getItems().addAll(names);

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(label2, 1, 1);
        GridPane.setConstraints(subjectCode, 1, 2);
        GridPane.setConstraints(label3, 1, 3);
        GridPane.setConstraints(creditHour, 1, 4);
        GridPane.setConstraints(label4, 1, 5);
        GridPane.setConstraints(subjectsRequired, 1, 6);
        GridPane.setConstraints(label5, 1, 7);
        GridPane.setConstraints(assignedLecture, 1, 8);

        GridPane.setConstraints(button1, 1, 9);
        GridPane.setConstraints(button2, 2, 9);

        createCourseGrid.getChildren().addAll(
                label1,
                label2,
                label3,
                label4,
                label5,
                subjectCode,
                creditHour,
                subjectsRequired,
                assignedLecture,
                button1,
                button2);

        sceneCreateCourse = new Scene(createCourseGrid, 400, 420);
        window.setTitle("Create Course");
        window.setScene(sceneCreateCourse);

    }

    public void assignCoursesGui() {

        GridPane assignCourseGrid = new GridPane();
        assignCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        assignCourseGrid.setVgap(20);
        assignCourseGrid.setHgap(20);

        Label label1 = new Label("Assign Course");
        Label label2 = new Label("Student Id");
        Label label3 = new Label("Course");

        TextField subjectCode = new TextField();

        Button button1 = new Button("Assign Courses");
        button1.setOnAction(e -> createUserGui());
        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setAdminMainMenuScene());

        ChoiceBox<String> subjectsRequired = new ChoiceBox<String>();

        // Will change when Sql implement
        subjectsRequired.getItems().addAll("test1", "Test2");

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(label2, 1, 1);
        GridPane.setConstraints(subjectCode, 1, 2);
        GridPane.setConstraints(label3, 1, 3);

        GridPane.setConstraints(subjectsRequired, 1, 6);

        GridPane.setConstraints(button1, 1, 9);
        GridPane.setConstraints(button2, 2, 9);

        assignCourseGrid.getChildren().addAll(
                label1,
                label2,
                label3,
                subjectCode,

                subjectsRequired,
                button1,
                button2);

        sceneCreateCourse = new Scene(assignCourseGrid, 400, 420);
        window.setTitle("Create Course");
        window.setScene(sceneCreateCourse);

    }

    public void deleteCoursesGui() {

        GridPane deleteCourseGrid = new GridPane();
        deleteCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        deleteCourseGrid.setVgap(20);
        deleteCourseGrid.setHgap(20);

        Label label1 = new Label("Assign Course");
        Label label2 = new Label("Student Id");
        Label label3 = new Label("Course");

        TextField subjectCode = new TextField();

        Button button1 = new Button("Assign Courses");
        button1.setOnAction(e -> createUserGui());
        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setAdminMainMenuScene());

        ChoiceBox<String> subjectsRequired = new ChoiceBox<String>();

        // Will change when Sql implement
        subjectsRequired.getItems().addAll("test1", "Test2");

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(label2, 1, 1);
        GridPane.setConstraints(subjectCode, 1, 2);
        GridPane.setConstraints(label3, 1, 3);

        GridPane.setConstraints(subjectsRequired, 1, 6);

        GridPane.setConstraints(button1, 1, 9);
        GridPane.setConstraints(button2, 2, 9);

        deleteCourseGrid.getChildren().addAll(
                label1,
                label2,
                label3,
                subjectCode,

                subjectsRequired,
                button1,
                button2);

        sceneDeleteCourse = new Scene(deleteCourseGrid, 400, 420);
        window.setTitle("Create Course");
        window.setScene(sceneDeleteCourse);

    }

    public void viewAllMembers() {

        TableView<ModelTable> table;

        GridPane viewMembersGrid = new GridPane();
        viewMembersGrid.setPadding(new Insets(10, 10, 10, 10));
        viewMembersGrid.setVgap(20);
        viewMembersGrid.setHgap(20);

        TableColumn<ModelTable, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ModelTable, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ModelTable, String> userTypeColumn = new TableColumn<>("Usertype");
        userTypeColumn.setMinWidth(200);
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        table = new TableView<>();
        table.setItems(controller.getTableAdmin());
        table.getColumns().addAll(nameColumn, idColumn, userTypeColumn);

        Label welcome = new Label("View All User");

        Button button1 = new Button("View by Courses");
        button1.setOnAction(e -> viewAdminCourseTable());

        Button button4 = new Button("Go Back");
        button4.setOnAction(e -> setAdminMainMenuScene());

        GridPane.setConstraints(table, 2, 2);

        GridPane.setConstraints(welcome, 2, 1);

        GridPane.setConstraints(button1, 2, 3);

        GridPane.setConstraints(button4, 2, 6);

        viewMembersGrid.getChildren().addAll(button1, button4, welcome, table);

        sceneViewAllMembers = new Scene(viewMembersGrid, 700, 450);
        window.setScene(sceneViewAllMembers);

    }

    public void teacherViewMySubjects() {

        GridPane teacherViewMySubjectsGrid = new GridPane();
        teacherViewMySubjectsGrid.setPadding(new Insets(10, 10, 10, 10));
        teacherViewMySubjectsGrid.setVgap(20);
        teacherViewMySubjectsGrid.setHgap(20);

        Label welcome = new Label("Your Class");
        Label classesLabel = new Label("Your Subjects");

        Button button4 = new Button("Back");
        button4.setOnAction(e -> setTeacherMainMenuScene());

        GridPane.setConstraints(welcome, 2, 0);
        GridPane.setConstraints(classesLabel, 4, 1);

        GridPane.setConstraints(button4, 2, 4);

        teacherViewMySubjectsGrid.getChildren().addAll(button4, welcome, classesLabel);

        sceneTeacherViewSubjects = new Scene(teacherViewMySubjectsGrid, 400, 250);
        window.setScene(sceneTeacherViewSubjects);
    }

    public void viewMyStudents(ChoiceBox<String> userChoice) {

        TableView<ModelTable> table;

        GridPane viewMembersGrid = new GridPane();
        viewMembersGrid.setPadding(new Insets(10, 10, 10, 10));
        viewMembersGrid.setVgap(20);
        viewMembersGrid.setHgap(20);

        TableColumn<ModelTable, String> nameColumn = new TableColumn<>("name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ModelTable, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        table = new TableView<>();
        table.setItems(controller.tableLectureSelectedCourse(userChoice));
        table.getColumns().addAll(nameColumn, idColumn);

        Label welcome = new Label("View All User");

        Button button4 = new Button("Go Back");
        button4.setOnAction(e -> setTeacherMainMenuScene());

        GridPane.setConstraints(table, 2, 0);

        GridPane.setConstraints(welcome, 2, 2);

        GridPane.setConstraints(button4, 2, 6);

        viewMembersGrid.getChildren().addAll(button4, welcome, table);

        viewMyStudents = new Scene(viewMembersGrid, 550, 450);
        window.setScene(viewMyStudents);

    }

    public void addStudentCourse() {
        String[] courseStrings = controller.populateCourseChoiceBox();

        GridPane createCourseGrid = new GridPane();
        createCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        createCourseGrid.setVgap(20);
        createCourseGrid.setHgap(20);

        Label label1 = new Label("Choose the course you want to add");

        ChoiceBox<String> courseAdded = new ChoiceBox<>();

        Button button1 = new Button("Register Subject");
        button1.setOnAction(e -> controller.addStudentSubjects(courseAdded));

        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setStudentMainMenuScene());

        courseAdded.getItems().addAll(courseStrings);

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(courseAdded, 1, 1);

        GridPane.setConstraints(button1, 1, 2);
        GridPane.setConstraints(button2, 2, 2);

        createCourseGrid.getChildren().addAll(
                label1,
                courseAdded,
                button1,
                button2);

        sceneViewAddCourse = new Scene(createCourseGrid, 400, 220);
        window.setTitle("Create Course");
        window.setScene(sceneViewAddCourse);
    }

    public void viewStudentCourse() {

        GridPane studentViewMySubjectsGrid = new GridPane();
        studentViewMySubjectsGrid.setPadding(new Insets(10, 10, 10, 10));
        studentViewMySubjectsGrid.setVgap(20);
        studentViewMySubjectsGrid.setHgap(20);

        Label welcome = new Label("Your Class");
        Label classesLabel = new Label("Your Subjects");

        Button button4 = new Button("Back");
        button4.setOnAction(e -> setStudentMainMenuScene());

        GridPane.setConstraints(welcome, 2, 0);
        GridPane.setConstraints(classesLabel, 4, 1);

        GridPane.setConstraints(button4, 2, 4);

        studentViewMySubjectsGrid.getChildren().addAll(button4, welcome, classesLabel);

        sceneStudentViewSubjects = new Scene(studentViewMySubjectsGrid, 400, 250);
        window.setScene(sceneStudentViewSubjects);

    }

    public void viewCourseTable() {
        String[] courseStrings = controller.populateLectureCourseChoiceBox();

        GridPane createCourseGrid = new GridPane();
        createCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        createCourseGrid.setVgap(20);
        createCourseGrid.setHgap(20);

        Label label1 = new Label("Choose the course you want to View");

        ChoiceBox<String> courseAdded = new ChoiceBox<>();

        Button button1 = new Button("View Course Members");
        button1.setOnAction(e -> viewMyStudents(courseAdded));

        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setTeacherMainMenuScene());

        courseAdded.getItems().addAll(courseStrings);

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(courseAdded, 1, 1);

        GridPane.setConstraints(button1, 1, 2);
        GridPane.setConstraints(button2, 2, 2);

        createCourseGrid.getChildren().addAll(
                label1,
                courseAdded,
                button1,
                button2);

        sceneViewCourseTable = new Scene(createCourseGrid, 400, 220);
        window.setTitle("Show Members");
        window.setScene(sceneViewCourseTable);
    }

    public void viewAdminCourseTable() {
        String[] courseStrings = controller.populateCourseChoiceBox();

        GridPane createCourseGrid = new GridPane();
        createCourseGrid.setPadding(new Insets(10, 10, 10, 10));
        createCourseGrid.setVgap(20);
        createCourseGrid.setHgap(20);

        Label label1 = new Label("Choose the course you want to View");

        ChoiceBox<String> courseAdded = new ChoiceBox<>();

        Button button1 = new Button("View Course Members");
        button1.setOnAction(e -> viewAdminStudents(courseAdded));

        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> setAdminMainMenuScene());

        courseAdded.getItems().addAll(courseStrings);

        GridPane.setConstraints(label1, 1, 0);
        GridPane.setConstraints(courseAdded, 1, 1);

        GridPane.setConstraints(button1, 1, 2);
        GridPane.setConstraints(button2, 2, 2);

        createCourseGrid.getChildren().addAll(
                label1,
                courseAdded,
                button1,
                button2);

        sceneViewAdminCourseTable = new Scene(createCourseGrid, 400, 220);
        window.setTitle("Show Members");
        window.setScene(sceneViewAdminCourseTable);
    }

    public void viewAdminStudents(ChoiceBox<String> userChoice) {

        TableView<ModelTable> table;

        GridPane viewMembersGrid = new GridPane();
        viewMembersGrid.setPadding(new Insets(10, 10, 10, 10));
        viewMembersGrid.setVgap(20);
        viewMembersGrid.setHgap(20);

        TableColumn<ModelTable, String> nameColumn = new TableColumn<>("name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ModelTable, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ModelTable, String> userColumn = new TableColumn<>("User");
        userColumn.setMinWidth(200);
        userColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        table = new TableView<>();
        table.setItems(controller.tableAdminSelectedCourse(userChoice));
        table.getColumns().addAll(nameColumn, idColumn, userColumn);

        Label welcome = new Label("View All User");

        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");
        Button button3 = new Button("Button3");

        Button button4 = new Button("Go Back");
        button4.setOnAction(e -> setAdminMainMenuScene());

        GridPane.setConstraints(table, 2, 0);

        GridPane.setConstraints(welcome, 2, 2);

        GridPane.setConstraints(button1, 2, 3);
        GridPane.setConstraints(button2, 2, 4);
        GridPane.setConstraints(button3, 2, 5);
        GridPane.setConstraints(button4, 2, 6);

        viewMembersGrid.getChildren().addAll(button4, welcome, table);

        viewviewAdminStudents = new Scene(viewMembersGrid, 800, 450);
        window.setScene(viewviewAdminStudents);

    }

}
