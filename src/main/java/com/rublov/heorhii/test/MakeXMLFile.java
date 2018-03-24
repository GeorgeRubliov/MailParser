package com.rublov.heorhii.test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MakeXMLFile {

    public void make(List<MessageMail> messageMails){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet;
        for(int i=0; i<messageMails.size(); i++){
            MessageMail mm = messageMails.get(i);
            sheet = workbook.createSheet("Sheet by Mail # "+i);
            int rowNum = 0;
            Row row = sheet.createRow(rowNum);
            for(int a=0; a<4; a++){
                for(int lc=0; lc<mm.getLists().get(a).size(); lc++){
                    row.createCell(lc).setCellValue(mm.getLists().get(a).get(lc));
                }
                rowNum++;
                row = sheet.createRow(rowNum);
            }

            row.createCell(1).setCellValue("Check");
            row.createCell(2).setCellValue("Vendor");
            row.createCell(3).setCellValue("Pay-To Name");
            row.createCell(4).setCellValue("Chk-Date");
            row.createCell(5).setCellValue("Invoice Number");
            row.createCell(6).setCellValue("Invc-Amount");
            row.createCell(7).setCellValue("Disc-Amount");
            row.createCell(8).setCellValue("NetCk-Amount");

            rowNum++;
            row = sheet.createRow(rowNum);
            row.createCell(8).setCellValue(mm.getLists().get(7).get(6));
            row.createCell(7).setCellValue(mm.getLists().get(7).get(5));
            row.createCell(6).setCellValue(mm.getLists().get(7).get(4));
            String[] temp = mm.getLists().get(7).get(3).split(" ");
            row.createCell(5).setCellValue(temp[1]);
            row.createCell(4).setCellValue(temp[0]);
            row.createCell(3).setCellValue(mm.getLists().get(7).get(2));
            row.createCell(2).setCellValue(mm.getLists().get(7).get(1));
            row.createCell(1).setCellValue(mm.getLists().get(7).get(0));


            for(int cl = 8; cl<mm.getLists().size(); cl++){
                rowNum++;
                row = sheet.createRow(rowNum);
                for(int cell = 8, t = mm.getLists().get(cl).size()-1; t>=0; cell--, t--){
                    row.createCell(cell).setCellValue(mm.getLists().get(cl).get(t));
                }
            }

            try(FileOutputStream out = new FileOutputStream(new File("test.xlsx"))){
                workbook.write(out);
                out.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }

    }

}
