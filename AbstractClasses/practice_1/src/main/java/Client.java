public abstract class Client {

    protected double amountMoney;

    public void put(double cash) {
        amountMoney = (cash > 0) ? amountMoney + cash : amountMoney;
    }

    public void take(double cash) {
        amountMoney = (amountMoney > cash) ? amountMoney - cash : amountMoney;
    }

    public double getAmount() {
        return amountMoney;
    }


    public abstract String getName();

    public  abstract String toString();

}
