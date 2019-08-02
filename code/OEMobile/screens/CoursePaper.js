import React, {Component} from 'react';
import { connect } from 'react-redux';
import { View } from 'react-native';
import { Container, Text, H1, List, ListItem, Button, Left, Right, Body } from "native-base";
import AssignmentQuestion from "../components/AssignmentQuestion";

class CoursePaper extends Component {
    constructor(props) {
        super(props);
        this.paperId = this.props.navigation.getParam("paperId", 0);
        this.state = {
            state: "NOT_FINISH",
            grade: 0,
            currentQuestion: -1
        };
    }

    componentWillMount(): void {
        this.paper = this.props.navigation.getParam("paper", {});
        this.paper.questions.sort((a, b) => {
            if (a.questionNumber <= b.questionNumber) return -1;
            else return 1;
        });
        this.questions = [];
        for (let ques of this.paper.questions) {
            this.questions.push(ques.paperWithQuestionsPrimaryKey.question);
        }
    }

    paperStatus() {
        switch (this.state.state) {
            case "NOT_START": return "未开始";
            case "NOT_FINISH": return "未提交";
            case "FINISHED": return "已提交";
            case "NOT_MARK": return "未批改";
            case "MARKED": return "已批改";
            default: return "什么鬼";
        }
    }

    startAnswer = () => {
        this.setState({
            currentQuestion: 0
        })
    };

    nextQuestion = () => {
        let temp = this.state.currentQuestion;
        temp++;
        this.setState({
            currentQuestion: temp
        })
    };

    lastQuestion = () => {
        let temp = this.state.currentQuestion;
        temp--;
        this.setState({
            currentQuestion: temp
        })
    };

    firstQues = () => {
        return this.state.currentQuestion === 0;
    };

    lastQues = () => {
        return this.state.currentQuestion === this.questions.length - 1;
    };

    saveAnswer = () => {
        console.log(this.props.paperAnswer)
    }

    render() {
        const startTime = this.paper.start.substr(0,10) + " " + this.paper.start.substr(11,8);
        const endTime =  this.paper.end.substr(0,10) + " " + this.paper.end.substr(11,8);
        if (this.state.currentQuestion === -1) {
            return (
                <Container>
                    <List>
                        <ListItem>
                            <H1>{this.paper.title}</H1>
                        </ListItem>
                        <ListItem>
                            <Text>状态：{this.paperStatus()}</Text>
                        </ListItem>
                        <ListItem>
                            <Text>开始时间：{startTime}</Text>
                        </ListItem>
                        <ListItem>
                            <Text>截止时间：{endTime}</Text>
                        </ListItem>
                        {
                            this.state.state === "MARKED" ?
                                <ListItem>
                                    <Text>总分：{this.state.grade}</Text>
                                </ListItem> : null
                        }
                        <Button full onPress={() => {this.startAnswer()}}>
                            <Text>开始答题</Text>
                        </Button>
                        <Text>注意：移动端暂不支持回答主观题，如作业中有主观题请勿在此提交作业</Text>
                    </List>
                </Container>
            );
        }
        else {
            return (
                <Container>
                    <AssignmentQuestion question={this.questions[this.state.currentQuestion]}/>
                    <ListItem>
                        <View style={{flexDirection: "row"}}>
                            <View style={{flex: 1}}>
                                <Button full disabled={this.firstQues()} onPress={() => {this.lastQuestion()}}>
                                    <Text>上一题</Text>
                                </Button>
                            </View>
                            <View style={{flex: 2, flexDirection: 'row'}}>
                                <View style={{flex: 1}}>
                                    <Button full onPress={() => {this.saveAnswer()}}>
                                        <Text>
                                            暂存
                                        </Text>
                                    </Button>
                                </View>
                                <View style={{flex: 1}}>
                                    <Button full onPress={() => {this.saveAnswer()}}>
                                        <Text>
                                            提交
                                        </Text>
                                    </Button>
                                </View>
                            </View>
                            <View style={{flex: 1}}>
                                <Button full disabled={this.lastQues()} onPress={() => {this.nextQuestion()}}>
                                    <Text>下一题</Text>
                                </Button>
                            </View>
                        </View>
                    </ListItem>
                </Container>
            )
        }
    }
}

function mapStateToProps(state) {
    return {
        paperAnswer: state.paperAnswer
    }
}

export default connect(mapStateToProps)(CoursePaper);
