// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//重置css样式
import '@/assets/css/reset.css'
//引入element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//引入vuex
import store from './store/index.js'
//路由
// import routeMap from './router/component.js';
//结束
//引入富文本编辑器
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(quillEditor)
Vue.use(ElementUI);
Vue.config.productionTip = false
//瀑布流
import waterfall from 'vue-waterfall2';
Vue.use(waterfall);
//引入md解析
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})