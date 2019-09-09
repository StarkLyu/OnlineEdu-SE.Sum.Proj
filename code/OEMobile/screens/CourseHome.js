import React, { Component } from "react";
import { Modal } from "react-native";
import CourseInfo from "../components/CourseInfo";
import { Container, Content, Button, Icon } from "native-base";
import CourseAnnouncement from "../components/CourseAnnouncement";

class CourseHome extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <Content>
                    <CourseInfo navigation={this.props.navigation}/>
                </Content>
            </Container>
        )
    }
}

export default CourseHome;
