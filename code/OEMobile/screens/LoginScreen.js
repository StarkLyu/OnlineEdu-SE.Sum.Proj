import React, { Component } from 'react';
import { Container, Content, Item, Icon, Input, Button , Text} from 'native-base';
import { connect } from 'react-redux';
//import Icon from "react-native-vector-icons/FontAwesome";

class LoginScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        };
        this.onLogin = this.onLogin.bind(this);
    }

    onLogin() {
        this.$axios.request({
            url: "/api/auth/signin",
            method: "post",
            data: {
                username: this.state.username,
                password: this.state.password
            }
        }).then((response) => {
            alert("登录成功！");
            this.props.setLogin(this.state.username, response.data.accessToken);
            console.log(this.props.accessToken);
            this.$axios.request({
                url: "/api/users/info",
                method: 'get',
                headers: {
                    "Authorization": "Bearer " + this.props.accessToken
                }
            }).then((infoResponse) => {
                this.props.setUserInfo(infoResponse.data);
                console.log(this.props.userInfo);
                this.props.navigation.navigate("Home");
            }).catch((error) => {
                console.log(error.response);
                if (error.response.data.status === 401) {
                    alert("获取用户信息出错");
                }
                else {
                    alert(error);
                }
            });
        }).catch((error) => {
            alert(error);
            console.log(error);
        })
    }

    render() {
        return (
            <Container>
                <Content>
                    <Item>
                        <Icon type={"FontAwesome"} name='user' />
                        <Input placeholder='用户名' onChangeText={(text) => this.setState({username: text})}/>
                    </Item>
                    <Item>
                        <Icon name='lock' />
                        <Input
                            placeholder='密码'
                            onChangeText={(text) => this.setState({password: text})}
                            secureTextEntry = {true}/>
                    </Item>
                    <Button full onPress={this.onLogin}>
                        <Text>登录</Text>
                    </Button>
                </Content>
            </Container>
        );
    }
}

function mapStateToProps(state) {
    //const { login } = state;
    return {
        accessToken: state.login.accessToken,
        userInfo: state.userInfo
    }
}



const mapDispatchToProps = dispatch => {
    return {
        setLogin: (username, accessToken) => dispatch({
            type: 'SET_LOGIN',
            login: {
                username: username,
                accessToken: accessToken,
                loginStatus: true
            }
        }),
        setUserInfo: (userInfo) => dispatch({
            type: "SET_USERINFO",
            userInfo
        })
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginScreen);
