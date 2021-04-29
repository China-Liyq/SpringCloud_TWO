package com.chinaliyq.consumerserver.beans;

/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/29 - 22:23
 * @Version: 1.0
 **/
public class Person {
    private String name;
    private char sex;
    private int age;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    public Person(String name, char sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
