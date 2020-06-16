<template>
  <div id="picture">
    <div class="container">
      <el-upload
        class="upload-demo"
        ref="upload"
        action="http://www.zczsylm.top:9200/upload"
        :data="uplist"
        multiple
        :with-credentials="true"
        :file-list="fileList"
        :on-success="fileSuccess"
        :on-error	="fileFail"
        :on-change="handleChange"
        :auto-upload="false"
        style="text-align:center;margin-top:5em"
      >
        <el-button style="margin-top:1em" slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button
          style="margin-left: 10px;margin-top:1em;"
          size="small"
          type="success"
          @click="submitUpload"
        >上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip" style="color:red;font-size:1em;">只能上传不能超过500MB</div>
      </el-upload>
    </div>
  </div>
</template>

<script>
export default {
data() {
    return {
      fileList:[],
      uplist:{},
      itemList: [
      ]
    };
  },
  mounted(){
    
  },
  methods: {
    handleChange(file, fileList){
      this.uplist = {
        file:fileList,
        type:'image'
      }
    },
    fileSuccess(response,file,fileList){
        console.log(response)
        // if(!response.regEX){
        //   this.$msgbox({
        //       title: "提示",
        //       message: "正则校验失败",
        //       type: "error"
        //     });
        // }
    },
    fileFail(err,file,fileList){
        console.log(err)
    },
    submitUpload(){
        this.$refs.upload.submit();
    },
    
  }
};
</script>

<style lang="less" scoped>
#index {
  width: 100%;
  height: 100vh;
  position: absolute;
  font-size: 10px;
  // top: 0;
  // bottom: 0;
  // left: 0;
  // right: 0;
  background-image: url('~@/assets/bg/2.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  .container {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    .upload-demo {
      width: 40%;
      background-color: rgb(88, 105, 206);
      text-align: center;
      height: 20em;
      line-height: 100%;
      border-radius: 1em;
      /deep/.el-upload-list el-upload-list--text{
        color: cyan;
        font-weight: bolder;
      }
    }
    .item {
      width: 23%;
      height: 5.5em;
      margin-right: 2.5em;
      margin-bottom: 2.5em;
      background-color: rgba(20, 221, 171, 0.9);
      border-radius: 0.5em;
      line-height: 5.5em;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      font-size: 2.5em;
      color: rgb(223, 23, 100);
      font-weight: bolder;
      transition: 0.35s;
      img {
        width: 25%;
        margin-right: 0.5em;
      }
    }
    .item:hover {
      background-color: rgba(225, 170, 229, 0.9);
    }
  }
}
@media screen and (max-width: 475px) {
  #index {
    font-size: 5px;
    .container {
      width: 80%;
      .item {
        width: 40%;
        margin-right: 1.5em;
      }
    }
  }
}
</style>