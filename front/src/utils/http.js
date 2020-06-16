import axios from 'axios' // 引用axios
import QS from 'qs'
import router from '@/router'
import store from '../store/index'

// axios 配置
axios.defaults.timeout = 50000 //设置接口响应时间
axios.defaults.baseURL = 'http://www.zczsylm.top:9200' // 这是调用数据接口,公共接口url+调用接口名
axios.defaults.withCredentials= true;
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
        // let token = store.state.token;  //从缓存中取token
        //有token
        // if (token) {
        //     // config.headers.Authorization = token;    //将token放到请求头发送给服务器
        // } else {
        //     //localStorage.clear();  //清空缓存
        //     //排除login请求接口
        //     if (router.currentRoute.name && router.currentRoute.name.toLowerCase() == "login") {

        //     } else {
        //         //否则 全部返回null
        //     }
        // }
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
            // if (error.response) {
            //     switch (error.response.status) {
            //         case 401: //token过期，清除token并跳转到登录页面
            //             //localStorage.clear();
            //             var baurl = window.location.href;
            //             console.log(baurl)
            //             router.replace({
            //                 path: 'login',
            //                 query: { backUrl: baurl }
            //             });
            //             return;
            //     }
            // }
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
  export function Postdownload(url, params,responsetype) {
    return new Promise((resolve, reject) => {
      //{responseType:'blob'}
      axios.post(url, QS.stringify(params)).then(response => {
          resolve(response);
        })
        .catch(error => {
          reject(error);
        });
    });
  }
export default axios