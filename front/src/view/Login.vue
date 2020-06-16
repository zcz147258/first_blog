<template>
  <div id="login">
    <div id="container">
      <h2>WELCOME</h2>
      <div>
        <span>用户名：</span>
        <el-input
          class="insert"
          prefix-icon="el-icon-user-solid"
          placeholder="请输入用户名"
          v-model="username"
          clearable
        ></el-input>
        <br />
        <span>密码：</span>
        <el-input
          class="insert"
          prefix-icon="el-icon-s-tools"
          type="password"
          placeholder="请输入密码"
          v-model="password"
          clearable
        ></el-input>
        <br />
        <!-- <span>验证码：</span>
        <el-input
          class="insert lastinput"
          prefix-icon="el-icon-message-solid"
          placeholder="请输入验证码"
          v-model="codelint"
          clearable
        ></el-input> -->
        <br />
      </div>
      <!-- <el-button @click="check" class="login" icon="el-icon-thumb" type="success">登录</el-button> -->
      <el-button
        @click="check"
        class="login"
        icon="el-icon-thumb"
        type="success"
        :loading="control"
      >登录</el-button>
      <el-button type="primary" class="signon" @click="signon">注册</el-button>
    </div>
  </div>
</template>

<script>
import { Login } from "@/utils/api";
import { mapMutations } from "vuex";
//导入动态路由
import {initDynamicRoutes} from '@/router'
export default {
  data() {
    return {
      username: "",
      password: "",
      codelint: "",
      control: false
    };
  },
  //刚进入把token清空
  mounted() {
    // this.exit();
  },
  methods: {
    signon(){
      this.$router.push('/signon')
    },
    // ...mapMutations(["changeToken", "exit"]),
    check() {
      this.control = true;
      if (this.username == "" || this.password == "") {
        this.$confirm("用户名和密码不能为空", "提示", {
          distinguishCancelAndClose: false,
          center: true
        })
          .then(res => {this.control=false})
          .catch(err => {this.control=false});
      } else {
        Login({
          username: this.username,
          password: this.password
        }).then(res => {
          if (res.success) {
            this.$notify({
              title: "成功",
              message: "欢迎进入！！！",
              type: "success"
            });
            //拿到菜单数据
            this.$store.commit('SetRouterList',res.routerList)
            this.$store.commit('Setusername',this.username)
            initDynamicRoutes()
            this.control=false
            // this.changeToken(res.token);
            this.$router.push("/dashboard");
          } else {
            this.$msgbox({
              title: "提示",
              message: res.message,
              type: "error"
            });
            this.control=false
          }
        });
      }
    }
  }
};
</script>

<style scoped lang="less">
#login {
  font-size: 10px;
  width: 100%;
  position: absolute;
  top: 0;
  min-height: 100%;
  bottom: 0;
  background-image: url(../assets/bg/7.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  #container {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 25%;
    height: 45%;
    transform: translate(-50%, -50%);
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 1em;
    display: flex;
    justify-content: center;
    align-items: center;
    > h2 {
      width: 100%;
      position: absolute;
      top: 1.5em;
      text-align: center;
      font-weight: bolder;
      font-size: 2.5em;
    }
    .login {
      position: absolute;
      width: 30%;
      bottom: 3.5em;
      left: 5em;
    }
    .signon{
      position: absolute;
       width: 30%;
      bottom: 3.5em;
      left: 15em;
    }
    > div {
      width: 90%;
      text-align: left;
      span {
        display: inline-block;
        width: 20%;
        margin-bottom: 2.5em;
        font-weight: bolder;
        margin-left: 2em;
        font-size: 1.5em;
        color: rgb(49, 151, 112);
      }
      .insert {
        display: inline-block;
        width: 70%;
      }
      .lastinput {
        width: 45%;
      }
    }
  }
}
@media screen and (max-width: 475px) {
  #login {
    font-size: 5px;
    #container {
      width: 80%;
      .login {
        bottom: 2em;
      }
      > div {
        .lastinput {
          width: 55%;
        }
      }
    }
  }
}
</style>