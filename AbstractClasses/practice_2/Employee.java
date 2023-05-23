
public interface Employee extends Comparable {

    double getMonthSalary();

    @Override
    default int compareTo(Object o) {
        Employee employee = (Employee) o;
        return Integer.compare((int) employee.getMonthSalary(), (int)getMonthSalary());
    }


}
