public class Employee {

    private String name;
    private String[] skills;
    public boolean isAvailable;

    public Employee(String name, String[] skills, boolean isAvailable) {
        this.name = name;
        this.skills = skills;
        this.isAvailable = isAvailable;
    }
}
