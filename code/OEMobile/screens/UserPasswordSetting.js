import React, {Component} from 'react';
import {Button, Container, Form, Input, Item, Label, Text} from 'native-base';
import UserEmailConfirm from '../components/UserEmailConfirm';
import {connect} from 'react-redux';

class UserPasswordSetting extends Component {
    constructor(props) {
        super(props);
        this.state = {
            oldPass: "",
            newPass: "",
            confirmPass: "",
            showConfirm: false
        }
    }

    changeRequest = () => {
        global.showLoading("获取验证码中");
        this.$axios.request({
            url: this.props.userUrl + "password",
            method: "patch",
            data: {
                password: this.state.newPass
            },
            headers: {
                "Authorization": "Bearer " + this.props.accessToken
            }
        }).then(() => {
            this.setState({showConfirm: true})
        }).catch((error) => {
            alert("出错啦！");
            console.log(error.response);
        })
    };

    sendConfirm = (confirmCode) => {
        this.$axios.request({
            url: this.props.userUrl + "password/confirm",
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
            return (<UserEmailConfirm sendConfirm={this.sendConfirm} />)
        }
        else {
            return (
                <Container>
                    <Form>
                        <Item stackedLabel>
                            <Label>旧密码</Label>
                            <Input onChangeText={(text) => this.setState({oldPass: text})}/>
                        </Item>
                        <Item stackedLabel>
                            <Label>新密码</Label>
                            <Input onChangeText={(text) => this.setState({newPass: text})}/>
                        </Item>
                        <Item stackedLabel>
                            <Label>确认密码</Label>
                            <Input onChangeText={(text) => this.setState({confirmPass: text})}/>
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

export default connect(mapStateToProps, mapDispatchToProps)(UserPasswordSetting);
