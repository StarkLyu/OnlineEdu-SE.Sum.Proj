import React, {Component} from 'react';
import { Container, H3, Form, Item, Label, Input, Button, Text } from 'native-base';

class UserEmailConfirm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            confirmCode: ""
        };
    }

    render() {
        return (
            <Container>
                <H3>请输入发往邮箱的二维码</H3>
                <Form>
                    <Item>
                        <Label>验证码</Label>
                        <Input onChangeText={(text) => this.setState({confirmCode: text})}/>
                    </Item>
                </Form>
                <Button full onPress={() => {this.props.sendConfirm(this.state.confirmCode)}}>
                    <Text>确认</Text>
                </Button>
            </Container>
        );
    }
}

export default UserEmailConfirm;
