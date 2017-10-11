package com.ecnu.psf.CustomSerializerKafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.log4j.Logger;

import java.util.Map;

public class CustomProducerSerializer implements Serializer<Transaction>{
    private static final Logger logger = Logger.getLogger(CustomProducerSerializer.class);

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, Transaction transaction) {
        byte[] byteVal = null;
        ObjectMapper objMapper = new ObjectMapper();
        try{
            byteVal = objMapper.writeValueAsBytes(transaction);
        }catch(Exception e){
            logger.error(e);
        }
        return byteVal;
    }

    public void close() {

    }
}
