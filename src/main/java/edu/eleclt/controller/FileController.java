package edu.eleclt.controller;


import cn.hutool.crypto.SecureUtil;
import edu.eleclt.entity.MyFile;
import edu.eleclt.loader.Log;
import edu.eleclt.repository.FileRepository;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import static edu.zip.MyHuffmanTree.zipFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileRepository fileRepository = new FileRepository();

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @GetMapping("/findAll")
    public Object[] findAll() {
        return fileRepository.findAll().getAll();
    }

    @GetMapping("/searchByName/{name}")
    public Object[] searchByName(@PathVariable("name") String Name) {
        Log.write("查找名称为"+Name+"的活动");
        return fileRepository.searchByName(Name).getAll();
    }

    @PostMapping("/upload")
    public Object[] upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String type = name.substring(name.lastIndexOf(".") + 1);
        name = name.substring(0, name.lastIndexOf("."));
        long size = file.getSize();
        String uName = Long.toString(new Date().getTime());
        File files = new File(fileUploadPath + uName + "." + type);

        String url = "http://localhost:9090/file/" + uName + "." + type;
        String md5 = SecureUtil.md5(file.getInputStream());
        if (fileRepository.findMd5(md5))
            return new Object[]{"", ""};

        file.transferTo(files);
        zipFile(fileUploadPath + uName + "." + type, fileUploadPath + "zip/" + uName + ".zip");

        fileRepository.addFile(new MyFile(size, name, type, url, md5));
        System.out.println("fileRepository = " + fileRepository.size());

        Log.write("上传文件名为"+name+"的文件");
        return new Object[]{url, uName + "." + type};
    }

    @GetMapping("/download/{uName}")
    public void download(@PathVariable String uName, HttpServletResponse response) throws IOException {
        File file = new File(fileUploadPath + uName);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uName, "UTF-8"));
        response.setContentType("application/octet-stream");

        os.write(FileUtils.readFileToByteArray(file));
        os.flush();
        os.close();
    }

    @Test
    public void addFile(){

    }
}
