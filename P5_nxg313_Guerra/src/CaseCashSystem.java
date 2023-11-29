import java.util.ArrayList;
import java.util.List;

public class CaseCashSystem {
    private List<Student> students;

    public CaseCashSystem() {
        this.students = new ArrayList<>();
    }

    /**
     * Function used to run the simulation provided by the commands. This
     * function should parse the commands and call their respective helper
     * function to complete a task.
     * @param commands to complete a task
     * @return
     */

    public List<String> runSimulation(List<String> commands) {
        // Clear the previous list of students
        students.clear();

        List<String> output = new ArrayList<>();
        // commands: "INIT", "GET", "DEPOSIT", "TRANSFER", "SORT", "WITHDRAWAL"

        for (String command : commands) {
            String[] array = command.split(",");

            switch (array[0].trim()) {
                case "INIT":
                    String name = array[1].trim();
                    int bal = Integer.parseInt(array[2].trim());
                    output.add(init(name, bal) ? "true" : "false"); // add result to output list
                    break;

                case "GET":
                    String nameToGetBalance = array[1].trim();
                    output.add(getBalance(nameToGetBalance) + "");
                    break;

                case "TRANSFER":
                    int t = Integer.parseInt(array[3].trim());
                    // test cases assume that findStudentByName() will not be null; (only valid inputs)
                    output.add(transfer(findStudentByName(array[1].trim()), findStudentByName(array[2].trim()), t)
                            ? "true" : "false");
                    break;

                case "WITHDRAWAL":
                    output.add(withdrawal(findStudentByName(array[1].trim()), Integer.parseInt(array[2].trim()) )
                            ? "true" : "false");
                    break;

                case "DEPOSIT":
                    output.add(deposit(findStudentByName(array[1].trim()), Integer.parseInt(array[2].trim()) )
                            ? "true" : "false");
                    break;

                case "SORT":
                    // sort by name (merge sort)
                    if (array[1].trim().equals("name")) {
                        List<String> sortedNamesList = sortName();
                        output.add(sortedNamesList.toString());
                    }
                    else if (array[1].trim().equals("balance")) {
                        List<String> sortedNamesList = sortBalance();
                        output.add(sortedNamesList.toString());
                    }
                    break;
            }

        }
        return output;

    }

