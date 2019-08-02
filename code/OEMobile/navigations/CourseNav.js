import React from 'react';
import { createDrawerNavigator } from "react-navigation";
import CourseHome from '../screens/CourseHome';
import CourseChapterNav from "./CourseChapterNav";
import CourseSignins from "../screens/CourseSignins"

const CourseNav = createDrawerNavigator({
    CourseHome,
    CourseChapterNav,
    CourseSignins
});

export default CourseNav;
