package lettuce;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.async.RedisListAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;

import java.util.StringTokenizer;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LettuceConnection {
    public static void main(String[] args) throws  Exception{
        RedisClient redisClient = new RedisClient(
                RedisURI.create("redis://localhost/1"));
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");

        RedisCommands<String, String> commands =connection.sync();
        commands.set("mor0002", "bar0003");
        String value = commands.get("mor0001");
        System.out.println(value);
        System.out.println("---------- list ----");
        System.out.println(commands.mget(new String[]{"mor0001", "mor0002"}));
//        connection.close();
//        redisClient.shutdown();

        System.out.println("-------------------");
        RedisAsyncCommands commands1=connection.async();
        RedisFuture future=commands1.get("mor0001");
//        future.thenAccept(new Consumer<String>() {
//            @Override
//            public void accept(String o) {
//                System.out.println(o);
//            }
//        });
        future.thenAcceptAsync(System.out::println);


        RedisListAsyncCommands<String, String> listAsyncCommands=connection.async();
        listAsyncCommands.lpush("events","key01:asd0");
        listAsyncCommands.lpush("events","key02:asd1");
        listAsyncCommands.lpush("events","key03:asd2");


    }
}
