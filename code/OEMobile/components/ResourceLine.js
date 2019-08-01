import React, {Component} from 'react';
import { ListItem, Text, Icon, Left } from "native-base";
import RNFetchBlob from "rn-fetch-blob";

class ResourceLine extends Component {
    constructor(props) {
        super(props);
    }

    _getResource() {
        let resourceUrl = "http://202.120.40.8:30382/online-edu/static/" + this.props.resourceInfo.url;
        switch (this.props.resourceInfo.resourceType) {
            case "VIDEO":
                this.props.navigation.navigate("CourseVideoPlay",{
                    videoUrl: resourceUrl
                });
                break;
            default:
                RNFetchBlob.config({
                    fileCache: true,
                    //path: RNFetchBlob.fs.dirs.DocumentDir + "/oemobile"
                }).fetch('GET', resourceUrl,{

                }).then((res) => {
                    alert("资源保存在：" + res.path())
                }).catch((error) => {
                    alert(error)
                });
        }
    }

    _iconType() {
        switch (this.props.resourceInfo.resourceType) {
            case "VIDEO": return "file-video-o";
            case "PPT": return "file-powerpoint-o";
            case "PDF": return "file-pdf-o";
            default: return "file-text-o";
        }
    }

    render() {
        return (
            <ListItem onPress={() => this._getResource()}>
                <Left>
                    <Icon name={this._iconType()} type={"FontAwesome"} />
                    <Text>    {this.props.resourceInfo.title}</Text>
                </Left>
            </ListItem>
        );
    }
}

export default ResourceLine;
