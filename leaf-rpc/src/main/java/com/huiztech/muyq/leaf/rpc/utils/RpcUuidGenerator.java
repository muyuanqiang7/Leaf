package com.huiztech.muyq.leaf.rpc.utils;

import com.huiztech.leaf.common.annotation.UuidKeyAnnotation;
import com.huiztech.leaf.common.result.RpcResponse;
import com.huiztech.leaf.common.service.GenerateUuidService;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/10 07:40]
 * @description []
 */
public class RpcUuidGenerator implements IdentifierGenerator {
    private static final String DEFAULT_KEY = "default";
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcUuidGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        LOGGER.info("rpc call uuid");
        StopWatch sw = new Slf4JStopWatch("dubboRpcUUid");
        GenerateUuidService generateUuidService = ApplicationContextHolder.getBean(GenerateUuidService.class);
        UuidKeyAnnotation annotation = o.getClass().getAnnotation(UuidKeyAnnotation.class);
        String key = DEFAULT_KEY;
        if (annotation != null) {
            key = annotation.value();
        }
        RpcResponse response = generateUuidService.getSnowflakeId(key);
        LOGGER.info("key is {}", key);
        if (response.isSuccess()) {
            sw.stop();
            return (long) response.getData();
        }
        return null;
    }

}
