package com.revature.entity;

public class Ticket {

    private int id;

    private int amount;

    private String description;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    private int employee_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public Ticket () {
    }


 /*   public Ticket(int id, int amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

  */
  /*  public Ticket(int id, int amount, String description, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

   */
    public Ticket(int id, int amount, String description, int employee_id, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.employee_id = employee_id;
        this.status = status;
    }


// put in status and got error for both constructors
    public Ticket(int amount, String description, int employee_id, String status) {
        this.amount = amount;
        this.description = description;
        this.employee_id = employee_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description +
                ", employee_id=" + employee_id +
                ", status=" + status + '\'' +
                '}';
    }
}
