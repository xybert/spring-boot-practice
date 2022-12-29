package com.xybert.springbootjpa.repository;

import com.xybert.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xybert
 * @description UserRepository
 * @date 2022/12/29 10:55
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
