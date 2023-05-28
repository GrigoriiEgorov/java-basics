package demoDatingSite;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.List;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> users;
    private static final String KEY = "DATING_USERS";
    private static final String REDIS_ADDRESS = "redis://127.0.0.1:6379";



    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_ADDRESS);
        try{
            redisson = Redisson.create(config);
        } catch (RedisConnectionException e){
            out.println("Не удалось подключиться к Redis");
            out.println(e.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    public void registrationUser(int id){
        users.add(System.currentTimeMillis(), String.valueOf(id));
    }

    public void shutdown(){
        redisson.shutdown();
    }

    public Iterable<String> getScoredSortedSet(){
        return redisson.getScoredSortedSet(KEY);
    }

    public void increasePositionInQuery (int id){
        users.addScore(String.valueOf(id),-200);
    }



}
