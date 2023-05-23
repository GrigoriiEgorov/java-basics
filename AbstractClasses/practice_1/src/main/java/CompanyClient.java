public class CompanyClient extends Client {

    public static final double COMMISSION = 1;


    @Override
    public void take(double cash) {
        super.take(cash * (1 + COMMISSION / 100));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "Commission put: 0 RUB; \n" +
                "Commission take: " + COMMISSION + " %;\n" +
                "Balance: " + amountMoney;

    }
}
