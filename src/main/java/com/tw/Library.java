package com.tw;

import java.util.ArrayList;
import java.util.List;

public class Library {

  public static void main(String[] args) {
    Menu menu = new Menu();
    List<Student> students = new ArrayList<>();

    while (menu.isAlive()) {
      Console console = new Console();
      Validator validator = new Validator();

      menu.printMenu(console);
      menu.judgeNumber(console);

      if (!console.getStudentStr().equals("")) {
        if (!validator.checkoutStudent(console.getStudentStr())) {
          console.log("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        } else {
          Student student = validator.getStudent();
          students.add(student);
          console.log("学生" + student.getName() +"的成绩被添加");
        }
      } else if (!console.getIds().equals("")) {
        if (!validator.checkoutStudentIds(console.getIds())) {
          console.log("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        } else {
          List<Integer> ids = validator.getIds();
          console.log(students, ids);
        }
      }
    }
  }
}
