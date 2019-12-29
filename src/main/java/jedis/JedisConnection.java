package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisConnection {
    //address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;

    //the jedis connection pool..
    private static JedisPool pool = null;

    public static void main(String[] args){
        pool = new JedisPool(redisHost, redisPort);
        {
            String key = "members";
            String member1 = "Sedarius";
            String member2 = "Richard";
            String member3 = "Joe";

            //get a jedis connection jedis connection pool
            Jedis jedis = pool.getResource();
            try {
                //save to redis
                jedis.sadd(key, member1, member2, member3);

                //after saving the data, lets retrieve them to be sure that it has really added in redis
                Set<String> members = jedis.smembers(key);
                for (String member : members) {
                    System.out.println(member);
                }
            } catch (JedisException e) {
                //if something wrong happen, return it back to the pool
                if (null != jedis) {
                    pool.returnBrokenResource(jedis);
                    jedis = null;
                }
            } finally {
                ///it's important to return the Jedis instance to the pool once you've finished using it
                if (null != jedis)
                    pool.returnResource(jedis);
            }
        }
        System.out.println("--------------------------------");
        {
            //add some values in Redis HASH
            String key = "javapointers";
            Map<String, String> map = new HashMap<>();
            map.put("name", "Java Pointers");
            map.put("domain", "www.javapointers.com");
            map.put("description", "Learn how to program in Java");

            Jedis jedis = pool.getResource();
            try {
                //save to redis
                jedis.hmset(key, map);

                //after saving the data, lets retrieve them to be sure that it has really added in redis
                Map<String, String> retrieveMap = jedis.hgetAll(key);
                for (String keyMap : retrieveMap.keySet()) {
                    System.out.println(keyMap + " " + retrieveMap.get(keyMap));
                }

            } catch (JedisException e) {
                //if something wrong happen, return it back to the pool
                if (null != jedis) {
                    pool.returnBrokenResource(jedis);
                    jedis = null;
                }
            } finally {
                ///it's important to return the Jedis instance to the pool once you've finished using it
                if (null != jedis)
                    pool.returnResource(jedis);
            }
        }
    }
}
