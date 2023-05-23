public class IndividualEntrepreneurClient extends Client {

    public static final double LINE_COMMISSION = 1000.0;
    public static final double COMMISSION_MORE_LINE = 0.5;
    public static final double COMMISSION_LESS_LINE = 1;
    public static final double EPS = 1e-7;


    @Override
    public void put(double cash) {
        cash = (cash < LINE_COMMISSION) ?  //(cash - LINE_COMMISSION) > EPS)
                cash * (1 - COMMISSION_LESS_LINE / 100):
                cash * (1 - COMMISSION_MORE_LINE / 100) ;
        super.put(cash);
    }

    @Override
    public String getName() {
        return "Individual Entrepreneur Client ";
    }

    @Override
    public String toString() {
        return "Commission put more then 1000 RUB: " + COMMISSION_MORE_LINE + " %; \n" +
                "Commission put less then 1000 RUB: " + COMMISSION_LESS_LINE + " %; \n" +
                "Commission take: 0 RUB;\n" +
                "Balance: " + amountMoney;

    }


}

