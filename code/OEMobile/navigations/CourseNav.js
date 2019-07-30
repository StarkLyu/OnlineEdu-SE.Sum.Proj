import React from 'react';
import { createDrawerNavigator } from "react-navigation";
import CourseHome from '../screens/CourseHome';
import CourseChapter from '../screens/CourseChapter';

const CourseNav = createDrawerNavigator({
    CourseHome,
    CourseChapter
});

export default CourseNav;
