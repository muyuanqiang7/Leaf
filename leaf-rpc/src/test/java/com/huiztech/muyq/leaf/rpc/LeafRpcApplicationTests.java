package com.huiztech.muyq.leaf.rpc;

import com.huiztech.leaf.common.result.RpcResponse;
import com.huiztech.leaf.common.service.GenerateUuidService;
import com.huiztech.muyq.leaf.rpc.domain.User;
import com.huiztech.muyq.leaf.rpc.repository.UserRepository;
import org.apache.dubbo.config.annotation.DubboReference;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LeafRpcApplicationTests {
    @DubboReference(version = "1.0.0", tag = "tag2")
    private GenerateUuidService generateUuidService;
    @Resource
    private UserRepository userRepository;


    @Test
    public void userId() {
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setName("Bob Chris");
            users.add(user);
        }

        userRepository.saveAll(users);

    }


    @Test
    void contextLoads() {
        RpcResponse decodeResponse = generateUuidService.decodeSnowflakeId(1292852921951596549L);
        if (decodeResponse.isSuccess()) {
            System.out.println(decodeResponse.getData());
        }
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            RpcResponse response = generateUuidService.getSnowflakeId("test");
//            if (response.isSuccess()) {
//                System.out.println(response.getData());
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

}
