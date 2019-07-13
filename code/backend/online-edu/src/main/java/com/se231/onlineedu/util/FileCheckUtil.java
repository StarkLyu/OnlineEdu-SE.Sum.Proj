package com.se231.onlineedu.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author liu
 *
 * @date 2019/07/11
 */
public class FileCheckUtil {
    private static String fileExtension = ".jpg,.jpeg,.png,.svg,.tif";

    static public Boolean checkImageSizeExceed(MultipartFile multipartFile, int limit){
        return multipartFile.getSize() > limit;
    }

    static public Boolean checkImageTypeWrong(String suffix){
        return !fileExtension.contains(suffix);
    }
}
