public class Student {
    // name of the student
    private String name;

    // balance of Student
    private int balance;

    // Constructor
    public Student(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     *
     * @return the current balance of the student
     */
    public int getBalance(){
        return balance;
    }

    /**
     * updates the balance of the Student with newAmount
     * @param newAmount
     */
    public void updateBalance(int newAmount){
        balance = newAmount;
    }

    /**
     * returns the name of the student
     */
    public String getName(){
        return name;
    }




}
