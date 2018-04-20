package com.tw;

import java.util.Scanner;

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
    String input = sc.nextLine();
    if (!input.equals("")) {
      menuNumber = Integer.parseInt(input);
    }
  }

  public void readStudent() {
    studentStr = sc.nextLine();
  }

  public void readIds() {
    ids = sc.nextLine();
  }

  public void log(String message) {
    System.out.print(message);
    studentStr = "";
    ids = "";
  }
}
