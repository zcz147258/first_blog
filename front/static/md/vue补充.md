# 一.导航守卫

```js
import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Page from '@/components/Page'
Vue.use(Router)
const router = new Router({
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      meta:{
        title:'首页',
        allowBack: true
      }
    },
    {
      path: '/page',
      name: 'Page',
      component: Page,
      title:'页面',
      meta:{
        title:'首页',
        allowBack: true
      }
      //内部导航
      // beforeEnter: (to, from, next) => {
      //   let token = false;
      //   console.log("进入局部首位")
      //    if (token) {
      //     next();
      //   } else {
      //     console.log("没有权限")
      //     next('login')
      //   }
      // }
    }
})
//全局导航
	router.beforeEach((to, from, next) => {
      // to and from are both route objects. must call `next`.
      //console.log("进入全局导航守卫i")
      console.log(to.matched.some(item =>item.meta.allowBack));
      next();
      //if (to = '/page') {
        // if (token) {

        // } else {
        //   console.log("没有权限")
        //   next('login')
        // }
      //}
    })
```

# 二.Token验证

```js
导入axios
http.js
	import axios from 'axios' // 引用axios
//import QS from 'qs'
import router from '@/router'

// axios 配置
axios.defaults.timeout = 50000 //设置接口响应时间
axios.defaults.baseURL = 'http://localhost:8080' // 这是调用数据接口,公共接口url+调用接口名
//获取域名
// let httpUrl = window.location.host
// if (httpUrl.indexOf('.com') !== -1) {
//   console.log('生产环境', httpUrl)
// } else if (httpUrl.indexOf('.net') !== -1) {
//   console.log('测试环境', httpUrl)
//   axios.defaults.baseURL = 'http://www.test.com' // 这是调用数据接口,公共接口url+调用接口名
// } else if (httpUrl.indexOf('localhost:8088') !== -1) {
//   console.log('指定开发环境', httpUrl)
//   axios.defaults.baseURL = 'http://localhost:8088/'
// } else {
//   console.log('开发环境', httpUrl)
//   axios.defaults.baseURL = 'http://192.168.6.138:8080/' // 这是调用数据接口,公共接口url+调用接口名
// }

// http request 拦截器，通过这个，我们就可以把Cookie传到后台
axios.interceptors.request.use(
    //config包含每次请求的信息
    config => {
        //拿请求路径
        //console.log('请求路径', config.url)
        //去缓存中拿token
        let token = document.cookie.substring(6);  //从缓存中取token
        //有token
        if (token) {
            console.log(1111)
            config.headers.Authorization = token;    //将token放到请求头发送给服务器
        } else {
            //localStorage.clear();  //清空缓存
            //排除login请求接口
            if (router.currentRoute.name && router.currentRoute.name.toLowerCase() == "login") {

            } else {
                //否则 全部返回null
            }
        }
        return config
    },
    err => {
        return Promise.reject(err)
    }
)

// http response 拦截器
axios.interceptors.response.use(
    response => {
        return response; //请求成功的时候返回的data
    },
    error => {
        try {
            if (error.response) {
                switch (error.response.status) {
                    case 401: //token过期，清除token并跳转到登录页面
                        //localStorage.clear();
                        var baurl = window.location.href;
                        console.log(baurl)
                        router.replace({
                            path: 'login',
                            query: { backUrl: baurl }
                        });
                        return;
                }
            }
            return Promise.reject(error.response.data)
        }
        catch (e) {

        }
    }
)
// 封装axios的get请求
export function getData(url, params) {
    return new Promise((resolve, reject) => {
      axios.get(url, {params:params}).then(response => {
          //console.log(response)
          resolve(response.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  }
  // 封装axios的post请求
  export function postData(url, params) {
    return new Promise((resolve, reject) => {
      axios.post(url, QS.stringify(params)).then(response => {
          resolve(response.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  }
export default axios



api.js
	import { postData, getData } from './http.js'
    export function Logininfo(data = {}){
        return getData('/login', data);
    }
    export function testinfo(data = {}){
        return getData('/getMessage', data)
    }	
Login.vue
	import { Logininfo, testinfo } from "@/utils/api.js";
	submit() {
      Logininfo({
        username: this.username,
        password: this.password
      }).then(res => {
        console.log(res);
      });
    },
    test() {
      testinfo().then(res => {
        console.log(res);
      });
    }
  }
```

# 三.less

