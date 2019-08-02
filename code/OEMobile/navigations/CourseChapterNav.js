import { createStackNavigator } from "react-navigation";
import CourseChapter from "../screens/CourseChapter";
import CourseSection from "../screens/CourseSection";
import CourseVideoPlay from "../screens/CourseVideoPlay";
import CoursePaper from "../screens/CoursePaper"

export default createStackNavigator({
    CourseChapter,
    CourseSection,
    CourseVideoPlay,
    CoursePaper
})
