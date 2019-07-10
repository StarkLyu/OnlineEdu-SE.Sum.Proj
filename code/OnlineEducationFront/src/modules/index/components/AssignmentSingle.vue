<template>
<!--    单选题组件-->
    <div id="AssignmentSingle">
        <div v-for="single in SingleQuestion" :key="single.key">
            <p>
                {{single.title}}
            </p>
            <el-radio-group style="columns:1; width: 80%" v-model="SingleQuestion[single.key-1].choices">
                <el-radio v-for="choice in SingleQuestion[single.key-1].choices" :key="choice.tag">
                    {{choice.tag+'. '+choice.content}}
                </el-radio>
            </el-radio-group>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AssignmentSingle",

        // data(){
        //     return{
        //         SingleQuestion:[
        //             {
        //                 key:1,
        //                 title:"第一题：这是一道单选题，请选择下列选项。",
        //                 choices:[
        //                     {
        //                         tag:"A",
        //                         content:"这是第一个选项",
        //                     },
        //                     {
        //                         tag:"B",
        //                         content:"这是第二个选项",
        //                     },
        //                     {
        //                         tag:"C",
        //                         content:"这是第三个选项",
        //                     },
        //                     {
        //                         tag:"D",
        //                         content:"这是第四个选项",
        //                     },
        //                 ]
        //             }
        //         ]
        //     }
        // },

        props: {
            SingleQuestion: Array,
        },
    }
</script>

<style scoped>
    .el-radio-group
    { .is-active {
        display: block;
    }
    }
</style>
