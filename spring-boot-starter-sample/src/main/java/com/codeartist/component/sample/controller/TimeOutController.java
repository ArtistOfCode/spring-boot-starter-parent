package com.codeartist.component.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 超时接口
 *
 * @author 艾江南
 * @date 2021/5/25
 */
@RestController
@RequestMapping("/api/timeout")
public class TimeOutController {

    @GetMapping
    public String timeout(Integer time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
