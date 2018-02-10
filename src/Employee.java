public class Employee {
    public static int nextId = 0;

    public int id;
    private String name;
    private String[] skills;
    public boolean isAvailable;

    public Employee(String name, String[] skills) {
        this.id = Employee.nextId++;
        this.name = name;
        this.skills = skills;
        this.isAvailable = true;
    }

    public Employee(String name) {
        this(name, new String[0]);
    }
}