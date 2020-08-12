package com.huiztech.leaf.common.service;


import com.huiztech.leaf.common.result.RpcResponse;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/8 11:46]
 * @description []
 */
public interface GenerateUuidService {
    /**
     * 通过key获取号段模式的分布式id
     *
     * @param key 关键字
     * @return Result
     */
    RpcResponse getSegmentId(String key);


    /**
     * 通过key获取snowflake模式的分布式id
     *
     * @param key 关键字
     * @return Result
     */
    RpcResponse getSnowflakeId(String key);

    /**
     * 通过snowflakeId反向解析
     *
     * @param snowflakeId 雪花算法生成id
     * @return rpcResponse
     */
    RpcResponse decodeSnowflakeId(long snowflakeId);
}
