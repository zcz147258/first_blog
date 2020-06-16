<template>
  <el-row class="tac">
    <el-col :lg="3">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        background-color="#353131"
        text-color="#E0FFFF"
        active-text-color="#B8860B"
      >
        <el-menu-item
          v-for="(item, index) in menuList"
          :key="index"
          :index="`${index+1}`"
          @click="handleOpen(index+1,item.routerpath)"
        >
          <i :class="'el-icon-' + item.icon"></i>
          <span slot="title">{{ item.routername }}</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :lg="21" id="container">
      <div id="headinfo">
        <!-- <div id="bread">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/index' }"
              >{{breadinfo.first}}</el-breadcrumb-item
            >
            <el-breadcrumb-item v-if="breadinfo.second != ''">{{breadinfo.second}}</el-breadcrumb-item>
          </el-breadcrumb>
        </div> -->
        <div class="logo">
          CMS管理系统
        </div>
        <div id="loginout">
          <span
            style="color:rgb(193, 221, 235);font-size:1.2em;margin-right:1em"
            >欢迎你！{{ username }}</span
          >
          <el-button
            type="danger"
            size="small"
            icon="el-icon-switch-button"
            @click="loginout"
          ></el-button>
        </div>
      </div>
      <router-view />
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      username: this.$store.state.username,
      //权限列表
      menuList: []
    };
  },
  created() {
    // console.log(this.$store.state.routerList)
    console.log(this.$router.options.routes)
    this.menuList = this.$store.state.routerList;
  },
  mounted() {
    this.$router.push("/selfinfo").catch(data => {});
  },
  methods: {
    handleOpen(index,path) {
      var path = `/${path}`
      this.$router.push(path).catch(data => {});
    },
    openitem(key, keypath) {
      // console.log(key, keypath);
    },
    loginout() {
      //清空session
      sessionStorage.clear()
      this.$router.push("/login");
      //刷新
      window.location.reload()
    }
  }
};
</script>

<style lang="less">
.el-menu-vertical-demo {
  min-height: 100vh;
}
.tac {
  width: 100%;
  min-height: 100vh;
}
#container {
  background: #fffaf0;
  height: 100vh;
  overflow: scroll;
  #headinfo {
    height: 50px;
    background-color: #353131;
    display: flex;
    // flex-direction: row-reverse;
    align-items: center;
    justify-content: space-between;
    .logo {
      font-size: 1.5em;
      font-weight: bold;
      margin-left: 3em;
      color: rgb(193, 221, 235);
    }
    #bread {
      margin-left: 20px;
    }
    #loginout {
      margin-right: 50px;
    }
  }
}
.el-menu i {
  color: #e0ffff;
}
</style>
