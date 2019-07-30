import axios from 'axios'

axios.defaults.withCredentials = true;

const state = {
    id: 0,
    courseInfo: {},
    identity: "",
    paperId:0,
};

// getters
const getters = {
    getCourseId: state => {
        return state.id;
    },
    getCourseUrl: state => {
        return "/api/courses/" + state.id + "/";
    },
    getCourseInfo: state => {
        return state.courseInfo;
    },
    getCourseTitle: state => {
        return state.courseInfo.courseTitle;
    },
    getCourseImg: state => {
        if (state.courseInfo.avatarUrl !== "") {
            return "http://202.120.40.8:30382/online-edu/static/" + state.courseInfo.avatarUrl + "?a=" + Math.random();
        }
        else return "";
    },
    isCourseTeacher: state => {
        if (state.identity === "STUDENT") return false;
        else return true;
    },
    getPaperById: (state) => (paperId) => {
        for (let paper of state.courseInfo.papers) {
            if (paper.id === paperId) {
                return paper;
            }
        }
    },
    getPaperId:state => {
        return state.paperId;
    }
};

// actions
const actions = {

};

// mutations
const mutations = {
    setCourseId(state, courseId) {
        state.id = courseId;
    },
    setCourseInfo: (state, info) => {
        state.courseInfo = info;
    },
    setIdentity: (state, identity) => {
        state.identity = identity;
    },
    setPaperId(state, paperId) {
        state.paperId=paperId;
    }
};

export default {
    state,
    getters,
    actions,
    mutations
}
