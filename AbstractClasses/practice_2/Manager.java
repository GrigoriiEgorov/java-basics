
public class Manager
        implements Employee {
    public static final double BONUS_MANAGER = 5;//%
    private double fixSalary;
    private double monthIncome;//amount money that make a manager for company

    public Manager(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary + monthIncome * BONUS_MANAGER / 100;
    }

    public double getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(double monthIncome) {
        this.monthIncome = monthIncome;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + getMonthSalary() + " RUB";
    }
}
