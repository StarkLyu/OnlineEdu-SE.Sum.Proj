import React from 'react';
import { createBottomTabNavigator } from "react-navigation";
import { Icon } from "native-base";
import CourseHome from '../screens/CourseHome';
import CourseChapterNav from "./CourseChapterNav";
import CoursePaperNav from "./CoursePaperNav";
import CourseSignins from "../screens/CourseSignins";
import CourseHeader from "../components/CourseHeader";

const CourseNav = createBottomTabNavigator({
    CourseHome: {
        screen: CourseHome,
        navigationOptions: () => ({
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
    CoursePaperList: {
        screen: CoursePaperNav,
        navigationOptions: () => ({
            title: "作业",
            tabBarIcon: () => (
                <Icon type={"FontAwesome"} name={"pencil"} />
            ),
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
    },
    navigationOptions: () => ({
        header: ({ navigation }) => (
            <CourseHeader navigation={navigation} />
        )
    })
});

export default CourseNav;
