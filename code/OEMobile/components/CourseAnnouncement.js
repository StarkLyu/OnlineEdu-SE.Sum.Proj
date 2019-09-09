import React, { Component } from "react";
import { connect } from "react-redux";
import { Container, Content, Accordion } from "native-base";

const announcement = [
    {
        title: "First Notice",
        content: "hahahaha\nijdijfidjfijdi"
    }
]

class CourseAnnouncement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            announcements: []
        }
    }

    componentDidMount(): void {
        let temp = [];
        for (let i of this.props.announcements) {
            temp.push({
                content: i.content,
                title: i.title
            })
        }
        this.setState({
            announcements: temp
        })
    }

    render() {
        return (
            <Container>
                <Content>
                    <Accordion dataArray={this.props.announcements}/>
                </Content>
            </Container>
        )
    }
}

function mapStateToProps(state) {
    return {
        announcements: state.courseInfo.notices
    }
}

export default connect(mapStateToProps, null)(CourseAnnouncement);
