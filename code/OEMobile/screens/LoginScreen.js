import React, { Component } from 'react';
import { Container, Header, Content, Item, Input, Icon, Button , Text} from 'native-base';

export default class LoginScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        };
        this.onLogin = this.onLogin.bind(this);
    }

    onLogin() {
        fetch("http://202.120.40.8:30382/online-edu/api/auth/signin",{
            method: "POST",
            body: this.state
        }).then(() => {
            alert("登录成功！");
            this.props.navigation.navigate("Home");
        })
    }

    render() {
        return (
            <Container>
                <Content>
                    <Item>
                        <Icon type={'FontAwesome'} name='user' />
                        <Input placeholder='Icon Textbox' onChangeText={(text) => this.setState({username: text})}/>
                    </Item>
                    <Item>
                        <Input
                            placeholder='Icon Alignment in Textbox'
                            onChangeText={(text) => this.setState({password: text})}
                            secureTextEntry = {true}/>
                        <Icon active name='swap' />
                    </Item>
                    <Button full onPress={this.onLogin}>
                        <Text>登录</Text>
                    </Button>
                </Content>
            </Container>
        );
    }
}
