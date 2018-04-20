package com.tw;

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
}
