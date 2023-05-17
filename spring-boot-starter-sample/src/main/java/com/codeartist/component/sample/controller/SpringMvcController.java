package com.codeartist.component.sample.controller;

import com.codeartist.component.core.entity.Base64File;
import com.codeartist.component.sample.entity.param.UserParam;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * MVC配置
 *
 * @author 艾江南
 * @date 2023/2/21
 */
@RestController
@RequestMapping("/api/mvc")
public class SpringMvcController {

    @PostMapping("/base64")
    public String base64File(@RequestParam("file") Base64File file) throws IOException {
        file.transferTo(new File("a.txt"));
        return "OK";
    }

    @PostMapping("/json")
    public UserParam json(@RequestBody UserParam param) {
        return param;
    }
}
