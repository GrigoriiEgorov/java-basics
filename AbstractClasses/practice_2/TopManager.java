public class TopManager
        implements Employee{

    public static final double BONUS_TOP_MANAGER = 150;//%
    private double fixSalary;
    private boolean availabilityBonus;

    public TopManager(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    public void setAvailabilityBonus(boolean availabilityBonus){
        this.availabilityBonus = availabilityBonus;
    }

    @Override
    public double getMonthSalary() {
        if (availabilityBonus){
            return fixSalary + fixSalary * BONUS_TOP_MANAGER / 100;
        }
        return fixSalary;
    }

    public double getFixSalary() {
        return fixSalary;
    }

    public void setFixSalary(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + getMonthSalary() + " RUB";
    }


}
