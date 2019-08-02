import React from "react";
import {FlatList, Image, View, StyleSheet, TouchableOpacity} from 'react-native';
import {Container, Header, Body, Text, Title, Right, Button, Icon} from 'native-base';
import UserUnit from "../components/UserUnit";
import UserHeader from "../components/UserHeader";
import { connect } from 'react-redux';

class UserCourseList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            activeFab: 'true',
            showCourses: '教授课程',
            courses: []
        };
        this.initialCourseData();
    }

    initialCourseData() {
        this.$axios.request({
            url: "/api/users/info/courses/learn",
            method: "get",
            headers: {
                "Authorization": "Bearer " + this.props.accessToken
            }
        }).then((response) => {
            console.log(response.data);
            this.setState({courses: response.data});
        }).catch((error) => {
            alert(error);
        })
    }

    enterCourse(courseId) {
        this.$axios.request({
            url: "/api/courses/" + courseId + "/info",
            method: "get",
            headers: {
                "Authorization": "Bearer " + this.props.accessToken
            }
        }).then((response) => {
            this.props.setCourseInfo(response.data.course);
            this.props.navigation.navigate("Course", {courseId: courseId});
        }).catch((error) => {
            alert(error);
            console.log(error.response);
        })
    }

    _createCourseCard(course) {
        //console.log(course.teacher);
        return (
            <TouchableOpacity onPress={() => {this.enterCourse(course.id)}}>
                <View style={styles.mainCard}>
                    <View style={styles.imgView}>
                        <Image style={styles.courseImg} source={{uri: "http://202.120.40.8:30382/online-edu/static/" + course.avatarUrl}}/>
                    </View>
                    <View style={styles.textView}>
                        <Text style={styles.titleText}>{course.courseTitle}</Text>
                        <View style={styles.secondLine}>
                            <View style={{flex: 1}}>
                                <UserUnit user={course.teacher}/>
                            </View>
                            <View style={{flex: 1}}>
                                <Text style={styles.locationText}>
                                    地点：{course.location}
                                </Text>
                            </View>
                        </View>
                        <Text style={styles.thirdLine}>
                            <Icon name={"calendar"} />
                            2019-07-12 ~ 2019-08-29
                        </Text>
                    </View>
                </View>
            </TouchableOpacity>
        )
    }

    render() {
        return (
            <Container>
                <Header>
                    <Body>
                    <Title>
                        我的课程
                    </Title>
                    </Body>
                    <Right>
                        <Button icon transparent onPress={() => {this.props.navigation.navigate("Login")}}>
                            <Icon name={"sign-out"} type={"FontAwesome"}/>
                        </Button>
                    </Right>
                </Header>
                <FlatList data={this.state.courses} renderItem={({item}) => this._createCourseCard(item)}/>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    return {
        accessToken: state.login.accessToken,
        userInfo: state.userInfo,
        authHeader: state.authHeader
    }
}

const mapDispatchToProps = dispatch => {
    return {
        setCourseInfo: (courseInfo) => dispatch({
            type: "SET_COURSE_INFO",
            courseInfo
        })
    }
};

const styles = StyleSheet.create({
    mainCard: {
        height: 100,
        borderStyle: "solid",
        borderWidth: 1,
        flexDirection: "row",
        marginTop: 10
    },
    imgView: {
        flex: 3,
        //backgroundColor: "yellow"
    },
    courseImg: {
        height: 100,
        width: 162,
        //backgroundColor: "blue"
    },
    textView: {
        flex: 4,
        flexDirection: "column"
    },
    titleText: {
        flex: 110,
        fontWeight: "bold",
        fontSize: 25
    },
    secondLine: {
        flex: 126,
        flexDirection: "row",
    },
    thirdLine: {
        flex: 50,
    },
    locationText: {
        marginTop: 10,
        fontSize: 16
    }
})

export default connect(mapStateToProps, mapDispatchToProps)(UserCourseList);
