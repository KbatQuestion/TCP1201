package Part2.Model;

public class ModelTable {

    private String name;
    private int id;
    private String course;

    // Table with column name, ID, and course
    public ModelTable() {
        this.name = "";
        this.id = 0;
        this.course = "";

    }

    // Display a table of information which includes the user's name (Student/Lecturer),
    // their ID, and the assigned/registered course
    public ModelTable(String name, int id, String course) {
        this.name = name;
        this.id = id;
        this.course = course;

    }

    // Getter
    public String getName() {
        return name;

    }

    // Setter 
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public int getId() {
        return id;
    }

    // Setter 
    public void setId(int id) {
        this.id = id;
    }

    // Getter
    public String getCourse() {
        return course;

    }

    // Setter 
    public void setCourse(String course) {
        this.course = course;
    }

}
