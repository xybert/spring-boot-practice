package com.xybert.springbootjpa.service.impl;

import com.google.common.collect.Lists;
import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;
import com.xybert.springbootjpa.repository.UserRepository;
import com.xybert.springbootjpa.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xybert
 * @description UserServiceImpl
 * @date 2022/12/29 11:05
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> listAll(UserRequest userRequest) {
        Pageable pageable = PageRequest.of(userRequest.getPageNum(), userRequest.getPageSize());
        Page<User> userPage = userRepository.findAll(getSpecification(userRequest), pageable);
        return userPage.getContent();
    }

    @Override
    public User getOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void addOne(UserDto userDto) {
        userRepository.save(setUser(userDto));
    }

    @Override
    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateOne(UserDto userDto) {
        userRepository.save(setUser(userDto));
    }

    /**
     * 获取查询条件
     *
     * @param userRequest 请求参数
     * @return Specification<User>
     */
    private Specification<User> getSpecification(UserRequest userRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (Objects.nonNull(userRequest.getSex())) {
                predicates.add(criteriaBuilder.equal(root.get("sex"), userRequest.getSex()));
            }
            if (Objects.nonNull(userRequest.getAge())) {
                predicates.add(criteriaBuilder.equal(root.get("age"), userRequest.getAge()));
            }
            if (Objects.nonNull(userRequest.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("status"), userRequest.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * 设置用户信息
     *
     * @param userDto userDto
     * @return User
     */
    private User setUser(UserDto userDto) {
        if (Objects.isNull(userRepository.findByAccount(Objects.requireNonNull(userDto.getAccount())))) {
            throw new RuntimeException();
        }
        User user = new User().setAccount(userDto.getAccount());
        if (Objects.nonNull(userDto.getSex())) {
            user.setSex(userDto.getSex());
        }
        if (Objects.nonNull(userDto.getAge())) {
            user.setAge(userDto.getAge());
        }
        if (Objects.nonNull(userDto.getTel())) {
            user.setTel(userDto.getTel());
        }
        if (Objects.nonNull(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (Objects.nonNull(userDto.getStatus())) {
            user.setStatus(userDto.getStatus());
        }
        return user;
    }
}
