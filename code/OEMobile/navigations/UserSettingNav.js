import React from "react";
import { createStackNavigator } from 'react-navigation';
import UserSetting from "../screens/UserSetting.js";
import UserAvatarSetting from "../screens/UserAvatarSetting";
import UserEmailSetting from "../screens/UserEmailSetting";
import UserPasswordSetting from "../screens/UserPasswordSetting";
import UserInfoSetting from "../screens/UserInfoSetting";

export default createStackNavigator({
    UserSetting: UserSetting,
    UserAvatarSetting: UserAvatarSetting,
    UserEmailSetting: UserEmailSetting,
    UserPasswordSetting: UserPasswordSetting,
    UserInfoSetting
})
