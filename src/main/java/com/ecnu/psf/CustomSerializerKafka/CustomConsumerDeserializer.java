package com.ecnu.psf.CustomSerializerKafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.log4j.Logger;

import java.util.Map;

public class CustomConsumerDeserializer implements Deserializer<Transaction>{
    private static final Logger logger = Logger.getLogger(CustomConsumerDeserializer.class);

    public void configure(Map<String, ?> map, boolean b) {

    }

    public Transaction deserialize(String s, byte[] bytes) {
        ObjectMapper objMapper = new ObjectMapper();
        Transaction t = null;
        try{
            t = objMapper.readValue(bytes,Transaction.class);
        }catch (Exception e){
            logger.error(e);
        }
        return t;
    }

    public void close() {

    }
}
