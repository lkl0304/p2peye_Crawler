package cn.lkangle.utils;

import cn.lkangle.entity.Target;
import jxl.Workbook;
import jxl.write.*;
import jxl.format.VerticalAlignment;
import jxl.format.Colour;
import jxl.format.Alignment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: Soft
 * @Date: 2018/6/9 23:36
 * @Desc: 将数据保存到Excel表格
 */
public class SaveToExcel {
    private static final Logger log = Logger.getLogger("SaveToExcel: ");

    public static void export(List<Target> targets) throws IOException, WriteException {
        if (targets != null && targets.size() > 0) {
            String firmName = (String) targets.get(0).getName();
            String filename = firmName + "_data.xls";
            File file = new File("D:/p2peye/" + filename);

            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet(firmName + "数据", 0);

            String[] titles = {"序号", "平台名称", "新增投资", "代收本金", "资金净流入", "平台平均综合利率", "累计偿还本金",
                    "在线投资人数", "借款人数量", "投资人数量", "新增投资人数", "新增投资人投资总额", "日期"};
            for (int i = 0; i < titles.length; i++) {
                WritableCell writableCell = new Label(i, 0, titles[i], getTitleStyle());
                sheet.addCell(writableCell);
            }

            for (int i = 1; i < targets.size() + 1; i++) {
                Target target = targets.get(i - 1);
                sheet.addCell(new Label(0, i, "" + (i)));
                sheet.addCell(new Label(1, i, (String) target.getName()));
                sheet.addCell(new Label(2, i, (String) target.getNewMoney()));
                sheet.addCell(new Label(3, i, (String) target.getCollected()));
                sheet.addCell(new Label(4, i, (String) target.getMoneyInOut()));
                sheet.addCell(new Label(5, i, (String) target.getZhRate()));
                sheet.addCell(new Label(6, i, (String) target.getAmount()));
                sheet.addCell(new Label(7, i, (String) target.getCountUser()));
                sheet.addCell(new Label(8, i, (String) target.getBorrowCount()));
                sheet.addCell(new Label(9, i, (String) target.getInvestCount()));
                sheet.addCell(new Label(10, i, (String) target.getNewUserCount()));
                sheet.addCell(new Label(11, i, (String) target.getNewUserMoney()));
                sheet.addCell(new Label(12, i, (String) target.getDatetime()));
            }

            workbook.write();
            workbook.close();

            log.info("《"+firmName + "》公司数据文件保存成功！文件位置--> " + file.getAbsolutePath());
        }
    }

    private static WritableCellFormat getTitleStyle() throws WriteException {
        WritableCellFormat style = new WritableCellFormat();  // 单元格样式
        WritableFont font  = new WritableFont(WritableFont.createFont("宋体"));  // 字体
        font.setColour(Colour.BLACK);  // 设置字体颜色
        style.setAlignment(Alignment.CENTRE); // 设置单元格水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置单元格垂直居中
        style.setFont(font);  // 设置字体

        return style;
    }
}
