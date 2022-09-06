package com.revature.entity;

public class Employee {

    private int id;

    private String username;

    private String password;

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    private boolean manager;

    public int getId() {
        return id;
    }
    public Employee() {

    }

    public Employee(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Employee(int id, String username, String password, boolean manager) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.manager=manager;
    }
    public Employee(String username, String password, boolean manager) {
        this.username = username;
        this.password = password;
        this.manager = manager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
// add boolean isManager???