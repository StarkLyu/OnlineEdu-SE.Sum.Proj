package com.se231.onlineedu.message.request;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import com.se231.onlineedu.model.WeekDay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Time Slot request form
 *
 * this form receive the request of setting course time.`
 *
 * @author Zhe Li
 * @date 2019/07/12
 */
@ApiModel("填写课程时间的表单")
public class TimeSlotForm {

    @ApiModelProperty(value = "时间段在周几",allowableValues = "0-6依次代表SUNDAY-SATURDAY")
    @NotNull
    private WeekDay day;

    @ApiModelProperty("开始时间是几点")
    @NotNull
    private Time start;

    @NotNull
    @ApiModelProperty("结束时间是几点")
    private Time end;
}
