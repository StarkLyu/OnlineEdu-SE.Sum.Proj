package com.se231.onlineedu.message.response;

/**
 * Response of application Class\
 *
 * response message for all kind of application.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
public class ApplyResponse {
    private int alert;

    public ApplyResponse(int alert){
        this.alert=alert;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }
}