```js
npm install less less-loader --save-dev  安装
配置
	{
        test: /\.less$/,
        loader: "style-loader!css-loader!less-loader",
      }
      修改less版本不然会冲突
         "less-loader": "^5.0.0",
```

# 四.Markdown

```js
安装npm install text-loader --save-dev
在webpack.base.config.js中的module.rules中添加规则

{
test: /.md$/,
loader: ‘text-loader’
}

template:
	<vue-markdown>{{msg}}</vue-markdown>
js:

import VueMarkdown from 'vue-markdown’
import xxx from 'XXX.md’
export default {
	components: {
	VueMarkdown
	},
	data () {
		return {
		msg: xxx
		}
	}
}

```

# 五.SRC请求头发送信息

```js
//添加请求头信息
    console.log(this.$store.state.token)
    let token = this.$store.state.token; 
    var img = this.$refs.check
    var url = basesrc;
    var request = new XMLHttpRequest();
    request.responseType = 'blob';
    request.open('get', url, true);
    request.setRequestHeader('Authorization', token);
    request.onreadystatechange = e => {
                if (request.readyState == XMLHttpRequest.DONE && request.status == 200) {
                    img.src = URL.createObjectURL(request.response);
                    img.onload = () => {
                        URL.revokeObjectURL(img.src);
                    }
                }
            };
    request.send(null);
```

# 六.路由传值

```js
1.
{
      path: '/playvideo/:name',
      name: 'PlayVideo',
      component: PlayVideo
    }
    
    
  this.$router.push(
        {
          path:`/playvideo/${name}`
        })
        
        
 2. this.$router.push({
          name: 'Describe',
          params: {
            id: id
          }
        })
3. this.$router.push({
          path: '/describe',
          query: {
            id: id
          }
        })
```

# 七.keep-alive

```js
keep-alive是Vue提供的一个抽象组件，用来对组件进行缓存，从而节省性能，由于是一个抽象组件，所以在v页面渲染完毕后不会被渲染成一个DOM元素

被包裹在keep-alive中的组件的状态将会被保留，例如我们将某个列表类组件内容滑动到第100条位置，那么我们在切换到一个组件后再次切换回到该组件，该组件的位置状态依旧会保持在第100条列表处
1.include 和 exclude
    
    <keep-alive include="bookLists,bookLists">
          <router-view></router-view>
    </keep-alive>
    <keep-alive exclude="indexLists">
          <router-view></router-view>
    </keep-alive>
	
利用include、exclude属性
include属性表示只有name属性为bookLists，bookLists的组件会被缓存，（注意是组件的名字，不是路由的名字）其它组件不会被缓存exclude属性表示除了name属性为indexLists的组件不会被缓存，其它组件都会被缓存
2.
```

# 八.前端下载

```js
console.log(res);
          const blob = new Blob([res.data]);
          const fileName = filename;
          const alink = document.createElement("a");
          alink.download = fileName;
          alink.style.display = "none";
          alink.href = URL.createObjectURL(blob); // 这里是将文件流转化为一个文件地址
          document.body.appendChild(alink);
          alink.click();
          URL.revokeObjectURL(alink.href); // 释放URL 对象
          document.body.removeChild(alink);
```

# 九.router-view

嵌套路由跳转

```js
 <div>
      <router-link to="/hello1">hello1</router-link>
      <router-link to="/hello2">hello2</router-link>
      <router-link to="/hello3">hello3</router-link>
    </div>
    路由1<router-view id="sencond" name="default"></router-view>

routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld,
    // },
    {
      path: '/',
      name: 'world',
      components: {
        default: HelloWorld,
        second: Test
      },
      children:[
        {
          path: '/hello1',
          name: 'hello1',
          component: hello1
        },
        {
          path: '/hello2',
          name: 'hello2',
          component: hello2
        },
        {
          path: '/hello3',
          name: 'hello3',
          component: hello3
        },
```

# 十.vue后台管理框架

```js
1.vue element admin
https://github.com/PanJiaChen/vue-element-admin

2.vue admin
https://github.com/taylorchen709/vue-admin

3.vue-manage-system
https://github.com/lin-xin/vue-manage-system

4.iview-admin
https://github.com/iview/iview-admin

5.vuestic-admin
https://github.com/epicmaxco/vuestic-admin

6.d2-admin
https://github.com/d2-projects/d2-admin
```

# 十一.add routers权限控制

