package com.rublov.heorhii.test.separator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ToXML {

    public void make(List<Message> messageList){
        XSSFWorkbook workbook = new XSSFWorkbook();
        for(int ml=0; ml<messageList.size(); ml++){
            Message message = messageList.get(ml);
            String[] strTemp = message.getText();
            List<Node> nodeList = message.getNodeList();
            XSSFSheet sheet = workbook.createSheet("Mail # "+ml);
            Row row;
            for(int i=0; i<message.getBaseLine()-2; i++){
                row = sheet.createRow(i);
                row.createCell(1).setCellValue(message.text[i]);
            }

            for(int il=message.getBaseLine()-1; il<strTemp.length-4; il++){
                row = sheet.createRow(il);
                for(int nl=0; nl<nodeList.size(); nl++){
                    Node node = nodeList.get(nl);
                    if(strTemp[il].length()>1){
                        row.createCell(nl).setCellValue(strTemp[il].substring(node.getStart(),node.getEnd()));
                    }

                }
            }
            for(int i=strTemp.length-4; i<strTemp.length; i++){
                row = sheet.createRow(i);
                row.createCell(1).setCellValue(message.text[i]);
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
