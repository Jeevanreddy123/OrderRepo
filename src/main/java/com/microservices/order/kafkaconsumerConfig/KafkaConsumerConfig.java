package com.microservices.order.kafkaconsumerConfig;

import com.microservices.demo.POJO.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


public class KafkaConsumerConfig {

    @Bean
    public Map<String,Object> consumerConfig(){
        JsonDeserializer deserializer = new JsonDeserializer();
        //deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        //deserializer.setUseTypeMapperForKey(true);
        Map<String,Object> props=new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new JsonDeserializer<>());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"my_group");
        return props;
    }

    @Bean()
    public ConsumerFactory<String, User> consumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer<User>();
        //deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        //deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,User> concurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,User> concurrentKafkaListenerContainerFactory=new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }





}
