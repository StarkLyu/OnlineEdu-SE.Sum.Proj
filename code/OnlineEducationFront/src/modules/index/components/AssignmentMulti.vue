<template>
<!--    多选题组件-->
    <div id="AssignmentMulti">
        <h4>
            {{multi.content}}
        </h4>
        <el-checkbox-group style="width: 80%"  v-model="answerList" @change="changeAnswer">
            <el-checkbox
                    v-for="(value, key) in multi.options"
                    :key="key"
                    :label="key">
                {{key + '. ' + value}}
            </el-checkbox>
        </el-checkbox-group>
        <p style="color:red" v-if="showAnswer">
            正确答案为：( {{multi.correctAnswer}} )
        </p>
    </div>
</template>

<script>
    export default {
        name: "AssignmentMulti",

        props: {
            multi: Object,
            showAnswer: false
        },
        data() {
            return {
                answerList: []
            }
        },
        methods: {
            changeAnswer: function () {
                let answerLine = "";
                this.answerList.sort();
                for (let i of this.answerList) {
                    answerLine += i;
                }
                this.multi.myAnswer = answerLine;
            }
        }
    }
</script>

<style scoped>

</style>
