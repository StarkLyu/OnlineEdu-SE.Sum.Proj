import React, {Component} from 'react';
import {Container, H3, Left, ListItem, Text} from "native-base";
import {FlatList} from "react-native";

class AssignmentMulti extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: "",
            selectedList: this.props.initAnswer.split("")
        }
    }

    selectOption = (optionKey) => {
        let newSelectedList = this.state.selectedList;
        if (this.optionSelected(optionKey)) {
            newSelectedList.splice(newSelectedList.indexOf(optionKey), 1);
        }
        else {
            newSelectedList.push(optionKey);
        }
        newSelectedList.sort();
        this.setState({
            answer: this.answerString(newSelectedList),
            selectedList: newSelectedList
        });
        let newAnswer = {
            questionId: this.props.question.id,
            answer: this.state.answer
        };
        this.props.setAnswer(newAnswer);
    };

    optionSelected = (optionKey) => {
        if (this.state.selectedList.indexOf(optionKey) === -1) return false;
        else return true;
    };

    answerString = (selectedList) => {
        let answer = "";
        for (let i in selectedList) {
            answer += i;
        }
        return answer;
    };

    _renderOption(optionKey) {
        return (
            <ListItem selected={this.optionSelected(optionKey)} onPress={() => this.selectOption(optionKey)}>
                <Text>{optionKey + ". " + this.props.question.options[optionKey]}</Text>
            </ListItem>
        )
    }

    render() {
        return (
            <Container>
                <ListItem>
                    <Left>
                        <H3>多选题</H3>
                    </Left>
                </ListItem>
                <ListItem>
                    <Text>{this.props.question.content}</Text>
                </ListItem>
                <FlatList data={Object.keys(this.props.question.options)} renderItem={({item}) => this._renderOption(item)} />
            </Container>
        );
    }
}

export default AssignmentMulti;
