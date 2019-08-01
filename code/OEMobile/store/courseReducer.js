import {SET_COURSE_INFO} from "./courseActions";

const courseInfoInit = {
    avatarUrl: "",
    courseTitle: "",
    endDate:"2019-08-09T16:00:00.000+0000",
    id: 8,
    learns: [],
    location: "",
    notices: [],
    papers: [],
    sectionList: [],
    signIns: [],
    startDate: [],
    state: "",
    students: [],
    teacher: {},
    teacherAssistants: [],
    timeSlots: [],
};

function courseInfoAction(state = courseInfoInit, action) {
    switch (action.type) {
        case SET_COURSE_INFO:
            return action.courseInfo;
        default:
            return state;
    }
}

export default {
    courseInfo: courseInfoAction
}
