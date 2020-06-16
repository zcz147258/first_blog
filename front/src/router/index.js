import Vue from "vue";
import Router from "vue-router";
import store from "@/store";
import { Message } from "element-ui";
//导入权限
import { GetAuthority } from "../utils/api";
//引入所有的数组
// import AuthorList from "./component";
Vue.use(Router);
//导入store的路由
//路由规则
const blogRule = {path: "/blogsub",name: "Editor",component: () => import("@/view/dashboard/blogsub")}
const noteadminRule = {path: "/noteadmin",name: "Music",component: () => import("@/view/dashboard/noteadmin")}
const userRule = {path: "/useradmin",name: "Self",component: () => import("@/view/dashboard/useradmin")}
const prctureRule = {path: "/picture",name: "Display",component: () => import("@/view/dashboard/picture")}
const selfinfoRule = {path: "/selfinfo",name: "Blog",component: () => import("@/view/dashboard/selfinfo")}
//路由映射规则
const ruleMappming = {
  'blogsub' : blogRule,
  'noteadmin': noteadminRule,
  'useradmin':userRule,
  'picture':prctureRule,
  'selfinfo':selfinfoRule
}
const router = new Router({
  // mode:"history",
  routes: [
    {
      path: "/login",
      name: "Login",
      component: () => import("@/view/Login")
    },
    {
      path: "/signon",
      name: "Signon",
      component: () => import("@/view/signon")
    },
    {
        path: "/",
        name:"Myblog",
        component:() => import("@/view/Myblog"),
        children:[
          {
            path: "/blogindex",
            name: "blogindex",
            component: () => import("@/view/indexitem/blogindex")
          },
          {
            path: "/notebook",
            name: "notebook",
            component: () => import("@/view/indexitem/notebook")
          },
          {
            path: "/noteall",
            name: "noteall",
            component: () => import("@/view/indexitem/noteall")
          },
          {
            path: "/photo",
            name: "photo",
            component: () => import("@/view/indexitem/photo")
          }
        ]
    },
    {
      path: "/dashboard",
      name: "Dashbord",
      component: () => import("@/view/dashboard"),
      children: [
        
        // {
        //   path: "/playvideo/:name",
        //   name: "PlayVideo",
        //   component: () => import("@/view/dashboard/playvideo")
        // }
      ]
    },
    //匹配所有不存在就跳转
    {
      path:"*",
      component: () => import("@/view/Notfound")
    }
  ]
});
//动态路由添加
export function initDynamicRoutes(){
  //获取当前的路由
  const currentRoute = router.options.routes
  const RoleList = store.state.routerList
  RoleList.forEach(item => {
    //如果有二级权限需要进行再次循环遍历
    const temp = ruleMappming[item.routerpath]
    currentRoute[3].children.push(temp)
  });
  router.addRoutes(currentRoute)
}
var checked = false;
router.beforeEach((to, from, next) => {
  //const isLogin = store.state.token ? true : false;

  // if (to.path == "/" || to.path == "/word") {
  //   next();
  // } else {
  //   if (store.state.token == "") {
  //     Message({
  //       message: "没有token请重新登录",
  //       type: "err"
  //     });
  //     next("/");
  //   } else {
  //检测权限控制
  // if (!checked) {
  //   GetAuthority({
  //     username: "mikasa"
  //   }).then(res => {
  //     console.log(res);
  //   });
  //   checked = true;
  // }
  next();
  // }
  // }
});
export default router;
