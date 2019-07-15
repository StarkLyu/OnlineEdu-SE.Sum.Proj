package com.se231.onlineedu.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author liu
 *
 * @date 2019/07/11
 */
public class FileCheckUtil {
    private static String imageFileExtension = ".jpg,.jpeg,.png,.svg,.tif";

    private static String videoFileExtension = ".avi.mpg.mlv.mpe.mpeg.dat.mp4";

    static public Boolean checkImageSizeExceed(MultipartFile multipartFile, int limit){
        return multipartFile.getSize() > limit;
    }

    static public Boolean checkImageTypeWrong(String suffix){
        return !imageFileExtension.contains(suffix);
    }

    static public Boolean checkVideoTypeWrong(String suffix){
        return !videoFileExtension.contains(suffix);
    }
}
