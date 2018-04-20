package com.tw;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Menu {

  private boolean isAlive = true;

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public void printMenu(Console consoleRead) {
    System.out.print("1. 添加学生\n" +
      "2. 生成成绩单\n" +
      "3. 退出\n" +
      "请输入你的选择（1～3）：");
    consoleRead.readMenu();
  }

  public void judgeNumber(Console console) {
    switch (console.getMenuNumber()) {
      case 1:
        System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        console.readStudent();
        break;
      case 2:
        System.out.print("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        console.readIds();
        break;
      case 3:
        isAlive = false;
        break;
      default:
        this.printMenu(console);
    }
  }

  public String getSchoolReport(List<Student> students, List<Integer> ids) {

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

    message += "全班总分中位数: " + getMiddleScore(students) + "\n";

    return message;
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
    if (total == 0) {
      return 0;
    }
    return total / size;
  }

  private double getMiddleScore(List<Student> students) {
    students = students.stream()
      .sorted(Comparator.comparing(student -> student.getTotalScore()))
      .collect(Collectors.toList());
    int size = students.size();
    double result = 0;
    if (size % 2 == 0 && size != 0) {
      result = (students.get(size / 2).getTotalScore() + students.get((size / 2) - 1).getTotalScore()) / 2;
    } else if (size != 0){
      int index = size / 2;

      result = students.get(index).getTotalScore();
    }

    return result;
  }
}
