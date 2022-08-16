package com.yzh.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * 杰克逊对象映射器
 *
 * @author 杨振华
 * @date 2022/08/15
 */
public class JacksonObjectMapper extends ObjectMapper {

    /**
     * 定义格式化常量
     */
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public JacksonObjectMapper(){
        super();
        // 默认true 收到未知属性时会报错，改为false
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // 反序列化的时候，对未知属性进行兼容处理
        this.getDeserializationConfig().withFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        /**
         * 进行反序列化
         *      ||
         * 列取日期的格式化 DEFAULT_FORMAT
         * 类型转换(这里的是Long --> String)
         */
        this.registerModule(new SimpleModule()
                .addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_FORMAT)))
                .addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_FORMAT)))
                .addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_FORMAT)))
                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                .addSerializer(Long.class,ToStringSerializer.instance)
                .addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_FORMAT))))

        ;
    }
}
