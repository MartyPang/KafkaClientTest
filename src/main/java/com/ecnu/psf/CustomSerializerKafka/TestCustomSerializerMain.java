package com.ecnu.psf.CustomSerializerKafka;

public class TestCustomSerializerMain {
    public static void main(String[] args){
        String brokers = "192.168.114.128:9092";
        String topic = "TransactionTopic";
        String groupId = "g1";

        ProducerThread pt = new ProducerThread(brokers,topic);
        Thread t1 = new Thread(pt);
        t1.start();

        ConsumerThread ct = new ConsumerThread(brokers,groupId,topic);
        Thread t2 = new Thread(ct);
        t2.start();
    }
}
