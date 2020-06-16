<template>
  <div id="word">
    <div>
      <el-row id="container">
        <div v-for="(item, index) in HtmlList" :key="index">
          <el-button type="success" @click="trunto(item.filename)">{{
            item.filename | filt
          }}</el-button>
          <!-- <a href="../../../static/md/java.html" style="display:inline-block;width:5em;height:2.5em;">{{item.filename}}</a> -->
          <el-button
            style="margin-top:0.5em;width:5em;height:3.5em;text-align:center;font-size:1em;"
            type="primary"
            @click="download(item.type, item.filename)"
            round
            >下载</el-button
          >
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import { GetInfo } from "@/utils/api.js";
import { GetHtml } from "@/utils/api.js";
import { Download } from "@/utils/api.js";
export default {
  data() {
    return {
      HtmlList: []
    };
  },
  mounted() {},
  filters: {
    filt(value) {
      let num = value.indexOf(".");
      let str = value.substring(0, num);
      return str;
    }
  },
  created() {
    GetInfo({
      username: "mikasa",
      type: "html"
    })
      .then(res => {
        console.log(res);
        this.HtmlList = res.Info;
      })
      .catch(err => {});
  },
  methods: {
    trunto(name) {
      // window.location.href = "../../../static/md/" + name;
      this.$msgbox({
        title: "提示",
        message: "请点击下载按钮下载" + name,
        type: "error"
      });
    },
    download(type, filename) {
      Download({
        type: type,
        filename: filename
      })
        .then(res => {
          if (res.data.message) {
            console.log(JSON.stringify(res.data.message));
          } else {
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
          }
        })
        .catch(err => {});
    }
  }
};
</script>

<style lang="less" scoped>
#word {
  width: 100%;
  //height: 100vh;
  font-size: 10px;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-image: url(../../assets/bg/5.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  > div {
    margin-top: 5em;
    #container {
      width: 50%;
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      justify-content: space-around;
      flex-wrap: wrap;
      display: flex;
      > div {
        > button {
          width: 7em;
          height: 4em;
          margin-bottom: 1.5em;
          font-size: 1.3em;
          font-weight: bolder;
        }
      }
    }
  }
}
@media screen and (max-width: 475px) {
  #word {
    font-size: 5px;
  }
}
</style>
