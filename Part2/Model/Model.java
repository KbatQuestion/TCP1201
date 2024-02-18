package Part2.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Model {

    public Integer currentUser;
    public Integer currenTrimInteger = 1;
    public Set<Integer> takenID = new HashSet<Integer>();

    // Store course code and their course hour
    private HashMap<String, Integer> subjectCourseHour = new HashMap<String, Integer>();
    // Store course code and their prerequisite subjects
    public HashMap<String, Set<String>> subjectPreRequsiteHashMap = new HashMap<String, Set<String>>();

    // Store teacher's ID and their respective password
    private HashMap<Integer, String> teacherPasswordHashMap = new HashMap<Integer, String>();
    // Store teacher's ID and their respective name
    private HashMap<Integer, String> teacherNameHashMap = new HashMap<Integer, String>();
    // Store teacher's ID and the course assigned to them
    private HashMap<Integer, ArrayList<String>> teacherAsignCourseHashMap = new HashMap<Integer, ArrayList<String>>();

    // Store student's ID and their respective password
    private HashMap<Integer, String> studentPasswordHashMap = new HashMap<Integer, String>();
    // Store student's ID and their respective name
    private HashMap<Integer, String> studentNameHashMap = new HashMap<Integer, String>();
    // Store student's ID and their current registered course
    private HashMap<Integer, Set<String>> studentRecordHashMap = new HashMap<Integer, Set<String>>();
    // Store student's ID and their past registered course
    private HashMap<Integer, Set<String>> studentPastRecordHashMap = new HashMap<Integer, Set<String>>();
    // Store student's ID and their course for upcoming semester
    private HashMap<Integer, Set<String>> studentFutureRecordHashMap = new HashMap<Integer, Set<String>>();
    // Store student's ID and their total credits
    private HashMap<Integer, Integer> studentCreditHashMap = new HashMap<Integer, Integer>();
    // Store student's ID and their credits for completed course
    private HashMap<Integer, Integer> studentCompletedCreditHashMap = new HashMap<Integer, Integer>();

    // Store teacher's ID and the assigned course code
    private HashMap<String, Integer> lectureRecordHashMap = new HashMap<String, Integer>();
    //
    private HashMap<String, Set<Integer>> subjectRecordHashMap = new HashMap<String, Set<Integer>>();
    // Store all courses in the system
    private Set<String> courseAvailablSet = new HashSet<String>();

    // Getter to get all the keys and values inside the teacherPasswordHashMap
    public HashMap<Integer, String> getTeacherPasswordHashMap() {
        return teacherPasswordHashMap;
    }

    // Setter to copy the keys and values of the called hash map to teacherPasswordHashmap
    public void setTeacherPasswordHashMap(HashMap<Integer, String> newTeacherPaswordHashMap) {
        this.teacherPasswordHashMap = newTeacherPaswordHashMap;
    }

    // Getter to get all the keys and values inside the teacherNameHashMap
    public HashMap<Integer, String> getTeacherNameHashMap() {
        return teacherNameHashMap;
    }

    // Setter to copy the keys and values of the called hash map to teacherNameHashMap
    public void setTeacherNameHashMap(HashMap<Integer, String> newTeacherNameHashMap) {
        this.teacherNameHashMap = newTeacherNameHashMap;
    }

    // Getter to get all the keys and values inside the teacherAsignCourseHashMap
    public HashMap<Integer, ArrayList<String>> getTeacherAsignCourseHashMap() {
        return teacherAsignCourseHashMap;
    }

    // Setter to copy the keys and values of the called hash map to teacherAsignCourseHashMap
    public void setTeacherAsignCourseHashMap(HashMap<Integer, ArrayList<String>> newTeacherAsignCourseHashMap) {
        this.teacherAsignCourseHashMap = newTeacherAsignCourseHashMap;
    }

    // Getter to get all the keys and values inside the studentPasswordHashMap
    public HashMap<Integer, String> getStudentPasswordHashMap() {
        return studentPasswordHashMap;
    }

    // Setter
    public void setStudentPasswordHashMap(HashMap<Integer, String> newStudentPaswordHashMap) {
        this.studentPasswordHashMap = newStudentPaswordHashMap;
    }

    // Getter to get all the keys and values inside the studentNameHashMap
    public HashMap<Integer, String> getStudentNameHashMap() {
        return studentNameHashMap;
    }

    // Setter
    public void setStudentNameHashMap(HashMap<Integer, String> newStudentNameHashMap) {
        this.studentNameHashMap = newStudentNameHashMap;
    }

    // Getter to get all the keys and values inside the studentRecordHashMap
    public HashMap<Integer, Set<String>> getStudentRecordHashMap() {
        return studentRecordHashMap;
    }

    // Setter
    public void setStudentRecordHashMap(HashMap<Integer, Set<String>> newStudentRecordHashMap) {
        this.studentRecordHashMap = newStudentRecordHashMap;
    }

    // Getter to get all the keys and values inside the lectureRecordHashMap
    public HashMap<String, Integer> getLectureRecordHashMap() {
        return lectureRecordHashMap;
    }

    // Setter
    public void setLectureRecordHashMap(HashMap<String, Integer> newAssignLectureHashMap) {
        this.lectureRecordHashMap = newAssignLectureHashMap;
    }

    // Getter to get all the keys and values inside the subjectRecordHashMap
    public HashMap<String, Set<Integer>> getSubjectRecordHashMap() {
        return subjectRecordHashMap;
    }

    // Setter
    public void setSubjectRecordHashMap(HashMap<String, Set<Integer>> newAssignStudentHashMapp) {
        this.subjectRecordHashMap = newAssignStudentHashMapp;
    }

    // Getter to get all the keys and values inside the courseAvailablSet
    public Set<String> getCourseAvailablSet() {
        return courseAvailablSet;
    }

    // Setter
    public void setCourseAvailablSet(Set<String> newCourseAvailablSet) {
        this.courseAvailablSet = newCourseAvailablSet;
    }

    // Getter to get all the keys and values inside the studentFutureRecordHashMap
    public HashMap<Integer, Set<String>> getStudentFutureRecordHashMap() {
        return studentFutureRecordHashMap;
    }

    // Setter
    public void setStudentFutureRecordHashMap(HashMap<Integer, Set<String>> newStudentFutureRecordHashMap) {
        this.studentFutureRecordHashMap = newStudentFutureRecordHashMap;
    }

    // Getter to get all the keys and values inside the studentPastRecordHashMap
    public HashMap<Integer, Set<String>> getStudentPastRecordHashMap() {
        return studentPastRecordHashMap;
    }

    // Setter
    public void setStudentPastRecordHashMap(HashMap<Integer, Set<String>> newStudentPastRecordHashMap) {
        this.studentPastRecordHashMap = newStudentPastRecordHashMap;
    }

    // Getter to get all the keys and values inside the subjectCourseHour
    public HashMap<String, Integer> getSubjectCourseHour() {
        return subjectCourseHour;
    }

    // Setter
    public void setSubjectCourseHour(HashMap<String, Integer> newSubjectCourseHour) {
        this.subjectCourseHour = newSubjectCourseHour;
    }

    // Getter to get all the keys and values inside the studentCreditHashMap
    public HashMap<Integer, Integer> getStudentCreditHashMap() {
        return studentCreditHashMap;
    }

    // Setter
    public void setStudentCreditHashMap(HashMap<Integer, Integer> newStudentCreditHashMap) {
        this.studentCreditHashMap = newStudentCreditHashMap;
    }

    // Getter to get all the keys and values inside the subjectPreRequisiteHashMap
    public HashMap<String, Set<String>> getSubtjectPreRequsiteHashMap() {
        return subjectPreRequsiteHashMap;
    }

    // Setter
    public void setSubtjectPreRequsiteHashMap(HashMap<String, Set<String>> newSubjectPreRequsiteHashMap) {
        this.subjectPreRequsiteHashMap = newSubjectPreRequsiteHashMap;
    }

    // Getter to get all the keys and values inside the studentCompletedCreditHashMap
    public HashMap<Integer, Integer> getStudentCompletedCreditHashMap() {
        return studentCompletedCreditHashMap;
    }

    // Setter
    public void setStudentCompletedCreditHashMap(HashMap<Integer, Integer> newStudentCompletedCreditHashMap) {
        this.studentCompletedCreditHashMap = newStudentCompletedCreditHashMap;
    }
}
