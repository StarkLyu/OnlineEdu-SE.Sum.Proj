import React, {Component} from 'react';
import { Header, Text, Icon, Right, Button } from 'native-base';
import { View } from "react-native";

class UserHeader extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Header>
                <View style={{flexDirection: "row"}}>
                    <View style={{flex: 1}}>
                        <Text> </Text>
                    </View>
                    <View style={{flex: 1}}>
                        <Text style={{textAlign: "center"}}>
                            {this.props.title}
                        </Text>
                    </View>
                    <View style={{flex: 1}}>
                        <Button icon transparent>
                            <Icon type={"FontAwesome"} name={"exit"} />
                        </Button>
                    </View>
                </View>
            </Header>
        );
    }
}

export default UserHeader;
