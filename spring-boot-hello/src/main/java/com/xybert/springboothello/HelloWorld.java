package com.xybert.springboothello;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 输出 hello，world
 * @author xybert
 * @date 2022/10/17 16:33
 */

@RestController
@RequestMapping("")
public class HelloWorld {

    private final static String HELLO = "Hello";
    private final static String DEFAULT_OBJECT = "world";

    @GetMapping("/world")
    public String helloWorld(@RequestParam(name = "object", required = false) String object) {
        if (StringUtils.isBlank(object)) {
            object = DEFAULT_OBJECT;
        }
        return String.format("%s, %s", HELLO, object);
    }
}
