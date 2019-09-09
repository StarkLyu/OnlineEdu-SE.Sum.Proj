import React from "react";
import { createStackNavigator } from "react-navigation";
import CourseHome from "../screens/CourseHome";
import CourseAnnouncement from "../components/CourseAnnouncement"

export default createStackNavigator({
    CourseHome: {
        screen: CourseHome,
        navigationOptions: {
            header: null
        }
    },
    CourseAnnouncement
})
