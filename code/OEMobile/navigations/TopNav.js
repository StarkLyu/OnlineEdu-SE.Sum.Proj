import React from "react";
import { createSwitchNavigator, createAppContainer } from "react-navigation";
import LoginScreen from "../screens/LoginScreen";
import UserNav from "./UserNav";
import CourseNav from "./CourseNav"


const TopNav = createSwitchNavigator({
    Login: {
        screen: LoginScreen
    },
    Home: {
        screen: UserNav
    },
    Course: {
        screen: CourseNav
    }
});

export default createAppContainer(TopNav);
