package Part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;

import Part2.Model.Model;
import Part2.Model.ModelTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

// Handle all the methods to be called
public class Controller {
    View view;
    Model model = new Model();
    ModelTable modelTable = new ModelTable();

    // 
    public Controller(View view) {
        this.view = view;
    }

    // Credential Logic for verification purpose
    public void isCredentialValid(String password, String id) {

        if (containsOnlyNumbers(id)) {
            int idInt = Integer.parseInt(id);

            HashMap<Integer, String> tempTeacherPasswordHashMap = new HashMap<Integer, String>(
                    model.getTeacherPasswordHashMap());

            HashMap<Integer, String> tempStudentPasswordHashMap = new HashMap<Integer, String>(
                    model.getStudentPasswordHashMap());

            if (tempTeacherPasswordHashMap.containsKey(idInt)) {

                if (tempTeacherPasswordHashMap.get(idInt).equals(password)) {

                    model.currentUser = idInt;
                    view.setTeacherMainMenuScene();
                    System.out.println("Lecturer login successful");
                }

                else {
                    view.errorMessenge("Invalid Username/Password", "Invalid Credentials");
                }

            }

            else if (tempStudentPasswordHashMap.containsKey(idInt)) {

                if (tempStudentPasswordHashMap.get(idInt).equals(password)) {

                    model.currentUser = idInt;
                    view.setStudentMainMenuScene();
                    checkMinCourseWork();
                    System.out.println("Student login successful");
                }

                else {
                    view.errorMessenge("Invalid Username/Password", "Invalid Credentials");
                }
            }

            else if (idInt == 0000 && password.equals("0000")) {
                view.setAdminMainMenuScene();
                System.out.println("Admin login successful");
            }

            // Example of Usage of the Error message just called it whenever you want
            else {
                view.errorMessenge("Invalid Username/Password", "Invalid Credentials");
            }
        }

        else {
            view.errorMessenge("Invalid Username/Password", "Invalid Credentials");
        }
    }

