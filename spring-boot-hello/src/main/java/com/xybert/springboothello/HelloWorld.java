package com.xybert.springboothello;

import com.xybert.springbootexception.result.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xybert
 * @description 输出 hello，world
 * @date 2022/10/17 16:33
 */

@RestController
@RequestMapping("")
public class HelloWorld {

    private final static String HELLO = "Hello";

    @GetMapping("/world")
    public BaseResult helloWorld(@RequestParam(name = "object", required = false, defaultValue = "world") String object) {
        return BaseResult.success((Object) String.format("%s, %s", HELLO, object));
    }
}
