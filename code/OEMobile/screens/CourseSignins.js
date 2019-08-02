import React, {Component} from 'react';
import { FlatList, PermissionsAndroid, ScrollView } from "react-native";
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
        this.initSignins();
    }

    initSignins = () => {
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

    async requestLocationPermission() {
        try {
            const granted = await PermissionsAndroid.request(
                PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
                {
                    title: '申请访问位置权限',
                    message: '签到功能需要位置权限才可以使用',
                    //buttonNeutral: 'Ask Me Later',
                    buttonNegative: '取消',
                    buttonPositive: '访问',
                },
            );
            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log('Location permission granted');
            } else {
                console.log('Camera permission denied');
            }
        } catch (err) {
            console.warn(err);
        }
    }

    componentWillMount(): void {
        this.requestLocationPermission();
    }

    startSignIn = (signInNo) => {
        Geolocation.getCurrentPosition(
            (position => {
                this.$axios.request({
                    url: "/api/users/" + this.props.userId + "/signIns",
                    method: "post",
                    headers: {
                        Authorization: "Bearer " + this.props.accessToken
                    },
                    data: {
                        courseId: this.props.courseId,
                        signInNo: signInNo,
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude
                    }
                }).then(() => {
                    alert("签到成功！");
                    this.initSignins();
                }).catch((error) => {
                    alert(error.response.data);
                    console.log(error.response)
                });
                console.log(signInNo);
                console.log(position);
            }),
            (error => {
                console.log(error.code, error.message)
            }),
            { enableHighAccuracy: true, timeout: 15000, maximumAge: 10000 }
        )

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
                        <Button icon onPress={() => {this.startSignIn(signIn.signIn.signInPrimaryKey.signInNo)}}>
                            <Icon name={"sign-in"} type={"FontAwesome"}/>
                        </Button>
                    </View>
                </View>
            </ListItem>
        )
    }

    render() {
        return (
            <ScrollView>
                <CourseHeader openDrawer={this.showDrawer} navigation={this.props.navigation}/>
                <List>
                    <FlatList data={this.state.signIns} renderItem={({item}) => this._signInLine(item)}/>
                </List>
            </ScrollView>
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
