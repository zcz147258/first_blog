<template>
  <div id="signon">
    <div class="container">
      <h2>注册</h2>
      <div>
        <div>
          <span> 用户名：</span
          ><el-input v-model="username" class="insert" placeholder="用户名"></el-input>
        </div>
        <div>
          <span> 密码：</span
          ><el-input v-model="password"  type="password" class="insert"  placeholder="密码"></el-input>
        </div>
        <div>
          <span> 邮箱：</span
          ><el-input v-model="email" type="email" class="insert" placeholder="邮箱"></el-input>
        </div>
        <div>
          <span> 验证码：</span
          ><el-input v-model="emailcode" class="insert2" placeholder="验证码"></el-input>
          <el-button style="width:6.5em;height:2.7em;margin-left:1em; text-align: center;" type="primary" @click="send"
            >发送验证</el-button
          >
        </div>

        <el-button
          class="singon"
          type="success"
          @click="signon"
          :disabled="controll"
          >注册</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { SignOn } from "../utils/api";
import { Send } from "../utils/api";
export default {
  data() {
    return {
      username: "",
      password: "",
      email: "",
      emailcode: "",
      controll: true
    };
  },
  methods: {
    send() {
      Send({
        email: this.email
      })
        .then(res => {
          this.controll = false;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    signon() {
      SignOn({
        username: this.username,
        password: this.password,
        email: this.email,
        emailcode: this.emailcode
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style lang="less" scoped>
#signon {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  background-image: url(../assets/bg/7.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  font-size: 10px;
  .container {
    h2 {
      text-align: center;
      font-weight: bolder;
      font-size: 2.5em;
      margin-top: 0.5em;
      margin-bottom: 0.5em;
    }
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 20%;
    height: 350px;
    border-radius: 8px;
    padding: 0px 3em;
    background: rgba(255, 255, 255, 0.8);
    > div {
      > div {
        display: flex;
        flex-wrap: nowrap;
        margin-bottom: 1em;
        .insert{
           width: 70%;
        }
        .insert2{
            width: 35%;
        }
        span{
          width: 25%;
          font-size: 1.5em;
          color: rgb(49, 151, 112);
          font-weight: bold;
          line-height: 2.5em;
        }
      }
    }
    .singon{
      width: 100%;
      margin: 1em auto;
    }
  }
}
</style>
