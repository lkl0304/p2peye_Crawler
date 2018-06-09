package cn.lkangle.core;

import cn.lkangle.entity.Target;
import cn.lkangle.utils.Const;
import cn.lkangle.utils.SaveToExcel;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: Soft
 * @Date: 2018/6/9 22:42
 * @Desc: 数据处理器
 */
public class DataDeal implements Runnable {
    private static final Logger log = Logger.getLogger("DataDeal: ");

    private String firmName;
    private String url;

    public DataDeal(String firmName, String url) {
        this.firmName = firmName;
        this.url      = url;
    }

    @Override
    public void run() {
        List<Target> targets = new LinkedList<>();

        List<List<Object>> allData = getAllData();
        String[] values = Const.typesRefKey.keySet().toArray(new String[Const.typesRefKey.size()]);
        if (allData.size() > 0) {
            int size = allData.get(0).size();
            for (int i = 0; i < size; i++) {
                Target target = new Target();
                
                try { // 避免出现本条数据不存在而导致进程结束的情况 下同
                    JSONObject o1 = (JSONObject) allData.get(0).get(i);
                    target.setNewMoney(o1.get("newmoney"));
                    target.setCollected(o1.get("collected"));
                    target.setDatetime(o1.get("time_sequences"));
                } catch (Exception ignored) { }

                try {
                    JSONObject o2 = (JSONObject) allData.get(1).get(i);
                    target.setMoneyInOut(String.valueOf(o2.get("moneyinout")));
                } catch (Exception ignored) {}

                try {
                    JSONObject o3 = (JSONObject) allData.get(2).get(i);
                    target.setZhRate(o3.get("zh_rate"));
                } catch (Exception ignored) {}

                try {
                    JSONObject o4 = (JSONObject) allData.get(3).get(i);
                    target.setAmount(o4.get("amount"));
                } catch (Exception ignored) {}

                try {
                    JSONObject o5 = (JSONObject) allData.get(4).get(i);
                    target.setCountUser(String.valueOf(o5.get("count_user")));
                } catch (Exception ignored) {}

                try {
                    JSONObject o6 = (JSONObject) allData.get(5).get(i);
                    target.setBorrowCount(o6.get("borrow_count"));
                    target.setInvestCount(o6.get("invest_count"));
                } catch (Exception ignored) {}

                try {
                    JSONObject o7 = (JSONObject) allData.get(6).get(i);
                    target.setNewUserCount(o7.get("newuser_count"));
                } catch (Exception ignored) {}

                try {
                    JSONObject o8 = (JSONObject) allData.get(7).get(i);
                    target.setNewUserMoney(o8.get("newuser_money"));
                } catch (Exception ignored) {}

                target.setName(firmName);
                targets.add(target);
            }
            this.save(targets);
        }
    }

    // 一次性获取所有数据
    private List<List<Object>> getAllData() {
        List<List<Object>> obs = new LinkedList<>();
        Const.typesRefKey.forEach((type, key)->{
            List<Object> objects = Request.getJsonObjects(url, type);
            obs.add(objects);
        });
        return obs;
    }

    // 保存
    private void save(List<Target> targets) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SaveToExcel.export(targets);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
