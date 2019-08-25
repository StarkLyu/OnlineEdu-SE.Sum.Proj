import React from 'react';
import { createBottomTabNavigator } from "react-navigation";
import { Icon } from "native-base";
import CourseHome from '../screens/CourseHome';
import CourseChapterNav from "./CourseChapterNav";
import CourseSignins from "../screens/CourseSignins"

const CourseNav = createBottomTabNavigator({
    CourseHome: {
        screen: CourseHome,
        navigationOptions: ({ navigation }) => ({
            title: "首页",
            tabBarIcon: () => (
                <Icon type={"FontAwesome"} name={"home"} />
            )
        })
    },
    CourseChapterNav: {
        screen: CourseChapterNav,
        navigationOptions: () => ({
            title: "章节",
            tabBarIcon: () => (
                <Icon type={"FontAwesome"} name={"list"} />
            )
        })
    },
    CourseSignins: {
        screen: CourseSignins,
        navigationOptions: () => ({
            title: "签到",
            tabBarIcon: () => (
                <Icon type={"Ionicons"} name={"pin"} />
            )
        })
    }
},{
    tabBarOptions: {
        activeBackgroundColor: "#eeeeee",
        activeTintColor: "blue",
        showIcon: true,
        showLabel: true
    }
});

export default CourseNav;
