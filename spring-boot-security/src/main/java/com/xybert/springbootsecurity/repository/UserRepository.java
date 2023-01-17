package com.xybert.springbootsecurity.repository;

import com.xybert.springbootsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xybert
 * @description UserRepository
 * @date 2023/01/16 17:46
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询用户
     *
     * @param account 用户名
     * @return User
     */
    User findByAccount(String account);
}
