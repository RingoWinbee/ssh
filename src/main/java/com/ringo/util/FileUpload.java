package com.ringo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	public String imageUpload(MultipartFile file,String frontName) throws Exception {
		String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //这里文件名生成策略比较简单
        String fname = sdf.format(new Date())+((int)(Math.random()*10))+((int)(Math.random()*10))+fileName.substring(fileName.lastIndexOf('.'));
        String name=frontName+fname;
        FileOutputStream fos = new FileOutputStream(name);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return fname;
	}
}
