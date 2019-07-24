import React, { Component } from "react";
import { Text } from "react-native";
import { Container, Button, Form, Item, Input, Label } from 'native-base';
import { connect } from "react-redux";
import UserEmailConfirm from '../components/UserEmailConfirm';

class UserEmailSetting extends Component {
    constructor(props) {
        super(props);
        this.state = {
            newEmail: this.props.userEmail,
            showConfirm: false,
        }
    }

    changeRequest = () => {
        this.$axios.request({
            url: this.props.userUrl + "email",
            method: "patch",
            data: {
                email: this.state.newEmail
            },
            headers: {
                "Authorization": "Bearer " + this.props.accessToken
            }
        }).then(() => {
            alert("有验证码啦！");
            this.setState({showConfirm: true})
        }).catch((error) => {
            alert("出错啦！");
            console.log(error.response);
        })
    };

    sendConfirm = (confirmCode) => {
        this.$axios.request({
            url: this.props.userUrl + "email/confirm",
            method: "get",
            params: {
                verificationToken: confirmCode
            },
            headers: {
                "Authorization": "Bearer " + this.props.accessToken
            },
            withCredentials: true
        }).then(() => {
            alert("修改成功！");
            this.setState({showConfirm: false});
        }).catch((error) => {
            console.log(error.response);
            alert(error);
        })
    };

    render() {
        if (this.state.showConfirm) {
            return (
                <UserEmailConfirm sendConfirm={this.sendConfirm}/>
            )
        }
        else {
            return (
                <Container>
                    <Form>
                        <Item stackedLabel>
                            <Label>修改邮箱</Label>
                            <Input defaultValue={this.state.newEmail} onChangeText={(text) => this.setState({newEmail: text})}/>
                        </Item>
                    </Form>
                    <Button full onPress={() => {this.changeRequest()}}>
                        <Text>提交修改</Text>
                    </Button>
                </Container>
            );
        }
    }
}

function mapStateToProps(state) {
    return {
        accessToken: state.login.accessToken,
        userEmail: state.userInfo.email,
        userUrl: "/api/users/" + state.userInfo.id + "/"
    }
}



const mapDispatchToProps = dispatch => {
    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(UserEmailSetting);
