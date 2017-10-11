package com.ecnu.psf.CustomSerializerKafka;

import com.ecnu.psf.utils.DateUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import java.util.Properties;

public class ProducerThread implements Runnable{

    public static final Logger logger = Logger.getLogger(ProducerThread.class);
    private final KafkaProducer<String, Transaction> producer;
    private final String topic;

    public ProducerThread(String brokers, String topic) {
        Properties prop = this.createProducerConfig(brokers);
        this.producer = new KafkaProducer<String, Transaction>(prop);
        this.topic = topic;
    }

    private static Properties createProducerConfig(String brokers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //set value.serializer to our custom serializer
        props.put("value.serializer", "com.ecnu.psf.CustomSerializerKafka.CustomProducerSerializer");

        return props;
    }

    public void run() {
        Long index = 0L;
        while(true) {
            final Transaction tx = new Transaction(index, "this is tx_"+index, DateUtils.getCurrentDateTime());
            producer.send(new ProducerRecord<String, Transaction>(topic, tx.getTx_id().toString(), tx), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        //logger.error(e);
                        e.printStackTrace();
                    }
                    //logger.info("Sent:" + tx.toString());
                    System.out.println("Sent:" + tx.toString());
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //logger.error(e);
                e.printStackTrace();
            }
            index++;
        }
    }
}
