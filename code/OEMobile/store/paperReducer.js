import { SET_ANSWER } from "./paperAction"

const initAnswer = {
    answerMap: new Map()
};

function paperReducer(state = initAnswer, action) {
    console.log(action);
    switch (action.type) {
        case SET_ANSWER:
            let changeAnswer = new Map(state.answerMap);
            changeAnswer.set(action.newAnswer.questionId, action.newAnswer.answer);
            return {
                answerMap: changeAnswer
            };
        default:
            return state;
    }
}

export default {
    paperAnswer: paperReducer
};