```js
这里面出现了两个个新的概念叫 “动态添加路由”和“导航守卫”，就是我前端router.js中只写所有人可以访问的路由表，比如login和404页面等。其他所有的组件资源全部写到一个新的components.js文件中，然后通过后端返回的menuData去映射符合components.js中的key，如果有对应的，就把它动态添加到router中，通过addRoutes添加。动态添加路由这个方法要写到导航守卫beforeEach这个钩子函数中。导航守卫的意思是我路由跳转到下个页面之前要做些什么。就是说我们登录后会跳到dashboard页面，在进到这个页面之前我们需要将后端请求回来的menuData进行二次封装，把他根据权限返回回来的data与我们前端components.js去做map匹配，将最终的数据通过addRoutes来push到我们的路由中，之后才能进到我们的dashborad页面，再通过dashborad页面进到对应的page页面，就是说我们把所有的权限控制全在dashboard页面进入之前就做完了。这里面还有一个小的优化的点：当我们通过前面说的浏览器菜单栏访问到非权限页面或者不存在的页面时，需要根据vue-router中的匹配优先级来最后addRoutes 404和*这个页面，这样就可以直接到达404页面而非空页面。


1.所有页面资源
	// components.js 所有的页面资源  
const home = () => import('../page/Home.vue');
const splashScreen = () => import('../page/splashScreen/SplashScreen.vue');
const addSplashScreen = () => import('../page/splashScreen/AddSplashScreen.vue');
const editSplashScreen = () => import('../page/splashScreen/EditSplashScreen.vue');

    export default {
        home,
        splashScreen,
        addSplashScreen,
        editSplashScreen,
    };
  2.router.js里面只书写所有用户通用的路由
  3.路由跳转的时候进行
  // main.js  伪代码 只保留具体的相关逻辑
import routeMap from './router/component.js';
const NotFound = () => import('./page/404.vue');
const formatRoutes = function (routes, routeData) {
    if (!routeData) {
        routeData = {
            name: 'home',
            path: '/home',
            // 组件匹配成功的话才可以访问具体的页面
            component: routeMap['home'],
            children: [],
        };
    }
    routes.length && routes.forEach(route => {
        if(route.component) {
            route.component = routeMap[route.component];
            routeData.children.push({
                path: route.path,
                name: route.index,
                component: route.component,
                meta: {
                    title: route.title,
                },
            })
        }
        if (route.children && route.children.length) {
            formatRoutes(route.children, routeData);
        }
    });
    return routeData;
};

let isFetchRemote = true;

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const username = sessionStorage.getItem('username');
    if(!username && to.path !== '/login'){
        next({path: '/login'});
    }
    else if (isFetchRemote && to.path !== '/login') {
        ajaxPost('/resourceAPI/getMenuData').then(res =>{
            if (res.status === 200 && res.data.errno === 0) {
                isFetchRemote = false;
                const menuData = res.data.result;
                localStorage.setItem('menudata', JSON.stringify(menuData));
                const routeData = formatRoutes(menuData);
                resourceApp.$router.addRoutes([routeData].concat([
                    {name:'404',path:'/404',component:NotFound},
                    {path:'*',redirect: '/404'}]));
                resourceApp.$router.push({
                    path: to.path,
                    query: to.query
                });
            }
            else {
                isFetchRemote = true;
            }
            next();
        })
        .catch(err => {
            console.log(err);
        });    
    }
    else {
        next();
    }
});
const resourceApp = new Vue({
    router,
    render: h => h(App)
}).$mount('#app');

4.而sidebar和dashboard这两个组件都只需要通过session拿到后端的menudate就可以。
	// dashboard 伪代码
    <template>
        <div class="nav_list">
            <div class="nav_list_item" v-for="item in navList" @click="goPage(item)">
                <i :class="item.icon"></i>
                <h2>{{item.title}}</h2>
            </div>
        </div>                       
    </template>

    <script>  
        created(){
            const routeArr = JSON.parse(localStorage.getItem('menudata'));
            this.navList = routeArr;
        },  
        methods: {
            goPage(item){
                // 只有一级菜单
                if(item.component){
                    this.$router.push(item.path);
                }else{
                    // 二级菜单的数据结构中只在children中有path
                    this.$router.push(item.children[0]['path']);
                }
            }
        }
    </script>
 5.
 // sidebar 伪代码
<script>
    export default {
        data() {
            return {
                routes: [],
            }
        },
        methods: {
            bouncer(arr){
                return arr.filter(function(val){
                  return !(!val || val === "");
                });
            }
        },
        created(){
            const menuData = JSON.parse(localStorage.getItem('menudata'));
            // 通过当前router的path来map对应的整个路由数组
            let routes = menuData.map((item)=>{

                // 只有一级路由
                if(item.component && item.path == this.$route.path){
                    console.log(item)
                    return item;
                }else{
                    if(item.children[0]['path'] == this.$route.path){
                        console.log(item)
                        return item;
                    }
                }
            })
            // 去掉数组中的undefined、null 等空值 假值
            this.routes = this.bouncer(routes);
        }
    }
</script>
```

