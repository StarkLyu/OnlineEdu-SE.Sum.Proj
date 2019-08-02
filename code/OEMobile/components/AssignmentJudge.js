import React, {Component} from 'react';
import { FlatList } from "react-native";
import { Container, ListItem, Text, H3, Left } from 'native-base';

class AssignmentJudge extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: "",
        }
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
                <ListItem onPress={() => {this.setState({answer: "T"})}} selected={this.state.answer === "T"}>
                    <Text>正确</Text>
                </ListItem>
                <ListItem onPress={() => {this.setState({answer: "F"})}} selected={this.state.answer === "F"}>
                    <Text>错误</Text>
                </ListItem>
            </Container>
        );
    }
}

export default AssignmentJudge;
