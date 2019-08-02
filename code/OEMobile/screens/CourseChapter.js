import React, {Component} from 'react';
import { View, FlatList, } from "react-native";
import { Container, Content, ListItem, Text, H3 } from "native-base";
import { connect } from "react-redux";
import CourseHeader from "../components/CourseHeader";
import { setCurrentSection } from "../store/sectionAction";
import UserFab from "../components/UserFab";

class CourseChapter extends Component {
    constructor(props) {
        super(props);
    }

    moveToSection(section) {
        this.props.setCurrentSection(section);
        this.props.navigation.navigate("CourseSection");
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
            <ListItem onPress={() => {this.moveToSection(section)}}>
                <Text>{section.title}</Text>
            </ListItem>
        )
    }

    render() {
        return (
            <Container>
                <CourseHeader openDrawer={this.props.navigation.openDrawer} navigation={this.props.navigation}/>
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

const mapDispatchToProps = (dispatch) => {
    return {
        setCurrentSection: (section) => dispatch(setCurrentSection(section))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(CourseChapter);
