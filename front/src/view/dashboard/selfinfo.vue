<template>
  <div id="selfinfo">
    <el-row>
      <el-col :span="2"></el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple" id="content">
          <h2 style="text-align:center;color:#FF7F50;font-size:1.5em;margin-bottom:1em">修改信息</h2>
          <el-form
            :model="ruleForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="用户名" prop="username">
              <el-input type="username" v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkpass">
              <el-input v-model="ruleForm.checkpass" type="password"></el-input>
            </el-form-item>
            <el-form-item label="个人简介" prop="introduce">
              <el-input v-model="ruleForm.introduce" :rows="5" size="medium" type="textarea"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button class="button1" type="primary" @click="submitForm('ruleForm')"
                >提交</el-button>
              <el-button class="button1" type="warning" @click="resetForm('ruleForm')"
                >重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <!-- <el-col :span="7"><div class="grid-content bg-purple"></div></el-col> -->
      <el-col :span="2"></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    //用户名校验
    var usernamepass = function(rule, value, callback) {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      }
      //发请求校验是否重复
      setTimeout(() => {
        if (value.length > 6) {
          return callback(new Error("用户名过长"));
        } else {
          //发请求校验
          callback();
        }
      });
    };
    //密码校验
    var passwordpass = function(rule, value, callback) {
      if (value === "") {
        callback(new Error("密码不能为空"));
      } else {
        callback();
      }
    };
    var checkpass = function(rule, value, callback) {
      if (value !== this.password) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: "",
        password: "",
        checkpass: "",
        introduce:''
      },
      rules: {
        username: [{ validator: usernamepass, trigger: "blur" }],
        password: [{ validator: passwordpass, trigger: "blur" }],
        checkpass: [{ validator: checkpass, trigger: "blur" }]
      }
    };
  },
  methods: {
    //提交表单信息
    submitForm(formName) {

    },
    //重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style lang="less" scoped>
#selfinfo {
  width: 100%;
  #content {
    margin-top: 2em;
    .button1{
      margin-left: 3.5em;
    }
  }
  #content::before{
    display: block;
    clear: both;
  }
}
</style>
