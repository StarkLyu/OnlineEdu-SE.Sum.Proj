import React from "react";
import { List, ListItem, Left, Right, View, Text } from 'native-base';
import Icon from 'react-native-vector-icons/FontAwesome';
import ImagePicker from 'react-native-image-picker';

class UserSetting extends React.Component {
    constructor(props) {
        super(props);
        //this.onSelectMenu = this.onSelectMenu.bind(this);
    }

    imagePickerOptions = {
        title: "选择新头像",
        cancelButtonTitle: "取消",
        takePhotoButtonTitle: "打开相机",
        chooseFromLibraryButtonTitle: "从相册选取"
    };

    selectedNewAvatar = (response) => {
        alert("emmm");
        console.log(response);
    };

    render() {
        const onSelectMenu = (target) => {
            this.props.navigation.navigate(target)
        };

        return (
            <View>
                <List>
                    <ListItem button onPress={() => {ImagePicker.showImagePicker(this.imagePickerOptions, this.selectedNewAvatar)}}>
                        <Left>
                            <Text>修改头像</Text>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserPasswordSetting")}}>
                        <Left>
                            <Text>修改密码</Text>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserEmailSetting")}}>
                        <Left>
                            <Text>修改邮箱</Text>
                        </Left>
                        <Right>
                            <Icon name={"chevron-right"} />
                        </Right>
                    </ListItem>
                    <ListItem button onPress={() => {onSelectMenu("UserInfoSetting")}}>
                        <Left>
                            <Text>修改基本信息</Text>
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
