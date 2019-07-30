import React, {Component} from 'react';
import { connect } from 'react-redux';
import { Container, Text, H1 } from "native-base";
import UserUnit from './UserUnit';

class CourseInfo extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <H1>{this.props.courseInfo.courseTitle}</H1>
                <Text>老师：<UserUnit user={this.props.courseInfo.teacher}/></Text>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    return {
        courseInfo: state.courseInfo
    }
}

export default connect(mapStateToProps, null)(CourseInfo);
