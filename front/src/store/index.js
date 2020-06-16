import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
export default new Vuex.Store({
    state:{
        token:'',
        routerList: JSON.parse(sessionStorage.getItem('routerList') || '[]'),
        username:sessionStorage.getItem('username') || ''
    },
    mutations:{
        Setusername(state,name){
            sessionStorage.setItem('username',name)
            state.username = name
        },
        changeToken(state,str){
            state.token = str;
        },
        exit(state){
            state.token = '';
        },
        SetRouterList(state,arr){
            sessionStorage.setItem('routerList',JSON.stringify(arr))
            state.routerList = arr
        }
    }
})