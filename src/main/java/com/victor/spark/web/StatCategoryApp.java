/**
 * @author Charles
 * @create 2019/3/14
 * @since 1.0.0
 */

package com.victor.spark.web;

import com.victor.spark.dao.CategoryClickCountDAO;
import com.victor.spark.domain.CategoryClickCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatCategoryApp {
    private static Map<String, String> link = new HashMap<>();

    static {
        link.put("dance", "舞蹈区");
        link.put("digital", "数码区");
        link.put("game", "游戏区");
        link.put("kichiku", "鬼畜区");
        link.put("music", "音乐区");
        link.put("technology", "技术区");
    }


//    @Autowired
    CategoryClickCountDAO categoryClickCountDAO = new CategoryClickCountDAO();

    @RequestMapping(value = "/dynamic_clickcount", method = RequestMethod.POST)
    public List<CategoryClickCount> query() throws IOException {
        List<CategoryClickCount> result = categoryClickCountDAO.query("20190114");
        List<CategoryClickCount> CNresult = new ArrayList<>();
//        for (CategoryClickCount categoryClickCount : result) {
//            System.out.println(categoryClickCount.getName());
//        }
        for (CategoryClickCount c : result) {
            String key = link.get(c.getName().substring(9));
            c.setName(key);
        }
        return result;
    }

    @RequestMapping(value = "/echarts", method = RequestMethod.GET)
    public ModelAndView echarts() {
//        System.out.println("11111");
        return new ModelAndView("categoryecharts");
    }

//    public static void main(String[] args) throws IOException {
//        StatCategoryApp app = new StatCategoryApp();
//        List<CategoryClickCount> categoryClickCounts = app.query();
//        for (CategoryClickCount c : categoryClickCounts) {
//            System.out.println(c.getName()+"-->"+c.getClickcount());
//        }
//
//    }
}
