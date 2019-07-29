<template>
<!--    单选题组件-->
    <div id="AssignmentSingle">
        <h4>
            {{single.content}}
        </h4>
        <img v-for="(img, index) in single.images" :key="index" :src="img">
        <el-radio-group style="width: 80%" v-model="single.myAnswer">
            <el-radio
                    v-for="(value, key) in single.options"
                    :key="key"
                    :label="key">
                {{key + '. ' + value}}
            </el-radio>
        </el-radio-group>
        <p style="color:red" v-if="showAnswer">
            正确答案为：( {{single.correctAnswer}} )
        </p>
    </div>
</template>

<script>
    export default {
        name: "AssignmentSingle",

        props: {
            single: Object,
            showAnswer: {
                default: false,
            },
        },
    }
</script>

<style scoped>
</style>
