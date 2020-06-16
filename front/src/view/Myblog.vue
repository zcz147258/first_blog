<template>
  <div id="index">
    <div class="head">
      <div class="tabbar">
        <div class="title">张承泽博客欢迎您!!</div>
        <div class="right">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
          >
            <el-menu-item index="1">博客首页</el-menu-item>
            <el-menu-item index="2">笔记</el-menu-item>
            <el-menu-item index="3">相册</el-menu-item>
            <el-menu-item index="4">登陆</el-menu-item>
          </el-menu>
        </div>
      </div>
    </div>
    <router-view />
    <div class="end">
      <span>帮助中心</span>
      <span>qq1092283755</span>
      <span>微信18419617559</span>
      <span>活动中心</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1"
    };
  },
  created(){
    this.$router.push("/blogindex").catch(data => {});
  },
  computed: {},
  methods: {
    handleSelect(key, keyPath) {
      switch (key) {
        case "1":
          this.$router.push("/blogindex").catch(data => {});
          break;
        case "2":
          this.$router.push("/noteall").catch(data => {});
          break;
        case "3":
          this.$router.push("/photo").catch(data => {});
          break;
        case "4":
          this.$router.push("/login").catch(data => {});
          break;
        default:
          this.$router.push("/blogindex").catch(data => {});
      }
    }
  }
};
</script>

<style lang="less" scoped>
#index {
  font-size: 20px;
  height: auto;
  background: rgba(172, 243, 225, 0.555);
  .head {
    width: 100%;
    height: 2.2em;
    line-height: 2.2em;
    background: bisque;
    box-shadow: 0px 0px 3px 5px rgba(0, 0, 0, 0.4);
    .tabbar {
      width: 60%;
      margin: auto;
      display: flex;
      justify-content: space-between;
      .right {
        .el-menu {
          background: transparent;
        }
        .el-menu--horizontal > .el-menu-item {
          height: 2.2em;
          line-height: 2.2em;
          font-size: 20px;
          border: none;
        }
        .el-menu--horizontal > .el-menu-item.is-active {
          border: none;
        }
        .el-menu-item:hover {
          background: rgb(224, 226, 114);
        }
      }
    }
  }
  .author {
    width: 80%;
    margin: 3px auto;
    background: url("~@/assets/blogimg/bg.jpg");
    height: 10em;
    filter: grayscale(30%);
    background-size: 100% 10em;
    background-repeat: no-repeat;
    display: flex;
    .left {
      margin-left: 3em;
      margin-top: 1.5em;
    }
    .right {
      margin-top: 1.5em;
      span {
        font-size: 2em;
        margin-left: 0.5em;
        color: rgb(224, 58, 127);
      }
      p {
        margin-left: 0.7em;
        color: rgb(168, 228, 58);
        font-size: 1.2em;
      }
    }
  }
  .container {
    width: 80%;
    display: flex;
    margin: auto;
    .left {
      width: 80%;
      .hr {
        width: 100%;
        padding: 1em 0em;
        font-size: 1.2em;
        border-bottom: 5px solid rgb(238, 126, 223);
        margin-bottom: 1em;
        > div {
          font-weight: bolder;
        }
      }
      .content {
        > div {
          background: white;
          margin-bottom: 1em;
          border-bottom: 5px solid tan;
          display: flex;
          .leftcon {
            width: 20%;
            img {
              width: 8em;
              height: 8em;
            }
          }
          .con {
            margin-top: 0.5em;
            margin-bottom: 0.5em;
            font-size: 0.75em;
            color: rgb(158, 134, 134);
            display: -webkit-box;
            -webkit-box-orient: vertical;
            text-overflow: ellipsis;
            -webkit-line-clamp: 3;
            overflow: hidden;
          }
          .rightcon {
            width: 80%;
            .first {
              display: flex;
              justify-content: space-between;
              .title {
                font-size: 1em;
                margin-top: 0.5em;
                color: rgba(11, 14, 13, 0.795);
                font-weight: bold;
              }
              .tag {
                font-size: 1.2em;
                margin-top: 0.35em;
                margin-right: 5em;
                .tag1 {
                  font-size: 0.8em;
                  width: 3em;
                  margin-left: 0.5em;
                  height: 1.5em;
                  text-align: center;
                  line-height: 1.5em;
                }
              }
            }
          }
          .last {
            margin-top: 0.8em;
            display: flex;
            .visit {
              display: flex;
            }
          }
        }
      }
    }
    .right {
      margin-left: 5px;
      width: 20%;
      .type {
        text-align: center;
        font-size: 1em;
        font-weight: bold;
        line-height: 2em;
        border-bottom: 2px solid rgb(163, 148, 148);
      }
      ul {
        margin-top: 1em;
        margin-bottom: 2em;
        li {
          padding-left: 1em;
          font-size: 18px;
          margin: 0.2em 0em;
        }
      }
    }
  }
  .end {
    width: 100%;
    background: rgba(32, 31, 31, 0.918);
    height: 5em;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    span {
      margin-left: 5em;
      font-size: 0.8em;
    }
  }
}
</style>
