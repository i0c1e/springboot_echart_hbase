/**
 * @author Charles
 * @create 2019/3/14
 * @since 1.0.0
 */

package com.victor.spark.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello springboot";
    }

    @RequestMapping(value = "/echart", method = RequestMethod.GET)
    public String echart() {
        return "hello springboot";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public ModelAndView first() {
        return new ModelAndView("test");
    }

}
