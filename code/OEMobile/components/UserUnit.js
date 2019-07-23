import React, { Component } from "react";
import { Text, View, StyleSheet } from "react-native";
import { Thumbnail } from 'native-base';

const styles = StyleSheet.create({
    mainView: {
        flexDirection: "row"
    },
    userName: {
        fontSize: 16,
        bottom: 0,
        marginTop: 10
    }
})

class UserUnit extends Component {
    render() {
        return (
            <View style={styles.mainView}>
                <Thumbnail small source={{uri:"http://202.120.40.8:30382/online-edu/static/" + this.props.user.avatarUrl}}/>
                <Text style={styles.userName}>{this.props.user.username}</Text>
            </View>
        )
    }
}

export default UserUnit;
