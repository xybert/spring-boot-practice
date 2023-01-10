package com.xybert.springbootjpa.service.impl;

import com.google.common.collect.Lists;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;
import com.xybert.springbootjpa.enums.ExceptionEnum;
import com.xybert.springbootjpa.repository.UserRepository;
import com.xybert.springbootjpa.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Date;
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
    public Pair<Long, List<User>> listAll(UserRequest userRequest) {
        Sort sort = Sort.by("createTime").descending();
        if (StringUtils.isNotBlank(userRequest.getSortField())) {
            sort = Objects.equals(userRequest.getSortType(), 1) ? Sort.by(userRequest.getSortField()).descending()
                    : Sort.by(userRequest.getSortField()).ascending();
        }
        Pageable pageable = PageRequest.of(userRequest.getPageNo() - 1, userRequest.getPageSize(), sort);
        Page<User> users = userRepository.findAll(getSpecification(userRequest), pageable);
        return new ImmutablePair<>(users.getTotalElements(), users.getContent());
    }

    @Override
    public User getOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public BaseResult addOne(UserDto userDto) {
        if (Objects.nonNull(userRepository.findByAccount(Objects.requireNonNull(userDto.getAccount())))) {
            throw new BaseException(ExceptionEnum.USER_ALREADY_EXIST);
        }
        return BaseResult.success(userRepository.save(setUser(userDto)));
    }

    @Override
    public BaseResult deleteOne(Long id) {
        userRepository.deleteById(id);
        return BaseResult.success();
    }

    @Override
    public BaseResult deleteBatch(List<Long> ids) {
        userRepository.deleteAllById(ids);
        return BaseResult.success();
    }

    @Override
    public BaseResult updateOne(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (!optionalUser.isPresent()) {
            throw new BaseException((ExceptionEnum.USER_NOT_EXIST));
        }
        if (Objects.equals(optionalUser.get().getAccount(), userDto.getAccount())) {
            throw new BaseException((ExceptionEnum.ACCOUNT_DUPLICATE));
        }
        return BaseResult.success(userRepository.save(setUser(userDto)));
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
            if (Objects.nonNull(userRequest.getAccount())) {
                // 用户名模糊匹配
                predicates.add(criteriaBuilder.like(root.get("account"), userRequest.getAccount()));
            }
            if (Objects.nonNull(userRequest.getSex())) {
                // 性别精确匹配
                predicates.add(criteriaBuilder.equal(root.get("sex"), userRequest.getSex()));
            }
            if (Objects.nonNull(userRequest.getEndAge())) {
                Integer beginAge = Objects.isNull(userRequest.getBeginAge()) ? 0 : userRequest.getBeginAge();
                // 年龄范围匹配
                predicates.add(criteriaBuilder.between(root.get("age"), beginAge, userRequest.getBeginAge()));
            }
            if (Objects.nonNull(userRequest.getStatus())) {
                // 状态精确匹配
                predicates.add(criteriaBuilder.equal(root.get("status"), userRequest.getStatus()));
            }
            if (Objects.nonNull(userRequest.getBeginTime()) || Objects.nonNull(userRequest.getEndTime())) {
                Date beginTime = Objects.isNull(userRequest.getBeginTime()) ? new Date() : userRequest.getBeginTime();
                Date endTime = Objects.isNull(userRequest.getEndTime()) ? new Date() : userRequest.getEndTime();
                if (!Objects.equals(beginTime, endTime)) {
                    // 创建时间范围
                    predicates.add(criteriaBuilder.between(root.get("createTime"), beginTime, endTime));
                }
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
