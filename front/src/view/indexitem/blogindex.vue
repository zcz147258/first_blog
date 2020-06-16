<template>
  <div id="index">
    <div class="author">
      <div class="left">
        <el-avatar
          shape="circle"
          :size="100"
          src="http://img5.imgtn.bdimg.com/it/u=3008142408,2229729459&fm=26&gp=0.jpg"
        ></el-avatar>
      </div>
      <div class="right">
        <span>mikasa</span>
        <p>
          个人简介：从事前端2年，涉及前端后端领域,掌握java生态,Vue,React,Linux,Node,MySql,Mongodb等技术。
        </p>
      </div>
    </div>
    <div class="container">
      <div class="left">
        <div class="hr">
          <div>最新文章</div>
        </div>
        <div class="content">
          <div v-for="(item,index) in bloginfo" :key="index">
            <div class="leftcon">
              <img
                :src="item.imageurl"
                alt=""
              />
            </div>
            <div class="rightcon">
              <div class="first">
                <div class="title">{{item.blog_title}}</div>
                <div class="tag">
                  <el-tag v-for="(it,ind) in JSON.parse(item.blog_tag)" v-bind:key="ind" class="tag1" :type="adjustcolor(ind)" size="middle">{{ it }}</el-tag>
                  <!-- <el-tag class="tag1" type="success">java</el-tag>
                  <el-tag class="tag1" type="info">java</el-tag> -->
                </div>
              </div>
              <div class="con">
                <p v-html="item.blog_content">
                  <!-- {{ item.blog_content }} -->
                </p>
              </div>
              <div class="last">
                <div class="author1">
                  <i class="el-icon-s-flag" style="color: #FFC0CB"></i>
                  <span style="color: #759b08">{{ item.username }}</span>
                </div>
                <div class="createtime" style="margin-left:2em">
                  <i class="el-icon-watch" style="color: #7FFFD4"></i>
                  <span style="font-size:0.75em;color:#999;"
                    >{{ item.create_time }}</span
                  >
                </div>
                <!-- <div class="visit" style="margin-left:0.7em">
                  <div class="view" style="margin-left:10em">
                    <i class="el-icon-view" style="color: #87CECB"></i>
                    <span style="font-size:0.85em;color:#999">{{ item.view }}</span>
                  </div>
                  <div class="comment" style="margin-left:1em">
                    <i class="el-icon-chat-square" style="color: #87CECB"></i>
                    <span style="font-size:0.85em;color:#999">{{ item.comment }}</span>
                  </div>
                  <div class="dianzan" style="margin-left:1em">
                    <i class="el-icon-thumb" style="color: #87CECB"></i>
                    <span style="font-size:0.85em;color:#999">{{ item.dianzan }}</span>
                  </div>
                </div> -->
              </div>
            </div>
            <div class="hr1"></div>
          </div>
        </div>
      </div>
      <div class="right">
        <div class="first">
          <div class="type">分类</div>
          <ul>
            <li><span>日常生活</span><span>(1)</span></li>
            <li><span>技术博客</span><span>(2)</span></li>
            <li><span>旅游写实</span><span>(12)</span></li>
            <li><span>服务器运维</span><span>(4)</span></li>
          </ul>
        </div>
        <div class="first">
          <div class="type">最新文章</div>
          <ul>
            <li><span>文章1</span></li>
            <li><span>文章2</span></li>
            <li><span>文章3</span></li>
            <li><span>文章4</span></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { GetBlog } from '@/utils/api'
export default {
  data() {
    return {
      activeIndex: "1",
      bloginfo:[]
    };
  },
  created(){
    GetBlog().then((res)=>{
      console.log(res)
      this.bloginfo = res.bloginfo;
    })
  },
  computed:{
      
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    adjustcolor(ind){
        switch(ind%3){
          case 0:
            return 'success'
          case 1:
            return 'danger'
          case 2:
            return 'warning'
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
   min-height: 100vh;
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
          position: relative;
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
             margin-left: 1em;
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
                font-size: 1.1em;
                margin-top: 0.35em;
                margin-right: 5em;
                .tag1 {
                  font-size: 0.8em;
                  width: 5em;
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
            position: absolute;
            bottom: 0.5em;
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
  .end{
    width: 100%;
    background: rgba(32, 31, 31, 0.918);
    height: 5em;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    span{
      margin-left: 5em;
      font-size: 0.8em;
    }
  }
}
</style>