    // Create a user (Student/Lecturer) in the system
    public void createUser(String name, String id, String password, CheckBox teacherCheckBox,
            CheckBox studentCheckBox) {

        int idInt = Integer.parseInt(id);

        if (!model.takenID.contains(idInt)) {
            if (teacherCheckBox.isSelected() && !studentCheckBox.isSelected()) {
                HashMap<Integer, String> tempTacherPasswordHashMap = new HashMap<Integer, String>(
                        model.getTeacherPasswordHashMap());
                HashMap<Integer, String> tempTeacherNameHashMap = new HashMap<Integer, String>(
                        model.getTeacherNameHashMap());

                tempTeacherNameHashMap.put(idInt, name);
                tempTacherPasswordHashMap.put(idInt, password);
                model.setTeacherNameHashMap(tempTeacherNameHashMap);
                model.setTeacherPasswordHashMap(tempTacherPasswordHashMap);
                model.takenID.add(idInt);

                view.errorMessenge("Lecturer " + (name) + " Created Sucessfully", "User Created");

            }

            if (studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                HashMap<Integer, String> tempStudentPasswordHashMap = new HashMap<Integer, String>(
                        model.getStudentPasswordHashMap());
                HashMap<Integer, String> tempStudentNameHashMap = new HashMap<Integer, String>(
                        model.getStudentNameHashMap());
                HashMap<Integer, Set<String>> studentFutureRecordHashMap = new HashMap<Integer, Set<String>>(
                        model.getStudentFutureRecordHashMap());
                HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(
                        model.getStudentCreditHashMap());
                Set<String> hash_Set = new HashSet<String>();
                Set<String> hash_Set2 = new HashSet<String>();
                Set<String> hash_Set3 = new HashSet<String>();

                HashMap<Integer, Set<String>> studentRecordHashMap = new HashMap<Integer, Set<String>>(
                        model.getStudentRecordHashMap());
                HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                        model.getStudentPastRecordHashMap());

                hash_Set.add("CS113");
                hash_Set.add("CS123");
                hash_Set.add("CS133");
                hash_Set.add("CS143");
                hash_Set.add("CS214");
                hash_Set.add("CS234");
                hash_Set.add("CS316");

                tempStudentPasswordHashMap.put(idInt, password);
                tempStudentNameHashMap.put(idInt, name);
                studentFutureRecordHashMap.put(idInt, hash_Set);
                studentCreditHashMap.put(idInt, 0);
                studentRecordHashMap.put(idInt, hash_Set2);
                studentPastRecordHashMap.put(idInt, hash_Set3);

                model.setStudentNameHashMap(tempStudentNameHashMap);
                model.setStudentPasswordHashMap(tempStudentPasswordHashMap);
                model.setStudentFutureRecordHashMap(studentFutureRecordHashMap);
                model.setStudentCreditHashMap(studentCreditHashMap);
                model.setStudentRecordHashMap(studentRecordHashMap);
                model.setStudentPastRecordHashMap(studentPastRecordHashMap);
                model.takenID.add(idInt);

                System.out.println(studentFutureRecordHashMap);

                view.errorMessenge("Student " + (name) + " Created Sucessfully", "User Created");

            }

            if (studentCheckBox.isSelected() && teacherCheckBox.isSelected()) {
                view.errorMessenge("Pick One User Type Only", "Invalid Option");
            }

        } else
            view.errorMessenge(id + " has been taken", "ID Taken");

    }

    // Check if the input ID is in integer only and not contain any alphabets
    private static boolean containsOnlyNumbers(String str) {
        // return false or true for the condition
        return str.matches("[0-9]+");
    }

    // Create a course in the system 
    public void createCourse(ChoiceBox<String> courseNameBox, ChoiceBox<String> lectureName) {

        if (lectureName.getValue() != null || courseNameBox.getValue() != null) {

            String courseName = courseNameBox.getValue();

            HashMap<Integer, String> NameHashMap = new HashMap<Integer, String>(model.getTeacherNameHashMap());
            String lectureSelected = lectureName.getValue();
            Set<String> courseAvailablSet = new HashSet<String>(model.getCourseAvailablSet());
            HashMap<Integer, ArrayList<String>> teacherAsignCourseHashMap = new HashMap<Integer, ArrayList<String>>(
                    model.getTeacherAsignCourseHashMap());

            HashMap<String, Integer> lectureRecordHashMap = new HashMap<String, Integer>(
                    model.getLectureRecordHashMap());

            Integer foundKey = getKeyByValue(NameHashMap, lectureSelected);

            if (!courseAvailablSet.contains(courseName)) {
                if (foundKey != null) {
                    System.out.println("Key for value '" + lectureSelected + "': " + foundKey);
                    courseAvailablSet.add(courseName);
                    teacherAsignCourseHashMap.computeIfAbsent(foundKey, k -> new ArrayList<>()).add(courseName);
                    lectureRecordHashMap.put(courseName, foundKey);

                    model.setTeacherAsignCourseHashMap(teacherAsignCourseHashMap);
                    model.setCourseAvailablSet(courseAvailablSet);
                    model.setLectureRecordHashMap(lectureRecordHashMap);

                } else {
                    System.out.println("Value '" + lectureSelected + "' not found in the HashMap");
                }

                System.out.println("Course Name " + courseName);
                System.out.println("Lecture Name " + lectureName.getValue());
                System.out.println("Array " + teacherAsignCourseHashMap);
                view.errorMessenge("Course " + (courseName) + " Created Sucessfully", "Course Created");
            }

            else {
                view.errorMessenge("Course Existed", "Duplicate Course");
            }
        }

        else
            view.errorMessenge("Invalid Option", "Select a Lecture/Subject");

    }

    // Return the key for a specific value
    private static Integer getKeyByValue(HashMap<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                // Return the key if the value is found inside the hash map
                return entry.getKey();
            }
        }
        // If value is not found, return null
        return null;
    }

    // Create all lecturers as choice to be selected in certain scenarios
    public String[] populateLecureChoiceBox() {

        HashMap<Integer, String> TeacherNameHashMap = new HashMap<Integer, String>(model.getTeacherNameHashMap());

        int size = TeacherNameHashMap.size();

        String[] stringArray = new String[size];

        int index = 0;
        for (Integer key : TeacherNameHashMap.keySet()) {
            stringArray[index++] = TeacherNameHashMap.get(key);
        }

        // return all the elements in the array
        return stringArray;

    }

    // Create courses as choice to be selected in certain scenarios for the specific semester
    public String[] populateCourseChoiceBox() {

        Set<String> courseAvailablSet = new HashSet<String>(model.getCourseAvailablSet());
        int size = courseAvailablSet.size();
        String coursearray[] = new String[size];

        System.arraycopy(courseAvailablSet.toArray(), 0, coursearray, 0, size);
        String[] stringArray = coursearray;
        // return all the elements in the array
        return stringArray;
    }

    // Create all courses in the system as choices to be selected 
    public ArrayList<String> populateCourseCreatorChoiceBox() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CS113");
        arrayList.add("CS123");
        arrayList.add("CS133");
        arrayList.add("CS143");
        arrayList.add("CS214");
        arrayList.add("CS224");
        arrayList.add("CS234");
        arrayList.add("CS316");
        // return all the elements in the array
        return arrayList;
    }

    // 
    public String[] populateLectureCourseChoiceBox() {

        HashMap<Integer, ArrayList<String>> teacherAsignCourseHashMap = new HashMap<Integer, ArrayList<String>>(
                model.getTeacherAsignCourseHashMap());
        ArrayList<String> arrayList = teacherAsignCourseHashMap.get(model.currentUser);
        String[] stringArray = new String[arrayList.size()];
        stringArray = arrayList.toArray(stringArray);
        // return all the elements in the array
        return stringArray;
    }

    // Display table of information with columns such as name, ID and course code
    public ObservableList<ModelTable> getTableAdmin() {
        ObservableList<ModelTable> table = FXCollections.observableArrayList();
        HashMap<Integer, String> teacherNameHashMap = new HashMap<Integer, String>(model.getTeacherNameHashMap());
        HashMap<Integer, String> studentNameHashMap = new HashMap<Integer, String>(model.getStudentNameHashMap());

        for (HashMap.Entry<Integer, String> entry : teacherNameHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            int id12 = entry.getKey();
            String name12 = entry.getValue();
            String course = "Lecture"; // this one for course, idk how to do
            table.add(new ModelTable(name12, id12, course));
        }

        for (HashMap.Entry<Integer, String> entry : studentNameHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            int id12 = entry.getKey();
            String name12 = entry.getValue();
            String course = "Student";
            table.add(new ModelTable(name12, id12, course));
        }

        // Display the table
        return table;
    }

    // Register courses selected by the student
    public void addStudentSubjects(ChoiceBox<String> courseAdded) {
        if (courseAdded.getValue().equals("CS214")) {

            HashMap<Integer, Set<String>> studentAsignCourseHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentRecordHashMap());
            HashMap<String, Set<Integer>> courseAssignStudentHashMap = new HashMap<String, Set<Integer>>(
                    model.getSubjectRecordHashMap());
            String course = courseAdded.getValue();
            HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentPastRecordHashMap());
            Set<String> pastRecord = new HashSet<>();
            HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());
            Integer coursework = studentCreditHashMap.get(model.currentUser);

            pastRecord = studentPastRecordHashMap.get(model.currentUser);
            studentCreditHashMap.put(model.currentUser, (coursework + 4));

            if (pastRecord.contains("CS113") && (studentCreditHashMap.get(model.currentUser)) < 12) {
                addToHashMap(courseAssignStudentHashMap, course, model.currentUser);
                addToHashMapStudent(studentAsignCourseHashMap, model.currentUser, course);

                model.setStudentRecordHashMap(studentAsignCourseHashMap);
                model.setSubjectRecordHashMap(courseAssignStudentHashMap);
                
                view.errorMessenge("Sucessfully Registered for " + course, "Register Sucess");
                
                model.setStudentCreditHashMap(studentCreditHashMap);
                System.out.println("Student Record" + studentAsignCourseHashMap);

                System.out.println("Course Recoed" + courseAssignStudentHashMap);
            }

            else
                view.errorMessenge("Requirement is not met", "Requirement is not met");
                studentCreditHashMap.put(model.currentUser, (coursework - 4));
            return;

        }

       else if (courseAdded.getValue().equals("CS224")) {

            HashMap<Integer, Set<String>> studentAsignCourseHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentRecordHashMap());
            HashMap<String, Set<Integer>> courseAssignStudentHashMap = new HashMap<String, Set<Integer>>(
                    model.getSubjectRecordHashMap());
            String course = courseAdded.getValue();
            HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentPastRecordHashMap());
            Set<String> pastRecord = new HashSet<>();
            HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());
            Integer coursework = studentCreditHashMap.get(model.currentUser);

            studentCreditHashMap.put(model.currentUser, (coursework+4));


            pastRecord = studentPastRecordHashMap.get(model.currentUser);

            if (pastRecord.contains("CS123") && (studentCreditHashMap.get(model.currentUser)) < 12) {
                addToHashMap(courseAssignStudentHashMap, course, model.currentUser);
                addToHashMapStudent(studentAsignCourseHashMap, model.currentUser, course);

                model.setStudentRecordHashMap(studentAsignCourseHashMap);
                model.setSubjectRecordHashMap(courseAssignStudentHashMap);
                model.setStudentCreditHashMap(studentCreditHashMap);
                view.errorMessenge("Sucessfully Registered for " + course, "Register Sucess");

                System.out.println("Student Record" + studentAsignCourseHashMap);

                System.out.println("Course Recoed" + courseAssignStudentHashMap);
            }

            else
                view.errorMessenge("Requirement is not met", "Requirement is not met");
                studentCreditHashMap.put(model.currentUser, (coursework - 4));
            return;

        }

        else if (courseAdded.getValue().equals("CS234")) {

            HashMap<Integer, Set<String>> studentAsignCourseHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentRecordHashMap());
            HashMap<String, Set<Integer>> courseAssignStudentHashMap = new HashMap<String, Set<Integer>>(
                    model.getSubjectRecordHashMap());
            String course = courseAdded.getValue();
            HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentPastRecordHashMap());
            Set<String> pastRecord = new HashSet<>();
            HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());
            Integer coursework = studentCreditHashMap.get(model.currentUser);

            studentCreditHashMap.put(model.currentUser, 4 + coursework);


            pastRecord = studentPastRecordHashMap.get(model.currentUser);

            if (studentCreditHashMap.get(model.currentUser) < 12) {

                addToHashMap(courseAssignStudentHashMap, course, model.currentUser);
                addToHashMapStudent(studentAsignCourseHashMap, model.currentUser, course);

                model.setStudentRecordHashMap(studentAsignCourseHashMap);
                model.setSubjectRecordHashMap(courseAssignStudentHashMap);
                model.setStudentCreditHashMap(studentCreditHashMap);
                view.errorMessenge("Sucessfully Registered for " + course, "Register Sucess");

                System.out.println("Student Record" + studentAsignCourseHashMap);

                System.out.println("Course Recoed" + courseAssignStudentHashMap);
            }

            else {view.errorMessenge("Requirement is not met", "Requirement is not met");
            studentCreditHashMap.put(model.currentUser, 4 - coursework);
            return;}
        }
        else if (courseAdded.getValue().equals("CS316")) {

            HashMap<Integer, Set<String>> studentAsignCourseHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentRecordHashMap());
            HashMap<String, Set<Integer>> courseAssignStudentHashMap = new HashMap<String, Set<Integer>>(
                    model.getSubjectRecordHashMap());
            String course = courseAdded.getValue();
            HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentPastRecordHashMap());
            Set<String> pastRecord = new HashSet<>();
            HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(
                    model.getStudentCreditHashMap());

            HashMap<Integer, Integer> studentCompleteCreditHashMap = new HashMap<Integer, Integer>(
                        model.getStudentCompletedCreditHashMap());

            pastRecord = studentPastRecordHashMap.get(model.currentUser);

            if (pastRecord.contains("CS133") && pastRecord.contains("CS214") 
                    && studentCompleteCreditHashMap.get(model.currentUser) <= 15 && (studentCreditHashMap.get(model.currentUser)) < 12) {
                addToHashMap(courseAssignStudentHashMap, course, model.currentUser);
                addToHashMapStudent(studentAsignCourseHashMap, model.currentUser, course);

                model.setStudentRecordHashMap(studentAsignCourseHashMap);
                model.setSubjectRecordHashMap(courseAssignStudentHashMap);
                view.errorMessenge("Sucessfully Registered for " + course, "Register Sucess");
                studentCreditHashMap.put(model.currentUser, 4);
                model.setStudentCreditHashMap(studentCreditHashMap);


                System.out.println("Student Record" + studentAsignCourseHashMap);

                System.out.println("Course Recoed" + courseAssignStudentHashMap);
            }

            else
                view.errorMessenge("Requirement is not met", "Requirement is not met");
            return;

        }

        else {
            HashMap<Integer, Set<String>> studentAsignCourseHashMap = new HashMap<Integer, Set<String>>(
                    model.getStudentRecordHashMap());
            HashMap<String, Set<Integer>> courseAssignStudentHashMap = new HashMap<String, Set<Integer>>(
                    model.getSubjectRecordHashMap());
            String course = courseAdded.getValue();
            HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());
            Integer coursework = studentCreditHashMap.get(model.currentUser);

            if (studentCreditHashMap.get(model.currentUser) < 12){
                addToHashMap(courseAssignStudentHashMap, course, model.currentUser);
                addToHashMapStudent(studentAsignCourseHashMap, model.currentUser, course);

                model.setStudentRecordHashMap(studentAsignCourseHashMap);
                model.setSubjectRecordHashMap(courseAssignStudentHashMap);
                view.errorMessenge("Sucessfully Registered for " + course, "Register Sucess");

                System.out.println("Student Record" + studentAsignCourseHashMap);

                System.out.println("Course Recoed" + courseAssignStudentHashMap);
                studentCreditHashMap.put(model.currentUser, (coursework+3));
                model.setStudentCreditHashMap(studentCreditHashMap);
            }

            else
                view.errorMessenge("Credit Hour Limited", "Credit Limit");

        }

    }

    //  
    private static void addToHashMap(HashMap<String, Set<Integer>> hashMap, String key, int value) {
        hashMap.putIfAbsent(key, new HashSet<>());

        Set<Integer> set = hashMap.get(key);
        set.add(value);
    }

    // Store student's ID with their registered courses
    private static void addToHashMapStudent(HashMap<Integer, Set<String>> hashMap, int key, String value) {
        hashMap.putIfAbsent(key, new HashSet<>());

        Set<String> set = hashMap.get(key);
        set.add(value);
    }

    // Display table with information such as lecturer's name and their assigned courses
    public ObservableList<ModelTable> tableLectureSelectedCourse(ChoiceBox<String> selectedSubject) {
        ObservableList<ModelTable> tableLecture = FXCollections.observableArrayList();

        HashMap<String, Set<Integer>> subjectRecordHashMap = new HashMap<String, Set<Integer>>(
                model.getSubjectRecordHashMap());
        HashMap<Integer, String> studentNameHashMap = new HashMap<Integer, String>(model.getStudentNameHashMap());
        String selectedSubjectString = selectedSubject.getValue();

        Set<Integer> integerSet = subjectRecordHashMap.get(selectedSubjectString);

        if (integerSet == null) {
            return tableLecture;
        }

        for (int element : integerSet) {
            // Store the current element in a variable
            int currentValue = element;
            String name = studentNameHashMap.get(currentValue);
            tableLecture.add(new ModelTable(name, currentValue, "0"));

            System.out.println(name);
            System.out.println(currentValue);

        }
        // Display the table
        return tableLecture;
    }

    // Display a table of all users which has registered or assigned to the specific course 
    public ObservableList<ModelTable> tableAdminSelectedCourse(ChoiceBox<String> selectedSubject) {

        ObservableList<ModelTable> tableLecture = FXCollections.observableArrayList();

        HashMap<String, Set<Integer>> subjectRecordHashMap = new HashMap<String, Set<Integer>>(
                model.getSubjectRecordHashMap());
        HashMap<Integer, String> studentNameHashMap = new HashMap<Integer, String>(model.getStudentNameHashMap());
        HashMap<String, Integer> lectureRecordHashMap = new HashMap<String, Integer>(model.getLectureRecordHashMap());
        HashMap<Integer, String> lectureNameHashMap = new HashMap<Integer, String>(model.getTeacherNameHashMap());

        String selectedSubjectString = selectedSubject.getValue();
        Set<Integer> integerSet = subjectRecordHashMap.get(selectedSubjectString);

        Integer lectureId = lectureRecordHashMap.get(selectedSubjectString);
        String lecturename = lectureNameHashMap.get(lectureId);

        tableLecture.add(new ModelTable(lecturename, lectureId, "Lecture"));

        if (integerSet == null) {
            return tableLecture;
        }

        for (int element : integerSet) {
            // Store the current element in a variable
            int currentValue = element;
            String name = studentNameHashMap.get(currentValue);
            tableLecture.add(new ModelTable(name, currentValue, "Student"));

            System.out.println(name);
            System.out.println(currentValue);
        }
        // Display the table
        return tableLecture;
    }

    // Check whether the student has reach the minimum credits needed for the semester
    public void checkMinCourseWork() {

        HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());

        Integer courseworkHour = studentCreditHashMap.get(model.currentUser);

        if (courseworkHour < 3) {
            view.errorMessenge("Not enough Credit ", "Please Enroll to More Classes");
        }

        else
            return;

    }

    // Get courses for the upcoming semester
    public Set<String> getStudentFutureRecord() {
        HashMap<Integer, Set<String>> studentFutureRecordHashMap = new HashMap<Integer, Set<String>>(
                model.getStudentFutureRecordHashMap());
        Set<String> future = new HashSet<>(studentFutureRecordHashMap.get(model.currentUser));
        // return the elements inside the set
        return future;
    }

    // Get courses for the previous semester
    public Set<String> getStudentPastRecord() {
        HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>(
                model.getStudentPastRecordHashMap());
        Set<String> past = new HashSet<>(studentPastRecordHashMap.get(model.currentUser));
        // return the elements inside the set
        return past;
    }

    // Get courses for the current semester
    public Set<String> getStudentRecord() {
        HashMap<Integer, Set<String>> studentRecordHashMap = new HashMap<Integer, Set<String>>(
                model.getStudentRecordHashMap());
        Set<String> current = new HashSet<>(studentRecordHashMap.get(model.currentUser));
        // return the elements inside the set
        return current;
    }

    public int currenTrimInteger = 1;

    // Allows the change of semester from semester 1 to semester 3
    public void trimesterSystem(String trimester_num) {
        HashMap<Integer, Set<String>> tempstudentRecordHashMap = new HashMap<>(model.getStudentRecordHashMap());
        HashMap<Integer, Set<String>> tempstudentPastRecordHashMap = new HashMap<>(model.getStudentPastRecordHashMap());
        HashMap<Integer, Set<String>> tempstudentFutureRecordHashMap = new HashMap<>(
                model.getStudentFutureRecordHashMap());
        HashMap<String, Integer> lectureRecordHashMap = new HashMap<String, Integer>(model.getLectureRecordHashMap());
        HashMap<String, Set<Integer>> subjectRecordHashMap = new HashMap<String, Set<Integer>>(
                model.getSubjectRecordHashMap());
        Set<String> courseAvailablSet = new HashSet<String>(model.getCourseAvailablSet());


        HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCreditHashMap());
        HashMap<Integer, Integer> studentCompletedCreditHashMap = new HashMap<Integer, Integer>(model.getStudentCompletedCreditHashMap());

        for (Map.Entry<Integer, Set<String>> entry : tempstudentRecordHashMap.entrySet()) {
            Integer key = entry.getKey();
            Set<String> originalSet = entry.getValue();
            Set<String> copiedSet = new HashSet<>(originalSet); // Create a deep copy of the set
            Set<String> copiedSetpast = new HashSet<>(tempstudentPastRecordHashMap.get(key));
            copiedSet.addAll(copiedSetpast);
            tempstudentFutureRecordHashMap.get(key).removeIf(copiedSet::contains);

            tempstudentPastRecordHashMap.put(key, copiedSet);
            originalSet.clear();
        }

        for (Map.Entry<Integer, Integer> entry : studentCreditHashMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue() + studentCompletedCreditHashMap.getOrDefault(key, 0);
            studentCompletedCreditHashMap.put(key, value);
        }

        for (Map.Entry<Integer, Integer> entry : studentCreditHashMap.entrySet()) {
            int key = entry.getKey();
            int value = 0;
            studentCreditHashMap.put(key, value);
        }



        courseAvailablSet.clear();
        lectureRecordHashMap.clear();
        subjectRecordHashMap.clear();



        model.setStudentPastRecordHashMap(tempstudentPastRecordHashMap);
        model.setStudentRecordHashMap(tempstudentRecordHashMap);
        model.setStudentFutureRecordHashMap(tempstudentFutureRecordHashMap);
        model.setLectureRecordHashMap(lectureRecordHashMap);
        model.setSubjectRecordHashMap(subjectRecordHashMap);
        model.setStudentCompletedCreditHashMap(studentCompletedCreditHashMap);
        model.setStudentCreditHashMap(studentCreditHashMap);
        model.setCourseAvailablSet(courseAvailablSet);

        view.errorMessenge("Trimester Change", "Trimester Change");

        if (currenTrimInteger == 3){
            currenTrimInteger = 1;
        }
        else{currenTrimInteger++;}

    }
}
