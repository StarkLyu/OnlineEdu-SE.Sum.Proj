import React, {Component} from 'react';
import { connect } from "react-redux";
import { setAnswer } from "../store/paperAction";
import AssignmentSingle from "./AssignmentSingle";
import AssignmentMulti from "./AssignmentMulti";
import AssignmentJudge from "./AssignmentJudge";

class AssignmentQuestion extends Component {
    constructor(props) {
        super(props);
    }

    getAnswer() {
        let answer = this.props.answer.get(this.props.question.id);
        if (answer === undefined) {
            return "";
        }
        else return answer;
    }

    render() {
        let questionType = this.props.question.questionType;
        switch (questionType) {
            case "SINGLE_ANSWER":
                return (
                    <AssignmentSingle
                        question={this.props.question}
                        setAnswer={this.props.setAnswer}
                        initAnswer={this.getAnswer()}
                    />);
            case "MULTIPLE_ANSWER":
                return (
                    <AssignmentMulti
                        question={this.props.question}
                        setAnswer={this.props.setAnswer}
                        initAnswer={this.getAnswer()}
                    />);
            case "T_OR_F":
                return (
                    <AssignmentJudge
                        question={this.props.question}
                        setAnswer={this.props.setAnswer}
                        initAnswer={this.getAnswer()}
                    />);
            default: return null;
        }
    }
}

function mapStateToProps(state) {
    return {
        answer: state.paperAnswer.answerMap
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        setAnswer: (newAnswer) => dispatch(setAnswer(newAnswer))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(AssignmentQuestion);
