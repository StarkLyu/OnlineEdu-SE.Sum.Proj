<template>
<!--    单选题组件-->
    <div id="AssignmentSingle">
        <div v-for="single in SingleQuestion" :key="single.key">
            <p>
                {{single.title}}
            </p>
            <el-radio-group style="width: 80%" v-model="single.answer">
                <el-radio style="columns:4" v-for="choice in SingleQuestion[single.key-1].choices"
                          :key="choice.tag"
                          :label="choice.tag">
                    {{choice.tag+'. '+choice.content}}
                </el-radio>
            </el-radio-group>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AssignmentSingle",

        data(){
            return{
                radio:"1",
            }
        },

        props: {
            SingleQuestion: Array,
        },
    }
</script>

<style scoped>
</style>
