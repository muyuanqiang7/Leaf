package com.sankuai.inf.leaf.server.rpc.impl;

import com.huiztech.leaf.common.result.RpcResponse;
import com.huiztech.leaf.common.service.GenerateUuidService;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.server.service.SegmentService;
import com.sankuai.inf.leaf.server.service.SnowflakeService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/8 11:53]
 * @description []
 */
@DubboService(version = "1.0.0")
public class GenerateUuidServiceImpl implements GenerateUuidService {
    @Resource
    private SegmentService segmentService;
    @Resource
    private SnowflakeService snowflakeService;

    /**
     * 通过key获取号段模式的分布式id
     *
     * @param key 关键字
     * @return Result
     */
    @Override
    public RpcResponse getSegmentId(String key) {
        return processResult(segmentService.getId(key));
    }

    /**
     * 通过key获取snowflake模式的分布式id
     *
     * @param key 关键字
     * @return Result
     */
    @Override
    public RpcResponse getSnowflakeId(String key) {
        return processResult(snowflakeService.getId(key));
    }

    @Override
    public RpcResponse decodeSnowflakeId(long snowflakeId) {
        Map<String, String> map = new HashMap<>();
        long originTimestamp = (snowflakeId >> 22) + 1288834974657L;
        Date date = new Date(originTimestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        map.put("timestamp", originTimestamp + "(" + sdf.format(date) + ")");

        long workerId = (snowflakeId >> 12) ^ (snowflakeId >> 22 << 10);
        map.put("workerId", String.valueOf(workerId));

        long sequence = snowflakeId ^ (snowflakeId >> 12 << 12);
        map.put("sequenceId", String.valueOf(sequence));
        return RpcResponse.getSuccessResponse(map);
    }

    private RpcResponse processResult(Result result) {
        if (result.getStatus().equals(Status.EXCEPTION)) {
            return RpcResponse.getFailureResponse(result.toString());
        }
        return RpcResponse.getSuccessResponse(result.getId());
    }
}
