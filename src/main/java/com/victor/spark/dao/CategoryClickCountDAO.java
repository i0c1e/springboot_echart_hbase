/**
 * @author Charles
 * @create 2019/3/14
 * @since 1.0.0
 */

package com.victor.spark.dao;

import com.victor.spark.domain.CategoryClickCount;
import com.victor.spark.domain.utils.HBaseUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Bean="categoryClickCountDAO")
public class CategoryClickCountDAO {
    public List<CategoryClickCount> query(String day) throws IOException {
        List<CategoryClickCount> list = new ArrayList<CategoryClickCount>();
        String tableName = "category_clickcount";

        Map<String, Long> values = HBaseUtils.getInstance().query(tableName, day);
        for (Map.Entry<String, Long> entry : values.entrySet()) {
            CategoryClickCount categoryClickCount = new CategoryClickCount();
            categoryClickCount.setName(entry.getKey());
            categoryClickCount.setClickcount(entry.getValue());
            list.add(categoryClickCount);
        }
        return list;
    }

//    public static void main(String[] args) throws IOException {
//        CategoryClickCountDAO categoryClickCountDAO = new CategoryClickCountDAO();
//        List<CategoryClickCount> result = categoryClickCountDAO.query("20190114");
//        for (CategoryClickCount categoryClickCount : result) {
//            System.out.print(categoryClickCount.getName() + " --> ");
//            System.out.println(categoryClickCount.getClickcount());
//        }
//    }
}
