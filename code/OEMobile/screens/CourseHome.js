import React, { Component } from "react";
import CourseInfo from "../components/CourseInfo";
import { Container } from "native-base";
import CourseHeader from "../components/CourseHeader";
import UserFab from "../components/UserFab";

class CourseHome extends Component {
    constructor(props) {
        super(props);
        this.showDrawer = this.showDrawer.bind(this);
    }

    showDrawer() {
        this.props.navigation.openDrawer()
    }

    render() {
        return (
            <Container>
                <CourseHeader openDrawer={this.showDrawer} />
                <CourseInfo/>
                <UserFab navigation={this.props.navigation}/>
            </Container>
        )
    }
}

export default CourseHome;
