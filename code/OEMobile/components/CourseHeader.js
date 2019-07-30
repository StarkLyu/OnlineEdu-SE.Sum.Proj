import React, {Component} from 'react';
import { Header, Left, Body, Button, Title, Icon } from "native-base";
import { connect } from "react-redux";

class CourseHeader extends Component {
    constructor(props) {
        super(props);
    }

    showDrawer() {
        this.props.openDrawer();
    }

    render() {
        return (
            <Header>
                <Left>
                    <Button transparent onPress={() => {this.showDrawer()}}>
                        <Icon name='menu' />
                    </Button>
                </Left>
                <Body>
                    <Title>
                        { this.props.courseTitle }
                    </Title>
                </Body>
            </Header>
        );
    }
}

function mapStateToProps(state) {
    return {
        courseTitle: state.courseInfo.courseTitle
    }
}

export default connect(mapStateToProps)(CourseHeader);
