<template>
    <div>
        <el-avatar :size="avatarSize" :src="user.avatarUrl"></el-avatar>
        <span class="name-font">{{ user.username }}</span>
    </div>
</template>

<script>
    export default {
        name: "UserUnit",
        props: {
            user: {
                default() {
                    return {
                        username: "starklyu",
                        avatarUrl: "http://202.120.40"
                    }
                }
            },
            size: {
                default: "small"
            }
        },
        computed: {
            avatarSize: function () {
                switch (this.size) {
                    case "small": return 18;
                    case "middle": return 25;
                    default: return 18
                }
            }
        }
    }
</script>

<style scoped>
    .name-font {
        font-size: 14px;
    }
</style>