    /**
     * Finds the student object given the name of the student
     * @param name
     * @return
     */
    private Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null; // Student not found
    }



    /**
     * Initializes a student with a name and an initial account balance.
     * This should return true if the student has not been created already,
     * and return false if a student with this name already exists.
     * InitialBalance cannot be negative. Corresponds to “INIT, name,
     * initialBalance”
     * @param name name of the student to create
     * @param initialBalance starting balance of the student
     * @return true if initialization was successful
     */
    public boolean init(String name, int initialBalance) {
        // check if student exists, if they do return false
        for (Student s : students) {
            if (s.getName().equals(name)) return false;
        }
        // else create student
        students.add(new Student(name, initialBalance));
        return true;
    }

    /**
     * returns the balance of a given student
     */
    public int getBalance(String name) {
        // find Student and return its balance
        for (Student s : students) {
            if (s.getName().equals(name)) {
                return s.getBalance();
            }
        }
        System.out.println("Student does not exist");
        return 0;
    }

    /**
     * Deposit money from a student account. Return true if deposit is
     * successful, and return false if deposit fails (negative input).
     * If false, the balance of the student account should not be changed at
     * all.
     */
    public boolean deposit(Student student, int amount) {
        if (student == null || amount < 0) {
            return false;
        }

        int cur = student.getBalance();
        cur = cur + amount;
        student.updateBalance(cur);
        return true;
    }

    /**
     * Transfers the amount from studentA account to studentB account.
     * @param studentA
     * @param studentB
     * @param amount
     * @return false if transfer results in a negative balance and true if
     * transfer is sucessfull
     */
    public boolean transfer(Student studentA, Student studentB, int amount) {
        // if (studentA == null || studentB == null) return false;

        // if the transfer results in a negative balance return false
        int b = studentA.getBalance() - amount;
        if (b < 0) return false;

        studentA.updateBalance(b);
        int a = studentB.getBalance() + amount;
        studentB.updateBalance(a);
        return true;
    }

    /**
     * sorts using merge sort
     * @return Returns a list of student names in alphabetical order
     */
    public List<String> sortName() {
        List<Student> inputList = new ArrayList<>(students);
        mergeSort(inputList);

        List<String> sortedNamesList = new ArrayList<>();
        for (Student s : inputList) {
            sortedNamesList.add(s.getName());
        }
        // System.out.print(sortedNamesList);
        return sortedNamesList;
    }

    private static void mergeSort(List<Student> inputList) {
        if (inputList.size() < 2) return; // base case
        int m = inputList.size() / 2;
        List<Student> leftHalf = new ArrayList<>();
        List<Student> rightHalf = new ArrayList<>();

        // copy left half
        for (int i = 0; i < m; i++) {
            leftHalf.add(inputList.get(i));
        }
        // copy right half
        for (int i = m; i < inputList.size(); i++) {
            rightHalf.add(inputList.get(i));
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(inputList, leftHalf, rightHalf);
    }
    private static void merge(List<Student> inputList, List<Student> leftHalf, List<Student> rightHalf) {
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            //compare elements based on alphabetical order and set to ordered list
            if ((leftHalf.get(i).getName().compareToIgnoreCase(rightHalf.get(j).getName()) < 0)) {
                inputList.set(k, leftHalf.get(i));
                i++;
            }
            else {
                inputList.set(k, rightHalf.get(j));
                j++;
            }
            k++;
        }
        // add any remaining elements
        while (i < leftSize) {
            inputList.set(k, leftHalf.get(i));
            i++;
            k++;
        }
        while (j < rightSize) {
            inputList.set(k, rightHalf.get(j));
            j++;
            k++;
        }
    }


    /**
     * sorts utilizing quicksort
     * @return Returns a list of student names in the order of smallest balance to
     * largest balance in their account.
     */

    public List<String> sortBalance() {
        List<Student> input = new ArrayList<>(students);
        quickSort(input, 0, input.size() - 1);

        List<String> sortedNamesList = new ArrayList<>();
        for (Student s : input) {
            sortedNamesList.add(s.getName());
        }
        // System.out.println(sortedNamesList);
        return sortedNamesList;

    }

    private static void quickSort(List<Student> input, int low, int high) {
        if (low >= high) return;
        // choose pivot
        int pivot = input.get(high).getBalance();
        int l = low;
        int r = high;

        // find values to swap based on pivots
        while (l < r) {
            while (input.get(l).getBalance() <= pivot && l < r) l++;
            while (input.get(r).getBalance() >= pivot && l < r) r--;

            // swap elements
            swap(input, l, r);
        }

        // swap pivot with the value that our left pointer is pointing to
        swap(input, l, high);

        // recursive quicksort left partition
        quickSort(input, low, l-1);

        // recursive quicksort right partition
        quickSort(input, l+1, high);
    }
    private static void swap(List<Student> input, int i, int j) {
        Student temp = input.get(i);
        input.set(i, input.get(j));
        input.set(j, temp);
    }

    /**
     * Remove money from a student account
     * @param student
     * @return true if remove is successful, and return false if
     * removing will result in a negative balance.
     */
    public boolean withdrawal(Student student, int amount) {
        if (student == null) return false;

        int bal = student.getBalance() - amount;
        if (bal < 0) return false;
        else student.updateBalance(bal);
        return true;
    }

    public static void main(String[] args) {
        // A few SAMPLE TEST CASES (MORE IN THE JUNIT TEST file)
        CaseCashSystem a = new CaseCashSystem();
        // a.sortName();
        List<String> commands = new ArrayList<>();
        commands.add("INIT, nico, 100");
        commands.add("INIT, abby, 1");
        commands.add("INIT, becky, 5");
        commands.add("INIT, will, 20");
        commands.add("INIT, Zelda, 70");
        commands.add("SORT, balance");

        commands.add("TRANSFER, nico, Zelda, 20");
        commands.add("GET, nico");
        commands.add("GET, Zelda");
        commands.add("WITHDRAWAL, nico, 50");
        commands.add("GET, nico");
        commands.add("DEPOSIT, nico, 100");
        commands.add("GET, nico");
        commands.add("SORT, name");
        commands.add("DEPOSIT, lewiidnf, 100");
        commands.add("WITHDRAWAL, nkcenec, 50");

        List<String> outputs = a.runSimulation(commands);
        System.out.println(outputs);


    }

}


