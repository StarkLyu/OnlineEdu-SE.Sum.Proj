<template>
<!--    单选题组件-->
    <div id="AssignmentSingle">
        <h4>
            {{single.title}}
        </h4>
        <el-radio-group style="width: 80%" v-model="single.answer">
            <el-radio style="columns:4"
                      v-for="choice in single.choices"
                      :key="choice.tag"
                      :label="choice.tag">
                {{choice.tag+'. '+choice.content}}
            </el-radio>
        </el-radio-group>
        <p>
            正确答案为：( {{single.correctAnswer}} )
        </p>
<!--        <div v-for="single in SingleQuestion" :key="single.key">-->
<!--            <div v-if="single.type==='single'">-->
<!--                <h4>-->
<!--                    {{single.title}}-->
<!--                </h4>-->
<!--                <el-radio-group style="width: 80%" v-model="single.answer">-->
<!--                    <el-radio style="columns:4"-->
<!--                              v-for="choice in SingleQuestion[single.key-1].choices"-->
<!--                              :key="choice.tag"-->
<!--                              :label="choice.tag">-->
<!--                        {{choice.tag+'. '+choice.content}}-->
<!--                    </el-radio>-->
<!--                </el-radio-group>-->
<!--                <el-button size="small">编辑</el-button>-->
<!--                <el-button size="small" type="danger">删除</el-button>-->
<!--                <p>-->
<!--                    正确答案为：( {{single.correctAnswer}} )-->
<!--                </p>-->
<!--            </div>-->
<!--        </div>-->
    </div>
</template>

<script>
    export default {
        name: "AssignmentSingle",

        props: {
            single: Array,
        },
    }
</script>

<style scoped>
</style>
