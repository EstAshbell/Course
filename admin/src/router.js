import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin"
import Welcome from "./views/admin/welcome"
import Chapter from "./views/admin/chapter"
import Section from "./views/admin/section"
import Course from "./views/admin/course"
import Category from "./views/admin/category"
import Teacher from "./views/admin/teacher.vue"

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/login",
    }, {
        path: "/login",
        component: Login
    }, {
        path: "/",
        name: "admin",
        component: Admin,
        children:[{
            path:"welcome", //子路由不能斜杠/开头
            name:"welcome",
            component:Welcome
        },{
            path:"business/course",
            name:"business/course",
            component:Course
        },{
            path:"business/chapter",
            name:"business/chapter",
            component:Chapter
        },{
            path:"business/section",
            name:"business/section",
            component:Section
        },{
            path:"business/category",
            name:"business/category",
            component:Category
        },{
            path:"business/teacher",
            name:"business/teacher",
            component:Teacher

        }]
    }]
})
