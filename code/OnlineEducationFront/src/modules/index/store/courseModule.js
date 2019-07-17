import axios from 'axios'

axios.defaults.withCredentials = true;

const state = {
    id: 0,
    courseInfo: {}
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
}

export default {
    state,
    getters,
    actions,
    mutations
}
