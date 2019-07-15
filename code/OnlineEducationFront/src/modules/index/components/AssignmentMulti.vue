<template>
<!--    多选题组件-->
    <div id="AssignmentMulti">
        <h4>
            {{multi.title}}
        </h4>
        <el-checkbox-group style="width: 80%" v-model="multi.answer">
            <el-checkbox style="columns:4"
                         v-for="choice in multi.choices"
                         :key="choice.tag"
                         :label="choice.tag">
                {{choice.tag+'. '+choice.content}}
            </el-checkbox>
        </el-checkbox-group>
        <p>
            正确答案为：( {{multi.correctAnswer}} )
        </p>
<!--        <div v-for="multi in MultiQuestion" :key="multi.key">-->
<!--            <div v-if="multi.type==='multi'">-->
<!--                <h4>-->
<!--                    {{multi.title}}-->
<!--                </h4>-->
<!--                <el-checkbox-group style="width: 80%" v-model="multi.answer">-->
<!--                    <el-checkbox style="columns:4"-->
<!--                                 v-for="choice in MultiQuestion[multi.key-1].choices"-->
<!--                                 :key="choice.tag"-->
<!--                                 :label="choice.tag">-->
<!--                        {{choice.tag+'. '+choice.content}}-->
<!--                    </el-checkbox>-->
<!--                </el-checkbox-group>-->
<!--                <p>-->
<!--                    正确答案为：( {{multi.correctAnswer}} )-->
<!--                </p>-->
<!--            </div>-->
<!--        </div>-->
    </div>
</template>

<script>
    export default {
        name: "AssignmentMulti",

        props: {
            multi: Array,
        },
    }
</script>

<style scoped>

</style>
