package Controllers;
import java.text.Normalizer;
import Model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.*;
import java.util.regex.Pattern;

public class SortData {
    public static List<Student> SortByNameZA(List<Student> a){
        Collections.sort(a, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                String name1 = s1.getName();
                String name2 = s2.getName();

                String lastName1 = name1.substring(name1.lastIndexOf(" ") + 1);
                String lastName2 = name2.substring(name2.lastIndexOf(" ") + 1);

                char lastChar1 = lastName1.charAt(0);
                char lastChar2 = lastName2.charAt(0);

                // Sắp xếp ngược lại, từ Z đến A
                return Character.compare(lastChar2, lastChar1);
            }
        });
        return a;
    }

    public static List<Student> SortByNameAZ(List<Student> a){
        Collections.sort(a, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                String name1 = s1.getName();
                String name2 = s2.getName();

                String lastName1 = name1.substring(name1.lastIndexOf(" ") + 1);
                String lastName2 = name2.substring(name2.lastIndexOf(" ") + 1);

                char firstChar1 = lastName1.charAt(0);
                char firstChar2 = lastName2.charAt(0);

                return Character.compare(firstChar1, firstChar2);
            }
        });
        return a;
    }

    public static String removeAccents(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedText).replaceAll("");
    }

    public static List<Student> searchByName(List<Student> students, String nameToSearch) {
        String searchKey = removeAccents(nameToSearch.toLowerCase());
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            String studentName = removeAccents(student.getName().toLowerCase());
            if (studentName.contains(searchKey)) {
                result.add(student);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("Ngo Quang Duong");
        Student student2 = new Student();
        student2.setName("Dinh Xuan Tung");
        Student student3 = new Student();
        student3.setName("Pham Minh Quan");
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // Tìm kiếm danh sách sinh viên theo tên
        String nameToSearch = "Quân";
        List<Student> foundStudents = SortData.searchByName(students, nameToSearch);

        // In danh sách sinh viên tìm thấy
        if (foundStudents.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên có tên: " + nameToSearch);
        } else {
            System.out.println("Danh sách sinh viên có tên: " + nameToSearch);
            for (Student student : foundStudents) {
                System.out.println(student.getName());
            }
        }
    }
}
