package com.huiztech.muyq.leaf.rpc.repository;

import com.huiztech.muyq.leaf.rpc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/10 23:27]
 * @description []
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
