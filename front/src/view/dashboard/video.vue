<template>
  <div id="video">
    <div class="container">
      <div v-for="(item, index) in videoList" :key="index">
        <div>
          <img src="../../assets/bg/4.jpg" alt @click="trunto(item.filename)" />
        </div>
        <p>{{ item.filename }}</p>
        <el-button
          style="margin-top:0.5em"
          type="primary"
          @click="download(item.type, item.filename)"
          round
          >下载</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { GetInfo } from "@/utils/api.js";
import { Download } from "@/utils/api.js";
export default {
  data() {
    return {
      videoList: []
    };
  },
  created() {
    GetInfo({
      username: "mikasa",
      type: "mp4"
    })
      .then(res => {
        console.log(res);
        this.videoList = res.Info;
      })
      .catch(err => {
        this.$msgbox({
          title: "提示",
          message: err.message,
          type: "error"
        })
          .then(() => {})
          .catch();
      });
  },
  methods: {
    download(type, filename) {
      Download({
        type: type,
        filename: filename
      })
        .catch(err => {
          console.log(err);
        })
        .then(res => {
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
        });
    },
    trunto(name) {
      console.log(name);
      this.$router.push({
        path: `/playvideo/${name}`
      });
    }
  }
};
</script>

<style lang="less" scoped>
#video {
  width: 100%;
  //height: 100vh;
  font-size: 10px;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-image: url(../../assets/bg/6.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  .container {
    width: 50%;
    height: 50%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: rgba(44, 233, 201, 0.85);
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    border-radius: 1em;
    > div {
      color: black;
      width: 20%;
      height: 20%;
      text-align: center;
      color: rgb(247, 0, 0);
      font-weight: bolder;
      font-size: 1em;
      p {
        margin-top: 1em;
      }
      > div {
        width: 7.5em;
        height: 7.5em;
        border-radius: 1.5em;
        overflow: hidden;
        margin: auto;
        margin-top: 1em;
        img {
          width: 15em;
          height: 15em;
          border-radius: 1.5em;
          cursor: pointer;
          transition: 0.5s;
        }
        img:hover {
          transform: scale(1.3);
        }
      }
    }
  }
}
@media screen and (max-width: 475px) {
  #video {
    font-size: 5px;
    .container {
      width: 80%;
      height: 50em;
    }
  }
}
</style>
