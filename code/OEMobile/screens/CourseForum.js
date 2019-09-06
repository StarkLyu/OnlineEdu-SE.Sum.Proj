import React, {Component} from 'react';
import { FlatList } from 'react-native';
import { connect } from 'react-redux';
import { Container, View, ListItem, Text } from "native-base";
import CourseForumTopic from '../components/CourseForumTopic';

class CourseForum extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sectionForum: []
        }
    }

    initForum = () => {
        global.showLoading("加载论坛中");
        this.$axios.request({
            url: `/api/courses/${this.props.courseId}/forums`,
            method: "get",
            headers: this.props.authHeader
        }).then((response) => {
            global.showLoading("论坛初始化中");
            let sectionForum = [];
            for (let sec of this.props.sectionList) {
                sectionForum.push({
                    secNo: sec.secNo,
                    title: sec.title
                })
            }
            for (let i in sectionForum) {
                sectionForum[i].topics = []
            }
            let forum = response.data;
            forum.sort((a,b) => {
                if (a.secNo === b.secNo) {
                    if (a.path < b.path) return -1;
                    else return 1;
                }
                else {
                    if (a.secNo < b.secNo) return -1;
                    else return 1;
                }
            });
            console.log(forum);
            let scanSecNum = 0;
            let secLength = sectionForum.length;
            let forumStack = [{}];
            let pathLevel = 1;
            for (let i of forum) {
                i.responses = [];
                let pathArr = i.path.split("/");
                let pathLength = pathArr.length;
                if (pathLength === 2) {
                    pathLevel = 2;
                    forumStack.shift();
                    forumStack.unshift(i);
                    for (; scanSecNum < secLength; ++scanSecNum) {
                        if (sectionForum[scanSecNum].secNo === i.secNo) {
                            sectionForum[scanSecNum].topics.push(i);
                            break;
                        }
                    }
                }
                else {
                    if (pathLength <= pathLevel) {
                        let popNum = pathLevel - pathLength + 1;
                        for (let j = 0; j < popNum; ++j) {
                            forumStack.shift();
                        }
                        pathLevel = popNum;
                    }
                    i.responseTo = forumStack[0].userId;
                    forumStack[0].responses.push(i);
                    forumStack.unshift(i);
                    pathLevel = pathLength;
                }
            }
            console.log(sectionForum);
            this.setState({
                sectionForum
            });
            global.cancelLoading();
        }).catch((error) => {
            console.log(error.response);
            alert(error);
        })
    };

    componentDidMount(): void {
        this.initForum();
    }

    render() {
        return (
            <Container>
                <FlatList
                    data={this.state.sectionForum}
                    renderItem={({ item }) => {
                        return (
                            <View>
                                <ListItem itemDivider>
                                    <Text>{item.title}</Text>
                                </ListItem>
                                <FlatList data={item.topics} renderItem={({ item }) => (<CourseForumTopic forumTopic={item} navigation={this.props.navigation}/>)}/>
                            </View>
                        )
                    }}
                />
            </Container>
        );
    }
}

function mapStateToProps(state) {
    return {
        courseId: state.courseInfo.id,
        authHeader: state.login.authHeader,
        sectionList: state.courseInfo.sectionList
    }
}

export default connect(mapStateToProps, null)(CourseForum);
