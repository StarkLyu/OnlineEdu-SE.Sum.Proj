import React, {Component} from 'react';
import { FlatList, Alert, Image, Dimensions } from 'react-native';
import { Container, Content, Form, Item, Text, Input, Textarea, Label, Button, View } from "native-base";
import ImagePicker from 'react-native-image-crop-picker';

class CourseAddTopic extends Component {
    constructor(props) {
        super(props);
        this.state = {
            images: [],
            test: false
        }
        //this.images = [];
    }

    addImage = (image) => {
        let temp = this.state.images;
        temp.push(image);
        this.setState({
            images: temp,
            test: true
        });
        console.log(this.state.images.length);
    };

    chooseImages = () => {
        Alert.alert(
            "添加图片",
            "请选择添加图片方式",
            [
                {
                    text: "打开相机",
                    onPress: () => {
                        ImagePicker.openCamera({
                            includeBase64: true
                        }).then((image) => {
                            console.log(typeof image);
                            this.addImage(image);
                        })
                    }
                },
                {
                    text: "从相册中选取",
                    onPress: () => {
                        ImagePicker.openPicker({
                            multiple: true,
                            includeBase64: true
                        }).then((images) => {
                            console.log(images[0].mime);
                            console.log(this.state.images);
                            for (let i of images) {
                                this.addImage(i);
                            }
                        })
                    }
                }
            ])
    };

    renderImage = (image) => {
        return <Image source={{uri: `data:${image.mime};base64,${image.data}`}} />
    };

    render() {
        let imgWidth = this.$window.width * 0.9;

        return (
            <Container>
                <Content>
                    <Form>
                        <Item stackedLabel>
                            <Label>标题</Label>
                            <Input/>
                        </Item>
                        <Textarea rowSpan={6} placeholder={"在此添加内容"}/>
                    </Form>
                    <View>
                        {
                            this.state.images.map((item, index) => {
                                return (
                                    <Image
                                        key={index}
                                        source={{uri: `data:${item.mime};base64,${item.data}`}}
                                        style={{
                                            width: imgWidth,
                                            height: imgWidth / item.width * item.height,
                                            marginLeft: "auto",
                                            marginRight: "auto"
                                        }}
                                    />
                                )
                            })
                        }
                    </View>
                    <Button onPress={() => {this.chooseImages()}}>
                        <Text>添加图片</Text>
                    </Button>
                </Content>
            </Container>
        );
    }
}

export default CourseAddTopic;
