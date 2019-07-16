package com.se231.onlineedu.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.UUID;


/**
 * @author YuxuanLiu
 *
 * @date 2019/07/12
 */
public class SaveFileUtil {
    private static String nginxPath = "/home/liu/nginx/online-edu/";

    public static String saveAvatar(Long id, MultipartFile multipartFile, String suffix, String type) throws IOException {
        String fileName = nginxPath + id + type +"-avatar/" + id + "-avatar" + suffix;
        File file = new File(fileName);

        if (file.getParentFile().exists()) {
            FileUtils.cleanDirectory(file.getParentFile());
        } else {
            file.getParentFile().mkdir();
        }
        System.out.println(file.getAbsolutePath());

        file.createNewFile();
        multipartFile.transferTo(file);
        Files.setPosixFilePermissions(file.getParentFile().toPath(), PosixFilePermissions.fromString("rwxrwxrwx"));
        Files.setPosixFilePermissions(file.toPath(), PosixFilePermissions.fromString("rwxr--r--"));

        return id + type+ "-avatar/" + id + "-avatar" + suffix;
    }

    public static String saveFile(MultipartFile multipartFile, String suffix) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + suffix;
        String filePath = nginxPath + fileName;
        File file = new File(filePath);
        file.createNewFile();

        System.out.println(file.getAbsolutePath());
        multipartFile.transferTo(file);
        Files.setPosixFilePermissions(file.toPath(), PosixFilePermissions.fromString("rwxr--r--"));

        return fileName;
    }


    public static ImageWithInfo saveImages(MultipartFile[] multipartFiles, int limit) throws IOException {
        int num = 1;
        ImageWithInfo imageWithInfo = new ImageWithInfo();
        StringBuilder errorString = new StringBuilder();
        for (MultipartFile multipartFile : multipartFiles) {
            if (FileCheckUtil.checkImageSizeExceed(multipartFile, limit)) {
                imageWithInfo.setHasError(true);
                errorString.append("image " + num + " size Exceed");
            }
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            if (FileCheckUtil.checkImageTypeWrong(suffix)) {
                imageWithInfo.setHasError(true);
                errorString.append("image " + num + " format error");
            }
            imageWithInfo.getImagesUrls().add(SaveFileUtil.saveFile(multipartFile, suffix));
        }
        return imageWithInfo;
    }

    public static boolean deleteImage(String fileName) throws IOException {
        String filePath = nginxPath + fileName;
        Path path = Paths.get(filePath);
        return Files.deleteIfExists(path);
    }
}
