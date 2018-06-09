package cn.lkangle;

import cn.lkangle.core.DataDeal;
import cn.lkangle.core.Request;
import cn.lkangle.entity.Target;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Soft
 * @Date: 2018/6/9 21:06
 * @Desc: P2P数据爬取
 */
public class P2pCrawler {

    public static void main(String[] args) throws IOException, WriteException, InterruptedException {
        List<String[]> nameAndUrls = Request.getNameAndUrls();
        assert nameAndUrls != null;

        List<Thread> tds = new LinkedList<>();
        for (String[] nus : nameAndUrls) {
            String name = nus[0];
            String url  = nus[1];

            Thread thread = new Thread(new DataDeal(name, url));
            tds.add(thread);

            if (tds.size() >= 10) {
                for (Thread t : tds) {
                    t.start();
                }
                for (Thread t : tds) {
                    t.join();
                }
                tds.clear();
            }
        }
    }
}