# 十二.vue打包注意事项

```js
在build/utils.js里找到以下代码段添加 publicPath:'../../' 

把config/index.js里的module.exports的build的assetsPublicPath改为'./'

1.在config/index.js中将assetsPublicPath原来的’/‘修改为‘./’。
2.在nigx服务器上的操作

2.在nigx服务器上的操作
  1.
server
{
##在server下添加或在location里面添加以下代码
location /
{
if (!-e $request_filename) {
rewrite ^(.*)$ /index.html?s=$1 last;
break;
}
}
## 如果访问的不是根目录用下面方式设置 qiancheng是我的子目录
   location /qiancheng{
　　　　if (!-e $request_filename) {
　　　　　　rewrite ^/(.*) /qiancheng/index.html last;
　　　　　　break;
　　　　}
　　}
}

配置后刷新页面出现404的话前端改一下打包文件把相对路径改成绝对路径就OK了!

// 这是原来的代码
<link href=./static/css/app.917e9845e61fd94284baf966597e6264.css rel=stylesheet>
// 这是修改后的代码
<link href=/static/css/app.917e9845e61fd94284baf966597e6264.css rel=stylesheet>
// 如果访问的是子目录请加上子目录
<link href=/子目录/static/css/app.917e9845e61fd94284baf966597e6264.css rel=stylesheet>

 2.

把项目打包后的dist里面的文件复制粘贴到Nginx的html文件下（替换Nginx的文件）


3.部署在tomcat
	1、解决history模式，报404。直接在Tomcat中的web.xml中加入以下内容解决：
                 <error-page>    
                    <error-code>404</error-code>    
                    <location>/</location>    
                </error-page> 
     2.config文件夹中，index.js文件代码打包配置如下，此处只展示build节点
               build: {

            // Template for index.html
            index: path.resolve(__dirname, '../dist/index.html'),

            // Paths
            assetsRoot: path.resolve(__dirname, '../dist'),
            assetsSubDirectory: 'static',
            assetsPublicPath: '/dist/',

            /**
             * Source Maps
             */

            productionSourceMap: false,
            // https://webpack.js.org/configuration/devtool/#production
            devtool: '#source-map',

            // Gzip off by default as many popular static hosts such as
            // Surge or Netlify already gzip all static assets for you.
            // Before setting to `true`, make sure to:
            // npm install --save-dev compression-webpack-plugin
            productionGzip: false,
            productionGzipExtensions: ['js', 'css'],

            // Run the build command with an extra argument to
            // View the bundle analyzer report after build finishes:
            // `npm run build --report`
            // Set to `true` or `false` to always turn it on or off
            bundleAnalyzerReport: process.env.npm_config_report
          }
3.、代码中解决静态资源无法找到，比如图片的路径必须定位到精确目录。
	<img src="../../../static/image/complain1.png" class="card-img">
	
	具体文献参考https://www.jianshu.com/p/a8fb034f6b6a
```

# 十三.自定义指令

```js
const aclDirective = {
    inserted:function(el,binding){ // 在被绑定的元素插入到dom中时
        const hasAuth = function(needAuths，haveAuths){ //判断用户是否拥有权限的function
            // implement 
        }
        const havaAuths = []; // 用户拥有的权限列表
        if(!hasAuth(binding.value,haveAuths)){ //binding.value 可以获得绑定指令时传入的参数
            el.style = "display:none"; //修改元素的可见状态
        }
    }
}
//main.js
Vue.directive('acl',aclDirective);
```

# 十四.vuex的核心概念

