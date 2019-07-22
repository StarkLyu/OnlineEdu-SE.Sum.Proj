<template>
    <div class="resource-unit float-left">
        <el-link @click="getResource">
            <i :class="symbolIcon"></i>
            {{ resourceInfo.title }}
        </el-link>
    </div>
</template>

<script>
    export default {
        name: "ResourceUnit",
        props: {
            resourceInfo: {
                resourceType: String,
                url: String,
                title: String
            }
        },
        methods: {
            getResource: function () {
                if (this.resourceInfo.resourceType === "VIDEO") {
                    this.$emit("play-video", this.resourceInfo.url);
                }
            }
        },
        computed: {
            symbolIcon: function () {
                return "iconfont el-icon-my-" + this.resourceInfo.resourceType.toLowerCase();
            }
        }
    }
</script>

<style scoped>
    .resource-unit {
        width: 120px;
        padding-left: 20px;
        padding-right: 20px;
        text-align: center;
    }
</style>
