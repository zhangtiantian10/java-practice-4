package com.tw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Validator {
  private Pattern pattern = Pattern.compile("[0-9]*");

  private Student student = new Student();

  private List<Subject> subjects = new ArrayList<>();

  private List<Integer> ids;

  public List<Integer> getIds() {
    return ids;
  }

  public void setIds(List<Integer> ids) {
    this.ids = ids;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  public boolean checkoutStudent(String studentStr) {
    String[] studentSplits = studentStr.split(", ");
    if (studentSplits.length != 5) {
      return false;
    }
    Optional optional = Arrays.stream(studentSplits)
      .collect(Collectors.toList())
      .subList(2, studentSplits.length)
      .stream()
      .filter(item -> !checkoutSubjective(item))
      .findFirst();

    if (optional.isPresent() || !pattern.matcher(studentSplits[1]).matches()) {
      return false;
    }

    student.setName(studentSplits[0]);
    student.setId(Integer.parseInt(studentSplits[1]));
    student.setSubjects(subjects);

    return true;
  }

  private boolean checkoutSubjective(String subjectiveStr) {
    String[] subjectiveSplits = subjectiveStr.split(": ");

    if (subjectiveSplits.length != 2 || !pattern.matcher(subjectiveSplits[1]).matches()) {
      return false;
    }

    Subject subject = new Subject(subjectiveSplits[0], Integer.parseInt(subjectiveSplits[1]));

    subjects.add(subject);

    return true;
  }

  public boolean checkoutStudentIds(String idStr) {
    if (idStr.equals("")) {
      return false;
    }
    String[] ids = idStr.split(", ");
    Optional optional = Arrays.stream(ids)
      .filter(id -> !pattern.matcher(id).matches())
      .findFirst();

    if (optional.isPresent()) {
      return false;
    }

    this.ids = Arrays.stream(ids)
      .map(id -> Integer.parseInt(id))
      .collect(Collectors.toList());

    return true;
  }

  public String getStudentErrorMessage() {
    return "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）: ";
  }

  public String getStudentIdsErrorMessage() {
    return "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交: ";
  }
}
