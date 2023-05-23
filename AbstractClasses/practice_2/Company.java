
import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private double income;
    private final String nameCompany;

    private ArrayList<Employee> employees;


    public Company(String nameCompany) {
        this.nameCompany = nameCompany;
        employees = new ArrayList<>();
    }

    public void hire(Employee employee) {
        employees.add(employee);
        Collections.sort(employees);
    }

    public void hireAll(ArrayList<Employee> employee) {
        employees.addAll(employee);
        Collections.sort(employees);
    }

    public void fire(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            return;
        }
        System.out.println("not found the employee");
    }

    public void addIncome(double addition){
        income += addition;
    }

    public double getIncome() {
        return income;
    }


    public ArrayList<Employee> getTopSalaryStaff(int count) {

        if (countMoreArraySize(count)) {
            return new ArrayList<>();
        }

        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            topSalaryStaff.add(i, employees.get(i));
            System.out.println(topSalaryStaff.get(i));
        }
        return topSalaryStaff;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        if (countMoreArraySize(count)) {
            return new ArrayList<>();
        }

        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();
        for (int i = employees.size() - count; i < employees.size(); i++) {
            int j = 0;
            lowestSalaryStaff.add(j, employees.get(i));
            System.out.println(lowestSalaryStaff.get(j));
            j++;
        }
        return lowestSalaryStaff;
    }

    public boolean countMoreArraySize(int count) {
        if (count > employees.size()) {
            System.out.println("count > size list");
            return true;
        }
        return false;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String toString (){

        String text = "";
        for (Employee employee: employees){
            text = text + employee.getClass() + " " + employee.getMonthSalary() + "\n";
        }
        return text;
    }


}
