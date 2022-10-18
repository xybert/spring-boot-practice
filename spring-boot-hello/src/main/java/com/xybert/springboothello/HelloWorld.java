package com.xybert.springboothello;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xybert
 * @date 2022/10/17 16:33
 */

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    private final static String HELLO = "Hello";

    @GetMapping("")
    public String helloWorld(@RequestParam(name = "object", required = false) String object) {
        if (StringUtils.isBlank(object)) {
            object = "world";
        }
        return String.format("%s, %s", HELLO, object);
    }
}
