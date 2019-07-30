import React, {Component} from 'react';
import { View, FlatList } from "react-native";
import { Container, Content, Separator, ListItem, Text, H3 } from "native-base";
import { connect } from "react-redux";
import CourseHeader from "../components/CourseHeader";

class CourseChapter extends Component {
    constructor(props) {
        super(props);
    }

    _drawChapter(chapter) {
        return (
            <View>
                <Separator>
                    <H3>{chapter.title}</H3>
                </Separator>
                <FlatList renderItem={({item}) => this._drawSection(item)} data={chapter.sectionBranchesList} />
            </View>
        )
    }

    _drawSection(section) {
        return (
            <ListItem>
                <Text>{section.title}</Text>
            </ListItem>
        )
    }

    render() {
        return (
            <Container>
                <CourseHeader openDrawer={this.props.navigation.openDrawer}/>
                <Content>
                    <FlatList renderItem={({item}) => this._drawChapter(item)} data={this.props.chapters} />
                </Content>
            </Container>
        );
    }
}
function mapStateToProps(state) {
    return {
        chapters: state.courseInfo.sectionList
    }
}

export default connect(mapStateToProps)(CourseChapter);
