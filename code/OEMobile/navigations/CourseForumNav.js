import React from "react";
import { createStackNavigator } from "react-navigation";
import CourseForum from "../screens/CourseForum";
import CourseAddTopic from "../screens/CourseAddTopic";

export default createStackNavigator({
    CourseForum: {
        screen: CourseForum
    },
    CourseAddTopic: {
        screen: CourseAddTopic

    }
})
