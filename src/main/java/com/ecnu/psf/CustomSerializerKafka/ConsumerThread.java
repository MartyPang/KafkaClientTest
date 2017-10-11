package com.ecnu.psf.CustomSerializerKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerThread implements Runnable{

    public static final Logger logger = Logger.getLogger(ConsumerThread.class);
    private final KafkaConsumer<String, Transaction> consumer;
    private final String topic;

    public ConsumerThread(String brokers, String groupId, String topic) {
        Properties prop = createConsumerConfig(brokers, groupId);
        this.consumer = new KafkaConsumer(prop);
        this.topic = topic;
        this.consumer.subscribe(Arrays.asList(this.topic));
    }

    private static Properties createConsumerConfig(String brokers, String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.ecnu.psf.CustomSerializerKafka.CustomConsumerDeserializer");
        return props;
    }
    public void run() {
        while (true) {
            ConsumerRecords<String, Transaction> records = consumer.poll(100);
            for (final ConsumerRecord<String, Transaction> record : records) {
                //logger.info("Receive: " + record.value().toString());
                System.out.println("Receive: " + record.value().toString());
            }
        }
    }
}
