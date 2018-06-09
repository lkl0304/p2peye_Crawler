package cn.lkangle.core;

import cn.lkangle.entity.ResponseData;
import cn.lkangle.utils.Const;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: Soft
 * @Date: 2018/6/9 21:50
 * @Desc: 请求数据
 */
public class Request {
    private static final Logger log = Logger.getLogger("Request: ");

    // 按天读取数据
    private static String getJsonData(String url) {
        Connection connect = Jsoup.connect(url);
        connect.headers(Const.headers);

        try {
            return connect.get().body().text();
        } catch (IOException e) {
            log.warning("获取数据异常--> " + e.getMessage());
            return null;
        }
    }

    // 获取封装好的响应数据
    public static List<Object> getJsonObjects(String baseUrl, String type) {
        String jsonData = getJsonData(baseUrl + "?flag=1&type=" + type);
        try {
            ResponseData data = JSONObject.parseObject(jsonData, new TypeReference<ResponseData>() {});
            if (data != null) {
                return data.getData().getData();
            }
        } catch (Exception e) {
            log.warning("数据解析异常--> " + e.getMessage());
        }
        return null;
    }

    /**
     * 获取url列表
     * 0-->平台名 1--> url
     */
    public static List<String[]> getNameAndUrls() {
        String url = "https://www.p2peye.com/shuju/ptsj_month/";
        Connection connect = Jsoup.connect(url);
        connect.header("Cookie", Const.cookie);
        connect.header("User-Agent", Const.UserAgent);
        connect.header("Referer", "https://www.p2peye.com/shuju/ptsj/");
        try {
            Document document = connect.get();
            Elements as = document.select("#platdata tr.bd > td.name > a");

            List<String[]> urls = new LinkedList<>();
            as.forEach((elem -> {
                String name = elem.text();
                String href = elem.attr("href");
                if (href != null && !"".equals(href) && !href.matches("(.*)html")) {
                    urls.add(new String[]{name, "https:" + href});
                }
            }));

            return urls;
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("获取url列表异常--> " + e.getMessage());
        }
        return null;
    }
}
