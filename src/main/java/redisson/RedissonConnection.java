package redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedissonConnection {
    public static void main(String[] args) {
        Config config = new Config().setTransportMode(TransportMode.NIO);
        config.useSingleServer()
                .setAddress("redis://localhost:6379/1");

        RedissonClient client = Redisson.create(config);

        RBucket<CustomData> bucket = client.getBucket("customer");
//        bucket.set(new CustomData(3,"morteza", new CustomDetail(7,9)));
        CustomData customer = bucket.get();
        System.out.println(customer.getName());
        client.shutdown();
    }
}
