public class PhysicalPersonClient extends Client {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "Commission put: 0 RUB; \n" +
                "Commission take: 0 RUB; \n" +
                "Balance: " + amountMoney;
    }

}
