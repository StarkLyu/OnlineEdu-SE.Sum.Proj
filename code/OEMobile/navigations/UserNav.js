import React from "react";
import { createBottomTabNavigator } from 'react-navigation';
import UserCourseList from "../screens/UserCourseList";
import UserSettingNav from "./UserSettingNav"

export default createBottomTabNavigator({
    UserCourseList: UserCourseList,
    UserSettingNav: UserSettingNav
})
