import React, {Component} from 'react';
import { connect } from "react-redux";
import { FlatList } from "react-native";
import { Container, ListItem, Text, H3, Left } from 'native-base';
import {setAnswer} from "../store/paperAction";

class AssignmentSingle extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: "",
        }
    }

    selectOption(select) {
        this.setState({
            answer: select
        });
        let newAnswer = {
            questionId: this.props.question.id,
            answer: select
        };
        this.props.setAnswer(newAnswer);
    }

    _renderOption(optionKey) {
        return (
            <ListItem selected={optionKey === this.state.answer} onPress={() => this.selectOption(optionKey)}>
                <Text>{optionKey + ". " + this.props.question.options[optionKey]}</Text>
            </ListItem>
        )
    }

    render() {
        return (
            <Container>
                <ListItem>
                    <Left>
                        <H3>单选题</H3>
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

const mapDispatchToProps = (dispatch) => {
    return {
        setAnswer: (newAnswer) => dispatch(setAnswer(newAnswer))
    }
};

export default connect(null, mapDispatchToProps)(AssignmentSingle);
