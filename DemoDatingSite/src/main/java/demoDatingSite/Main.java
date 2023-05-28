package demoDatingSite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int AMOUNT_USERS = 20;
    private static final int AMOUNT_RICH_USERS = AMOUNT_USERS / 10;

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        generateUsers(redis);

        while (true) {
            //users, which can donate
            List<Integer> richUser = generateRichUsers(redis);

            Iterable<String> iterableQuery = redis.getScoredSortedSet();
            for (String s : iterableQuery){

                if (richUser.contains(Integer.parseInt(s))){
                    System.out.println("> Пользователь " + s + " оплатил платную услугу");
                }

                System.out.println("- На главной странице показываем пользователя" + s);
            }
            Thread.sleep(2000);
        }
//        redis.shutdown();
    }

    private static void generateUsers(RedisStorage redis) throws InterruptedException {
        for (int id = 1; id < AMOUNT_USERS + 1; id++) {
            redis.registrationUser(id);
            Thread.sleep(5);
        }
    }

    private static List<Integer> generateRichUsers(RedisStorage redis){
        List<Integer> richUser = new ArrayList<>();

        while (richUser.size() < AMOUNT_RICH_USERS) {
            int rich_id = new Random().nextInt(AMOUNT_USERS);
            if(!richUser.contains(rich_id)){
                richUser.add(rich_id);
                redis.increasePositionInQuery(rich_id);
                System.out.println(rich_id);
            }
        }
        return richUser;
    }
}