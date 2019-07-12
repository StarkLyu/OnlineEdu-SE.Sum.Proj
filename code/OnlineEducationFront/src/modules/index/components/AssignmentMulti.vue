<template>
<!--    多选题组件-->
    <div id="AssignmentMulti">
        <div v-for="multi in MultiQuestion" :key="multi.key">
            <h4>
                {{multi.title}}
            </h4>
<!--            <el-radio-group style="width: 80%" v-model="single.answer">-->
<!--                <el-radio style="columns:4" v-for="choice in SingleQuestion[single.key-1].choices"-->
<!--                          :key="choice.tag"-->
<!--                          :label="choice.tag">-->
<!--                    {{choice.tag+'. '+choice.content}}-->
<!--                </el-radio>-->
<!--            </el-radio-group>-->
            <el-checkbox-group style="width: 80%" v-model="multi.answer">
                <el-checkbox style="columns:4"
                             v-for="choice in MultiQuestion[multi.key-1].choices"
                             :key="choice.tag"
                             :label="choice.tag">
                    {{choice.tag+'. '+choice.content}}
                </el-checkbox>
            </el-checkbox-group>
            <p>
                正确答案为：( {{multi.correctAnswer}} )
            </p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AssignmentMulti",

        props: {
            MultiQuestion: Array,
        },
    }
</script>

<style scoped>

</style>
