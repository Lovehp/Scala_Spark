package com.hp.spark.kafka;

/**
 * @program: 00_Spark
 * @description: Kafka API 测试类
 * @author: hupeng
 * @create: 2018-08-13 17:25
 **/
public class KafkaClientApp {
    public static void main(String[] args) {
        new KafkaProducer(KafkaProperties.TOPIC).start();
        new KafkaConsumer(KafkaProperties.TOPIC).start();
    }
}
