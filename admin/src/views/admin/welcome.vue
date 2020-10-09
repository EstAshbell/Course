<template>
    <div>
        <h1>Welcome!!</h1>
    </div>
</template>

<script>
    export default {
        name: 'welcome',
        mounted:function() { //初始化加载
            this.$parent.activeSidebar("welcome-sidebar");
        },
        methods:{
        }
    }
</script>