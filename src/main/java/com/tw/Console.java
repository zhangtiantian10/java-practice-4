package com.tw;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Console {
  private Scanner sc = new Scanner(System.in);

  private int menuNumber = 0;
  private String studentStr = "";
  private String ids = "";

  public Console() {
  }

  public int getMenuNumber() {
    return menuNumber;
  }

  public void setMenuNumber(int menuNumber) {
    this.menuNumber = menuNumber;
  }

  public String getStudentStr() {
    return studentStr;
  }

  public void setStudentStr(String studentStr) {
    this.studentStr = studentStr;
  }

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }

  public void readMenu() {
    menuNumber = Integer.parseInt(sc.nextLine());
  }

  public void readStudent() {
    studentStr = sc.nextLine();
  }

  public void readIds() {
    ids = sc.nextLine();
  }

  public void log(String message) {
    System.out.println(message);
  }

  public void log(List<Student> students, List<Integer> ids) {

    List<Student> searchStudents = students.stream()
      .filter(student -> ids.contains(student.getId()))
      .collect(Collectors.toList());

    String message = "成绩单\n" + getSchoolReportTitle(searchStudents);

    message += "========================\n";

    message += searchStudents.stream()
      .map(student -> {
        String scoreStr = student.getSubjects().stream()
          .map(subject -> String.valueOf(subject.getScore()))
          .collect(Collectors.joining("|"));
        return student.getName() + "|" + scoreStr + "|" + student.getAverage() + "|" + student.getTotalScore();
      })
      .collect(Collectors.joining("\n"));

    message += "\n========================\n";

    int total = getClassTotal(students);
    double average = getClassAverage(total, students.size());

    message += "全班总分平均数: " + getClassAverage(total, students.size()) + "\n";

    message += "全班总分中位数: " + getMiddleScore(students);

    System.out.println(message);
  }

  private String getSchoolReportTitle(List<Student> students) {
    String subjectStr = students.stream()
      .map(student -> student.getSubjects())
      .flatMap(subjects -> subjects.stream())
      .map(subject -> subject.getName())
      .distinct()
      .collect(Collectors.joining("|"));

    return "姓名|" + subjectStr + "|平均分|总分\n";
  }

  private int getClassTotal(List<Student> students) {
    Optional<Integer> optional = students.stream()
      .map(student -> student.getTotalScore())
      .reduce((sum, next) -> sum + next);
    if (optional.isPresent()) {
      return optional.get();
    }

    return 0;
  }

  private double getClassAverage(int total, int size) {
    return total / size;
  }

  private double getMiddleScore(List<Student> students) {
    students = students.stream()
      .sorted(Comparator.comparing(student -> student.getTotalScore()))
      .collect(Collectors.toList());
    int size = students.size();
    double result;
    if (size % 2 == 0) {
      result = (students.get(size / 2).getTotalScore() + students.get((size / 2) - 1).getTotalScore()) / 2;
    } else {
      int index = size / 2;

      result = students.get(index).getTotalScore();
    }

    return result;
  }
}
