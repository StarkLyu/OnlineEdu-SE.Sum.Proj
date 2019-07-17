<template>
    <span>{{ dateRange }}</span>
</template>

<script>
    export default {
        name: "DateRangeFormat",
        props: {
            start: String,
            end: String
        },
        computed: {
            dateRange: function () {
                return this.start.substr(0,10) + " ~ " + this.end.substr(0,10)
            }
        }
    }
</script>

<style scoped>

</style>
