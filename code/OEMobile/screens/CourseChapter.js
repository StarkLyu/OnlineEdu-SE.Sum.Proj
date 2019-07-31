import React, {Component} from 'react';
import { View, FlatList, TouchableOpacity } from "react-native";
import { Container, Content, Separator, ListItem, Text, H3 } from "native-base";
import { connect } from "react-redux";
import CourseHeader from "../components/CourseHeader";

class CourseChapter extends Component {
    constructor(props) {
        super(props);
    }

    moveToSection(section) {
        this.props.navigation.navigate("CourseSection", {
            section: section
        })
    }

    _drawChapter(chapter) {
        return (
            <View>
                <ListItem itemDivider>
                    <Text>{chapter.title}</Text>
                </ListItem>
                <FlatList renderItem={({item}) => this._drawSection(item)} data={chapter.sectionBranchesList} />
            </View>
        )
    }

    _drawSection(section) {
        return (
            <TouchableOpacity onPress={() => {this.moveToSection(section)}}>
                <ListItem>
                    <Text>{section.title}</Text>
                </ListItem>
            </TouchableOpacity>
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
