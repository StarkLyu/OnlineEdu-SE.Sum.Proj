import React, {Component} from 'react';
import { Container, Content } from "native-base";
import { connect } from "react-redux";
import CourseForumResponseUnit from "../components/CourseForumResponseUnit";

class CourseForumResponses extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <Content>
                    {
                        this.props.responses.map((item,index) => {
                            return <CourseForumResponseUnit response={item}/>
                        })
                    }
                </Content>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    return {
        responses: state.topic.responses
    }
}

export default connect(mapStateToProps, null)(CourseForumResponses);
