import React, {Component} from 'react';
import {Image, Modal, TouchableOpacity} from 'react-native';
import { Card, CardItem, Left, Body, Right, Text, Button, View, Header, Icon, Footer } from "native-base";
import UserUnit from '../components/UserUnit';
import CourseAddTopic from '../screens/CourseAddTopic';
//import ImageViewer from "react-native-image-zoom-viewer";
import RNFetchBlob from "rn-fetch-blob";
import ImageViewer from "./ImageViewer";

class CourseForumTopic extends Component {
    constructor(props) {
        super(props);
        let imgLength = this.props.forumTopic.imageUrls.length;
        let initSize = new Array(imgLength);
        initSize.fill(1);
        let imgList = [];
        for (let i of this.props.forumTopic.imageUrls) {
            imgList.push({
                url: `http://202.120.40.8:30382/online-edu/static/${i}`
            })
        }
        console.log(imgList);
        this.state = {
            imgSize: initSize,
            showViewer: false,
            currentImg: 0,
            imgList
        }
    }

    saveImage = (url) => {
        RNFetchBlob.config({
            path: `${RNFetchBlob.fs.dirs.PictureDir}/oeMobile`
        }).fetch('GET', url, {

        }).progress((received, total) => {
            global.showLoading(`图片保存中：${Math.trunc(received/total*100)}%`);
        }).then(() => {
            global.cancelLoading();
            this.$toast.successToast("图片保存成功！");
        }).catch((error) => {
            global.cancelLoading();
            this.$toast.errorToast("出错啦！" + error);
        })
    };

    render() {
        let topic = this.props.forumTopic;
        let imgWidth = this.$window.width * 0.9;
        let imgSize = this.state.imgSize;

        return (
            <View>
                <Card>
                    <CardItem header>
                        <Body>
                        <Text>{topic.title}</Text>
                        </Body>
                        <Right>
                            <Text note>{topic.createAt}</Text>
                        </Right>
                    </CardItem>
                    <CardItem>
                        <Body>
                        <Text>{topic.content}</Text>
                        {
                            topic.imageUrls.map((item, index) => {
                                return (<TouchableOpacity onPress={() => {
                                    this.refs.imageViewer.showViewer(index);
                                }}>
                                    <Image
                                        key={index}
                                        source={{uri: `http://202.120.40.8:30382/online-edu/static/${item}`}}
                                        style={{width: imgWidth, height: imgWidth * imgSize[index]}}
                                        onLoadStart={() => {
                                            Image.getSize(`http://202.120.40.8:30382/online-edu/static/${item}`, (width, height) => {
                                                let temp = this.state.imgSize;
                                                temp[index] = height/width;
                                                this.setState({
                                                    imgSize: temp
                                                })
                                            }, () => {
                                                console.log("fuck");
                                            })}
                                        }
                                    />
                                </TouchableOpacity>)
                            })
                        }
                        </Body>
                    </CardItem>
                    <CardItem>
                        <Left>
                            <UserUnit user={topic.userId}/>
                        </Left>
                        <Right>
                            <Button>
                                <Text>查看回复</Text>
                            </Button>
                            <Button onPress={() => {this.props.navigation.navigate("CourseAddTopic")}}>
                                <Text>添加回复</Text>
                            </Button>
                        </Right>
                    </CardItem>
                </Card>
                <ImageViewer ref={"imageViewer"} imgList={this.state.imgList}/>
                {/*<Modal visible={this.state.showViewer} transparent animationType={"fade"}>*/}
                    {/*<ImageViewer*/}
                        {/*imageUrls={this.state.imgList}*/}
                        {/*index={this.state.currentImg}*/}
                        {/*enableSwipeDown={true}*/}
                        {/*onClick={() => {*/}
                            {/*console.log(this.state.imgList);*/}
                            {/*this.setState({showViewer: false});*/}
                        {/*}}*/}
                        {/*menuContext={{*/}
                            {/*saveToLocal: "保存图片",*/}
                            {/*cancel: "取消"*/}
                        {/*}}*/}
                        {/*onSave={(url) => {*/}
                            {/*this.saveImage(url)*/}
                        {/*}}*/}
                        {/*// renderHeader={() => (*/}
                        {/*//     <Header transparent style={{height: 0, position: "absolute", top: 0, zIndex: 0}}>*/}
                        {/*//         <Right>*/}
                        {/*//             <Button transparent onPress={() => {this.setState({showViewer: false})}}>*/}
                        {/*//                 <Icon name={"cancel"} type={"FontAwesome"} style={{color: "white"}} />*/}
                        {/*//             </Button>*/}
                        {/*//         </Right>*/}
                        {/*//     </Header>*/}
                        {/*// )}*/}
                    {/*/>*/}
                {/*</Modal>*/}
            </View>
        );
    }
}

export default CourseForumTopic;
