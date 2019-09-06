package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.message.request.TempRecord;
import com.se231.onlineedu.model.StudyRecord;
import com.se231.onlineedu.model.StudyRecordPrimaryKey;
import com.se231.onlineedu.model.StudyTempRecord;
import com.se231.onlineedu.model.VideoAction;
import com.se231.onlineedu.repository.StudyRecordRepository;
import com.se231.onlineedu.repository.StudyTempRecordRepository;
import com.se231.onlineedu.service.StudyRecordService;
import com.se231.onlineedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2019/09/06
 */
@Service
public class StudyRecordServiceImpl implements StudyRecordService {
    @Autowired
    StudyTempRecordRepository studyTempRecordRepository;

    @Autowired
    StudyRecordRepository studyRecordRepository;

    @Autowired
    UserService userService;

    static final int MAX_STUDY_TIME = 720;

    @Override
    public StudyTempRecord submitRecord(Long userId, TempRecord tempRecord) {
        StudyTempRecord studyTempRecord = studyTempRecordRepository.findById(userId)
                .orElse(new StudyTempRecord(userId))   ;
        VideoAction videoAction = VideoAction.valueOf(tempRecord.getAction());
        java.sql.Date date = new java.sql.Date(tempRecord.getTime().getTime());
        StudyRecord studyRecord = studyRecordRepository.findById(new StudyRecordPrimaryKey(userService.getUserInfo(userId),date))
                .orElse(new StudyRecord(userService.getUserInfo(userId),date));
        switch (videoAction) {
            case CHANGE_SPEED:
                studyRecord.setChangeSpeedTime(studyRecord.getChangeSpeedTime() + 1);
                studyRecordRepository.save(studyRecord);
                return studyTempRecord;

            case JUMP :
                studyRecord.setJumpTimes(studyRecord.getJumpTimes() + 1);
                studyRecordRepository.save(studyRecord);
                return studyTempRecord;

            case START_PLAY:
                studyTempRecord.setPrevState(VideoAction.START_PLAY);
                studyTempRecord.setStartTime(tempRecord.getTime());
                return studyTempRecordRepository.save(studyTempRecord);

            case PAUSE:
                if(studyTempRecord.getPrevState()!=null&&studyTempRecord.getPrevState().equals(VideoAction.PAUSE)){
                    return studyTempRecord;
                }
                studyTempRecord.setPrevState(VideoAction.PAUSE);
                studyTempRecord.setPrevTime(tempRecord.getTime());
                return studyTempRecordRepository.save(studyTempRecord);

            default:
                if(studyTempRecord.getPrevState()==null){
                    studyTempRecord.setPrevState(videoAction);
                    System.out.println(studyTempRecord.getPrevState());
                    return studyTempRecordRepository.save(studyTempRecord);
                }
                if (studyTempRecord.getPrevState().equals(VideoAction.PAUSE)) {
                    Long pauseTime = tempRecord.getTime().getTime() - studyTempRecord.getPrevTime().getTime();
                    int pause = pauseTime.intValue()/60000;
                    studyRecord.setPauseTime((studyRecord.getPauseTime() + pause));
                    if(videoAction.equals(VideoAction.FINISH_WATCH)){
                        studyRecord.setTimeInMinute(((int)(tempRecord.getTime().getTime()-studyTempRecord.getStartTime().getTime())+studyRecord.getTimeInMinute())/60000);
                    }
                }

                else if(videoAction.equals(VideoAction.FINISH_WATCH)){
                    studyRecord.setTimeInMinute(((int)(tempRecord.getTime().getTime()-studyTempRecord.getStartTime().getTime())+studyRecord.getTimeInMinute())/60000);
                }
                if(studyRecord.getTimeInMinute()>MAX_STUDY_TIME){
                    studyRecord.setTimeInMinute(MAX_STUDY_TIME);
                }
                studyRecordRepository.save(studyRecord);
                studyTempRecord.setPrevState(videoAction);
                return studyTempRecordRepository.save(studyTempRecord);
            }
        }
}
