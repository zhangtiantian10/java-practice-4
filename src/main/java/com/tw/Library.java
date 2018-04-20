package com.tw;

import java.util.ArrayList;
import java.util.List;

public class Library {

  public static void main(String[] args) {
    Menu menu = new Menu();
    List<Student> students = new ArrayList<>();
    Console console = new Console();

    menu.printMenu(console);
    menu.judgeNumber(console);

    while (menu.isAlive()) {
      Validator validator = new Validator();

      if (console.getMenuNumber() == 1) {
        if (!validator.checkoutStudent(console.getStudentStr())) {
          console.log(validator.getStudentErrorMessage());
          console.readStudent();
        } else {
          Student student = validator.getStudent();
          students.add(student);
          console.log("学生" + student.getName() +"的成绩被添加\n");
          menu.printMenu(console);
          menu.judgeNumber(console);
        }
      } else if (console.getMenuNumber() == 2) {
        if (!validator.checkoutStudentIds(console.getIds())) {
          console.log(validator.getStudentIdsErrorMessage());
          console.readIds();
        } else {
          List<Integer> ids = validator.getIds();
          console.log(menu.getSchoolReport(students, ids));
          menu.printMenu(console);
          menu.judgeNumber(console);
        }
      }
    }
  }
}
