//package com.se231.onlineedu.Repository;
//
//import com.se231.onlineedu.model.TimeSlot;
//import com.se231.onlineedu.model.WeekDay;
//import com.se231.onlineedu.repository.TimeSlotRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Time;
//import java.util.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// *
// * @author liu
// * @date 2019/07/17
// */
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class TimeSlotRepositoryTest {
//    @Autowired
//    private TimeSlotRepository timeSlotRepository;
//
//    @Test
//    public void findByDateAndStartAndEnd() throws Exception {
//        Time time = new Time(1);
//        TimeSlot timeSlot = new TimeSlot();
//        timeSlot.setDay(WeekDay.FRIDAY);
//        timeSlot.setEnd(time);
//        timeSlot.setStart(time);
//        TimeSlot found = timeSlotRepository.save(timeSlot);
//        System.out.println(found.getDay());
//        System.out.println(found.getEnd());
//        System.out.println(found.getStart());
//        System.out.println(time);
//        TimeSlot found1 = timeSlotRepository.findByDayAndStartAndEnd(WeekDay.FRIDAY, time, time).orElseThrow(()->new Exception("hah"));
//
//        System.out.println(found.getDay());
//
//        assertThat(found.getDay()).isEqualTo(WeekDay.FRIDAY);
//    }
//}
