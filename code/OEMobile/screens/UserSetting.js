import React from "react";
import { View, Text } from "react-native";
import { List, ListItem, Left, Right, H3 } from 'native-base';
import Icon from 'react-native-vector-icons/FontAwesome'

class UserSetting extends React.Component {
    constructor(props) {
        super(props);
        //this.onSelectMenu = this.onSelectMenu.bind(this);
    }

    render() {
        const onSelectMenu = (target) => {
            this.props.navigation.navigate(target)
        }

        return (
            <View>
                <List>
                    <ListItem button onPress={() => {onSelectMenu("UserAvatarSetting")}}>
                        <Left>
                            <H3>修改头像</H3>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserPasswordSetting")}}>
                        <Left>
                            <H3>修改密码</H3>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserEmailSetting")}}>
                        <Left>
                            <H3>修改邮箱</H3>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserInfoSetting")}}>
                        <Left>
                            <H3>修改基本信息</H3>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                </List>
            </View>
        );
    }
}

export default UserSetting;
