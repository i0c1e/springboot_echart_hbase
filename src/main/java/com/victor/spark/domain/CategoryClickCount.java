/**
 * @author Charles
 * @create 2019/3/14
 * @since 1.0.0
 */

package com.victor.spark.domain;

import org.springframework.stereotype.Component;

@Component
public class CategoryClickCount {
    private String name;
    private long clickcount;

    public void setName(String name) {
        this.name = name;
    }

    public void setClickcount(long clickcount) {
        this.clickcount = clickcount;
    }

    public String getName() {
        return name;
    }

    public long getClickcount() {
        return clickcount;
    }
}