```js
Vuex有五个核心概念：

　　state, getters, mutations, actions, modules。

　　1. state：vuex的基本数据，用来存储变量

　　 2. geeter：从基本数据(state)派生的数据，相当于state的计算属性
　　 3. mutation：提交更新数据的方法，必须是同步的(如果需要异步使用action)。每个 mutation 都有一个字符串的 事件类型 (type) 和 一个 回调函数 (handler)。

　　 回调函数就是我们实际进行状态更改的地方，并且它会接受 state 作为第一个参数，提交载荷作为第二个参数。
　　 4. action：和mutation的功能大致相同，不同之处在于 ==》1. Action 提交的是 mutation，而不是直接变更状态。 2. Action 可以包含任意异步操作。

　　  5. modules：模块化vuex，可以让每一个模块拥有自己的state、mutation、action、getters,使得结构非常清晰，方便管理。
　　  
　　  使用下面这两种方法存储数据：
　　dispatch：异步操作，写法： this.$store.dispatch('mutations方法名',值)
　　commit：同步操作，写法：this.$store.commit('mutations方法名',值)
　　
　　在actions中提交mutation，并且可以包含任何的异步操作。actions可以理解为通过将mutations里面处里数据的方法变成可异步的处理数据的方法，简单的说就是异步操作数据（但是还是通过mutation来操作，因为只有它能操作）
```

# 十五.在vue中使用富文本编辑器

```js
1. 安装
	npm install vue-quill-editor -S
	npm install quill -S
2.引入
    //引入富文本编辑器
    import { quillEditor } from 'vue-quill-editor'
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'
    Vue.use(quillEditor)
3.使用
	<quill-editor
    v-model="content"
    ref="myQuillEditor"
    :options="editorOption"
    @focus="onEditorFocus($event)"
    @blur="onEditorBlur($event)"
    @change="onEditorChange($event)"
  >
  </quill-editor>
  
  
  
  import {quillEditor} from 'vue-quill-editor'
export default {
  data() {
    return {
      content: "", // 富文本编辑器默认内容
      editorOption: {
       
      }
    };
  },
  mounted() {
    this.editor = this.$refs.myQuillEditor.quill;
  },
  beforeDestroy() {
    this.editor = null;
    delete this.editor;
  },
  components:{
      quillEditor
  },
  methods: {
    // 准备富文本编辑器
    onEditorReady(editor) {},
    // 富文本编辑器 失去焦点事件
    onEditorBlur(editor) {},
    // 富文本编辑器 获得焦点事件
    onEditorFocus(editor) {},
    // 富文本编辑器 内容改变事件
    onEditorChange(editor,html,text) {
        console.log(this.content)
    }
  }
};
```

# 十六.vuex持久化

```js
设置state为null,然后在对应的getters里面添加sessionStorage控制,在mutations里面添加对应sessionStorage控制,如：
1.方法1
let _store = {
  state: {
    appType: null
  },
  getters: {
    appType(state){
        if(!state.appType) {
          state.appType = getSessionStorage("appType")
        }
        return state.appType
    }
  },
  mutations: {
    setAppType (state, appType) {
       state.appType = appType
       setSessionStorage("appType",appType)
    }
  }
};

2.方法2
在页面初始化的时候,取出所有的保存在sessionStorage里面的值,同时在页面刷新前,将所有的state保存在sessionStorage里面

//在页面加载时读取sessionStorage里的状态信息
if (sessionStorage.getItem("store") ) {
    this.$store.replaceState(Object.assign({},     
    this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
}

 
//在页面刷新时将vuex里的信息保存到sessionStorage里
window.addEventListener("beforeunload",()=>{
    sessionStorage.setItem("store",JSON.stringify(this.$store.state))
})

3.最终解决方案:
const myPlugin = store => {
      // 当 store 初始化后调用
      let _state = store.state;
      for(let key in _state) {
        let _sessionvalue = getSessionStorage(key);
        if(_sessionvalue) {
          _state[key] = _sessionvalue;
        }
      }<br
      

办法一：将vuex中的数据直接保存到浏览器缓存中（sessionStorage、localStorage、cookie）
办法二：在页面刷新的时候再次请求远程数据，使之动态更新vuex数据
办法三：在父页面向后台请求远程数据，并且在页面刷新前将vuex的数据先保存至sessionStorage（以防请求数据量过大页面加载时拿不到返回的数据）

分析：
办法一的缺点是不安全，不适用大数据量的存储；
办法二适用于少量的数据，并且不会出现网络延迟；
办法三是要讲的重点，办法二和办法一一起使用。

```

# 十七.自定义指令

```js
Vue.directive('focus', {
  // 当被绑定的元素插入到 DOM 中时……
  inserted: function (el) {
    // 聚焦元素
    el.focus()
  }
})
<input v-focus>
```

# 十八.瀑布流

```js
npm i vue-waterfall2@latest --save
import waterfall from 'vue-waterfall2';
Vue.use(waterfall);
```

