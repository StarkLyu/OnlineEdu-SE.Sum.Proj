import React from "react";
import { createStackNavigator } from "react-navigation";
import CourseChapter from "../screens/CourseChapter";
import CourseSection from "../screens/CourseSection";
import CourseVideoPlay from "../screens/CourseVideoPlay";
import CoursePaper from "../screens/CoursePaper"
import CourseHeader from "../components/CourseHeader";

export default createStackNavigator({
    CourseChapter: {
        screen: CourseChapter,
        navigationOptions: ({ navigation }) => ({
            header: (
                <CourseHeader navigation={navigation}/>
            )
        })
    },
    CourseSection: {
        screen: CourseSection,
        navigationOptions: ({ navigation }) => ({
            title: navigation.getParam("sectionTitle", "未知章节名")
        })
    },
    CourseVideoPlay,
    CoursePaper
})
