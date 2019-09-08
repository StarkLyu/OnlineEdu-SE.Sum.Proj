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
                        username: "???",
                        avatarUrl: "http://202.120.40"
                    }
                }
            },
            userId: {
                default: -1
            },
            size: {
                default: "small"
            }
        },
        data() {
            return {
                userUnit: {
                    username: "",
                    avatarUrl: ""
                }
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
        },
        created() {
            if (this.userId === -1) {
                this.userUnit = this.user;
            }
            else {
                this.$http.request({
                    url: `/api/users/${this.userId}/avatar`,
                    method: "get",
                }).then((response) => {
                    this.userUnit = response.data;
                }).catch((error) => {
                    console.log(error);
                    this.userUnit = this.user;
                })
            }
        }
    }
</script>

<style scoped>
    .name-font {
        font-size: 14px;
    }
</style>
