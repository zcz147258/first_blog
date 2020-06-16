<template>
  <div id="noteadmin">
    <div class="content">
    </div>
    <div class="upload">
      <el-upload
        class="upload-demo"
        drag
        ref="upload"
        action="http://www.zczsylm.top:9200/upload"
        :data="uplist"
        multiple
        :with-credentials="true"
        :file-list="fileList"
        :on-success="fileSuccess"
        :on-error="fileFail"
        :on-change="handleChange"
        :auto-upload="false"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">
          只能上传md文件，且不超过5M
        </div>

      </el-upload>
       <el-button
          style="margin-left: 10px;margin-top:1em;"
          size="small"
          type="success"
          @click="submitUpload"
        >上传到服务器</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList: [],
      uplist: {},
      itemList: []
    };
  },
  mounted() {},
  methods: {
    handleChange(file, fileList) {
      this.uplist = {
        file: fileList,
        type: "note"
      };
    },
    fileSuccess(response, file, fileList) {
      console.log(response);
      if(!response.regEX){
        this.$msgbox({
            title: "提示",
            message: "正则校验失败",
            type: "error"
          });
      }
    },
    fileFail(err, file, fileList) {
      console.log(err);
    },
    submitUpload() {
      this.$refs.upload.submit();
    }
  }
};
</script>

<style lang="less" scoped>
#noteadmin {
  .content {
    height: 10em;
    background: rgba(182, 216, 205, 0.63);
    width: 100%;
    margin-bottom: 2em;
  }
  .upload{
    text-align: center;
  }
}
</style>
