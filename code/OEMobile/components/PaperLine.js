import React, {Component} from 'react';
import { View } from "react-native";
import { ListItem, Left, Icon, Text } from "native-base";

class PaperLine extends Component {
    constructor(props) {
        super(props);
    }

    paperDDL() {
        return this.props.paper.end.substr(0,10) + " " + this.props.paper.end.substr(11,8)
    }

    render() {
        return (
            <ListItem>
                <Left>
                    <Icon type={"FontAwesome"} name={"file-text-o"} />
                    <Text>   {this.props.paper.title}</Text>
                </Left>
                <View style={{marginRight: 0, maxWidth: 200}}>
                    <Text>DDL: {this.paperDDL()}</Text>
                </View>
            </ListItem>
        );
    }
}

export default PaperLine;
