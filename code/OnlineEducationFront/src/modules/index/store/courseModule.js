import axios from 'axios'

axios.defaults.withCredentials = true;

const state = {
    id: 0,
    courseInfo: {},
    identity: "",
}

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
        return state.courseInfo.imgUrl;
    },
    isCourseTeacher: state => {
        if (state.identity === "STUDENT") return false;
        else return true;
    }
}

// actions
const actions = {

}

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
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}
