package com.xybert.springbooteasyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.xybert.springbooteasyexcel.entity.User;
import com.xybert.springbooteasyexcel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author xybert
 * @description UserListener
 * @date 2022/12/23 17:06
 */

@Slf4j
public class UserListener implements ReadListener<User> {

    /**
     * 每次缓存的数据量
     */
     private final static int BATCH_SIZE = 500;

    /**
     * 数据量
     */
    private long dataNum = 0;

    /**
     * 缓存数据
     */
     private List<User> cacheData = ListUtils.newArrayListWithExpectedSize(BATCH_SIZE);

     private final UserService userService;

    public UserListener(UserService userService){
        this.userService = userService;
    }

    @Override
    public void invoke(User data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cacheData.add(data);
        if (cacheData.size() >= BATCH_SIZE) {
            userService.saveBatch(cacheData);
            dataNum += cacheData.size();
            cacheData = ListUtils.newArrayListWithExpectedSize(BATCH_SIZE);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (CollectionUtils.isNotEmpty(cacheData)) {
            userService.saveBatch(cacheData);
            dataNum += cacheData.size();
        }
        log.info("成功录入所有数据，数据量为：{}", dataNum);
    }
}
