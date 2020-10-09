<template>
    <div>
        <h1>Welcome!!</h1>
    </div>
</template>

<script>
    export default {
        name: "welcome",
        mounted: function() {  //初始化加载
            //siderbar激活样式方法一
            // this.$parent.activeSidebar("welcome-sidebar");
        },
        methods: {
        }
    }
</script>