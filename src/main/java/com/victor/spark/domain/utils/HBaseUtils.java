/**
 * @author Charles
 * @create 2019/3/14
 * @since 1.0.0
 */

package com.victor.spark.domain.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.tomcat.jni.Mmap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseUtils {
    HBaseAdmin admin = null;
    Configuration conf = null;

    private HBaseUtils() {
        conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "hadoop131,hadoop132,hadoop133");
        conf.set("hbase.rootdir", "hdfs://hadoop131:9000/hbase");
        try {
            admin = new HBaseAdmin(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HBaseUtils instance;

    public static synchronized HBaseUtils getInstance() {
        if (instance == null) {
            instance = new HBaseUtils();
            return instance;
        }
        return instance;
    }

    public HTable getTable(String tableName) {
        HTable table = null;
        try {
            table = new HTable(conf, tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    public void put(String tableName, String rowkey,
                    String family, String column, String value) {
        HTable table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Long> query(String tableName, String prefix) throws IOException {
        String family = "info";
        String qualifier = "clickcount";

        HashMap<String, Long> map = new HashMap<>();

        HTable table = HBaseUtils.getInstance().getTable(tableName);
        Filter filter = new PrefixFilter(Bytes.toBytes(prefix));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner rs = table.getScanner(scan);
        for (Result r : rs) {
            byte[] value = r.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
            byte[] row = r.getRow();
            map.put(Bytes.toString(row), Bytes.toLong(value));
        }

        return map;
    }


    public static void main(String[] args) throws IOException {
//        HBaseUtils instance = HBaseUtils.getInstance();
//        instance.put("category_clickcount", "20190313_1",
//                "info", "click", "100");

        Map<String, Long> map = HBaseUtils.getInstance().query("category_clickcount", "20190114-dance");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


    }

}