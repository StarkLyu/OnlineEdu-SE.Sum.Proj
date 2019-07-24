import React from "react";
import { View, Text } from "react-native";
import { createSwitchNavigator, createAppContainer } from "react-navigation";
import LoginScreen from "../screens/LoginScreen"
import UserNav from "./UserNav"

// class HomeScreen extends React.Component {
//     render() {
//         return (
//             <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
//                 <Text>Home Screen</Text>
//             </View>
//         );
//     }
// }

const TopNav = createSwitchNavigator({
    Login: {
        screen: LoginScreen
    },
    Home: {
        screen: UserNav
    }
});

export default createAppContainer(TopNav);
