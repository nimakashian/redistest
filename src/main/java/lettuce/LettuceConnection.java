package lettuce;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class LettuceConnection {
    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient(
                RedisURI.create("redis://localhost"));
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");

        RedisCommands<String, String> commands =connection.sync();
       // commands.set("foo", "bar");
        String value = commands.get("foo");
        System.out.println(value);
        connection.close();
        redisClient.shutdown();
    }
}
