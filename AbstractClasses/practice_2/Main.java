
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Company company = new Company("company");
        Random random = new Random();
        int amountOperator = 180;
        int amountManager = 80;
        int amountTopManager = 10;
        int allEmployee = amountManager + amountTopManager + amountOperator;


        for (int i = 0; i < amountOperator; i++) {
            company.hire(new Operator(random.nextInt(20_000, 50_000)));
        }

        for (int i = 0; i < amountManager; i++) {
            Manager manager = new Manager(random.nextInt(60_000, 90_000));
            manager.setMonthIncome(random.nextInt(115_000, 140_000));
            company.addIncome(manager.getMonthIncome());
            company.hire(manager);
        }

        for (int i = 0; i < amountTopManager; i++) {
            TopManager topManager = new TopManager(random.nextInt(100_000, 130_000));
            topManager.setAvailabilityBonus(company.getIncome() > 10_000_000);
            company.hire(topManager);
        }

        System.out.println("The top salary: ");
        company.getTopSalaryStaff(15);
        System.out.println("\nThe lowest salary: ");
        company.getLowestSalaryStaff(30);

        for (int i = 0; i < allEmployee * 0.5; i++) {
            company.fire(company.getEmployees().get(random.nextInt(company.getEmployees().size())));
        }

        System.out.println("The top salary: ");
        company.getTopSalaryStaff(15);
        System.out.println("\nThe lowest salary: ");
        company.getLowestSalaryStaff(30);



    }
}
