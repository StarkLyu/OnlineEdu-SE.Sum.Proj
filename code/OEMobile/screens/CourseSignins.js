import React, {Component} from 'react';
import { FlatList } from "react-native";
import {Container, List, ListItem, Text, Button, Icon, View} from "native-base";
import CourseHeader from "../components/CourseHeader";
import UserFab from "../components/UserFab";
import {connect} from "react-redux";
import Geolocation from "react-native-geolocation-service"

class CourseSignins extends Component {
    constructor(props) {
        super(props);
        this.showDrawer = this.showDrawer.bind(this);
        this.state = {
            signIns: []
        };
        this.signins = [{
            signIn: {
                startDate: "ss",
                endDate: "ss"
            },
            ok: false
        }];
        this.$axios.request({
            url: this.signInUrl(),
            method: "get",
            headers: {
                Authorization: "Bearer " + this.props.accessToken
            }
        }).then((response) => {
            this.signins = response.data;
            console.log(this.signins);
            this.setState({
                signIns: response.data
            })
        }).catch((error) => {
            console.log(error.response);
        })
    }

    startSignIn = (signInNo) => {
        this.$axios.request({
            url: "/api/courses/" + this.props.userId + "/signIns",
            method: "post",
            headers: {
                Authorization: "Bearer " + this.props.accessToken
            },
            data: {
                courseId: this.props.courseId,
                signInNo: signInNo,
            }
        })
    };

    signInUrl = () => {
        return "/api/courses/" + this.props.courseId + "/signIns"
    };

    statusText = (status) => {
        if (status) return "已签到";
        else return "未签到"
    };

    showDrawer() {
        this.props.navigation.openDrawer()
    }

    _signInLine(signIn) {
        return (
            <ListItem>
                <View style={{flexDirection: "row"}}>
                    <View style={{flex: 5}}>
                        <Text>签到：{signIn.signIn.startDate + " ~ " + signIn.signIn.endDate + "  " + this.statusText(signIn.ok)}</Text>
                    </View>
                    <View style={{flex: 1}}>
                        <Button icon>
                            <Icon name={"sign-in"} type={"FontAwesome"}/>
                        </Button>
                    </View>
                </View>
            </ListItem>
        )
    }

    render() {
        return (
            <Container>
                <CourseHeader openDrawer={this.showDrawer} />
                <List>
                    <FlatList data={this.state.signIns} renderItem={({item}) => this._signInLine(item)}/>
                </List>
                <UserFab navigation={this.props.navigation}/>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    return {
        courseId: state.courseInfo.id,
        accessToken: state.login.accessToken,
        userId: state.userInfo.id
    }
}

export default connect(mapStateToProps, null)(CourseSignins);
