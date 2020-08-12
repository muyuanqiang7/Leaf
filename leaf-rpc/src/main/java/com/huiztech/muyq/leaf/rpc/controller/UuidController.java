package com.huiztech.muyq.leaf.rpc.controller;

import com.huiztech.leaf.common.result.RpcResponse;
import com.huiztech.leaf.common.service.GenerateUuidService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/9 12:18]
 * @description []
 */
@RestController
@RequestMapping(value = "uuid")
public class UuidController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UuidController.class);
    @DubboReference(version = "1.0.0",tag = "tag2")
    private GenerateUuidService generateUuidService;

    @GetMapping()
    public RpcResponse getUuid() {
        LOGGER.info("dubbo rpc get snowflakeId");
        StopWatch sw = new Slf4JStopWatch("dubboRpcUUid");
        RpcResponse response = generateUuidService.getSnowflakeId("dubbo");
        sw.stop();
        return response;
    }

    @GetMapping(value = "/decode")
    public RpcResponse decodeUuid(@RequestParam Long snowflakeId) {
        return generateUuidService.decodeSnowflakeId(snowflakeId);
    }
}
