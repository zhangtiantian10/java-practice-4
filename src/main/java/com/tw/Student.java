package com.tw;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Student {
  private int id;

  private String name;

  private List<Subject> subjects;

  private int totalScore;

  private double average;

  public Student() {
  }

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getTotalScore() {
    Optional<Integer> optional = subjects.stream()
      .map(subject -> subject.getScore())
      .reduce((sum, next) -> sum + next);
    if (optional.isPresent()) {
      totalScore = optional.get();
    }
    return totalScore;
  }

  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  public double getAverage() {
    Optional<Integer> optional = subjects.stream()
      .map(subject -> subject.getScore())
      .reduce((sum, next) -> sum + next);
    if (optional.isPresent()) {
      average = optional.get() / subjects.size();
    }
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

}
