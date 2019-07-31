import React, {Component} from 'react';
import { FlatList } from "react-native";
import { Container, Separator, ListItem, Text } from "native-base";

class CourseSection extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <Separator>
                    <Text>章节简介</Text>
                </Separator>
                <ListItem>
                    <Text>
                        { this.props.section.description }
                    </Text>
                </ListItem>
                <Separator>
                    <Text>章节资源</Text>
                </Separator>
                <FlatList />
                <Separator>
                    <Text>章节作业</Text>
                </Separator>
            </Container>
        );
    }
}

export default CourseSection;
