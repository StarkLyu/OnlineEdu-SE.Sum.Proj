package com.se231.onlineedu.util;

import java.util.ArrayList;
import java.util.List;

public class ImageWithInfo {
    private List<String> imagesUrls = new ArrayList<>();
    private boolean hasError;
    private String ErrorMessage;

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
