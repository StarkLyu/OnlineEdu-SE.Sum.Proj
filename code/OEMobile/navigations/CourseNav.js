import React from 'react';
import { createDrawerNavigator } from "react-navigation";
import CourseHome from '../screens/CourseHome';
import CourseChapterNav from "./CourseChapterNav";

const CourseNav = createDrawerNavigator({
    CourseHome,
    CourseChapterNav
});

export default CourseNav;
