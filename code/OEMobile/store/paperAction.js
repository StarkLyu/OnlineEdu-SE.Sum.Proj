export const SET_ANSWER = "ADD_ANSWER";

export function setAnswer(newAnswer) {
    return {
        type: SET_ANSWER,
        newAnswer: newAnswer
    }
}
