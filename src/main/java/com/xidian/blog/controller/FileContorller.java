package com.xidian.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author 米
 * @date 2020/2/17
 */

@Controller
@RequestMapping("/file/")
public class FileContorller {
    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        //獲取原始名字
        String fileName = file.getOriginalFilename();
        // 獲取後綴名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路徑
        String filePath = "d:/upload/";
        // 文件重命名，防止重複
        fileName = filePath + UUID.randomUUID() + fileName;
        //文件對象
        File dest = new File(fileName);

        //判斷路徑是否存在，如果不存在則創建
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            //保存到服務器
            file.transferTo(dest);
            return "上傳成功";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "上傳失敗";
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        //文件位置，真實環境是存放在數據庫中
        File file = new File("D:\\upload\\a.txt");
        //創建輸入對象
        FileInputStream fis = new FileInputStream(file);
        //設置相關格式
        response.setContentType("application/force-download");
        //設置下載后的文件名以及header
        response.addHeader("Content-disposition","attachment;FileName=" + "a,txt");
        //創建輸出對象
        OutputStream os = response.getOutputStream();
        //常規操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1){
            os.write(buf,0,len);
        }
        fis.close();

    }
}
