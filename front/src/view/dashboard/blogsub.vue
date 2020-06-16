<template>
<div id="blogsub">
  <div class="titleimg">
    <div>
      <span>标题：</span><el-input class="title" v-model="title" placeholder="请输入标题"></el-input>
    </div>
    <div>
      <span>分类：</span>
        <el-radio-group v-model="radionum" @change="radiocheck" style="font-size:1.2em">
          <el-radio :label="1">日常生活</el-radio>
          <el-radio :label="2">技术博客</el-radio>
          <el-radio :label="3">旅游写实</el-radio>
          <el-radio :label="4">服务器运维</el-radio>
        </el-radio-group>
    </div>
    <div>
      <span>标签：</span>
      <el-tag style="font-size:1em;margin-left:1em;font-weight:normal" v-for="(item,index) in tagList" type="warning" :key="index">{{ item }}</el-tag>
      <el-input class="tag" v-model="tag" placeholder="请输入标签"></el-input>
      <el-button type="primary" style="margin-left:2em" @click="addtag">增加</el-button>
    </div>
    <div>
        <span>图片：</span>
        <img :src="imageurl" style="margin-right:1em" alt="">
        <input type="file" @change="changeImg">
    </div>
  </div>
  <div class="submit">
    <h2>正文:</h2>
    <quill-editor
      v-model="content"
      ref="myQuillEditor"
      :options="editorOption"
      @focus="onEditorFocus($event)"
      @blur="onEditorBlur($event)"
      @change="onEditorChange($event)"
    >
    </quill-editor>
    <el-button  type="warning" class="upload" @click="upload">上传发布</el-button>
  </div>
</div>
  
</template>

<script>
import { quillEditor } from "vue-quill-editor";
import { PostBlog } from '@/utils/api'
export default {
  data() {
    return {
      title:"",//标题
      content: "", // 富文本编辑器默认内容
      editorOption: {},//编辑选项
      radionum: '',//选中的分类
      radio:'',
      tag:'',//增加标签内容
      tagList:[],
      imageurl:''
    };
  },
  mounted() {
    this.editor = this.$refs.myQuillEditor.quill;
  },
  beforeDestroy() {
    this.editor = null;
    delete this.editor;
  },
  components: {
    quillEditor
  },
  methods: {
    changeImg(e){
      var file = e.target.files[0]
      this.imgShrink(file).then((res)=>{
        this.imageurl = res
      })
    },
    // 准备富文本编辑器
    onEditorReady(editor) {},
    // 富文本编辑器 失去焦点事件
    onEditorBlur(editor) {},
    // 富文本编辑器 获得焦点事件
    onEditorFocus(editor) {},
    // 富文本编辑器 内容改变事件
    onEditorChange(editor, html, text) {
    //   console.log(this.content);
    },
    //提交标签
    addtag(){
      if(this.tag != ''){
        this.tagList.push(this.tag)
      }else{
         this.$message('不能为空');
      }
    },
    radiocheck(e){
        var arr = [
          '日常生活',
          '技术博客',
          '旅游写实',
          '服务器运维'
        ]
        this.radio = arr[e-1]
    },
    upload(){
        PostBlog({
            title: this.title,
            content:this.content,
            radio:this.radio,
            tagList:JSON.stringify(this.tagList),
            imageurl:this.imageurl
        }).then((res)=>{console.log(res)}).catch((err)=>{
            console.log(err)
        })
    },
    imgShrink(file,imgWidth,imgHeight) {
        return new Promise(function(resolve, reject) {
          let imgResult = ""
          var width = imgWidth || 100 //图像大小
          var height = imgHeight || 100//图像大小
          let reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = function() {
            let img = new Image();
            img.src = this.result;
            let quality = 0.8, //图像质量 取值0-1 值越小，所绘制出的图像越模糊
            canvas = document.createElement("canvas"),
            drawer = canvas.getContext("2d");
            img.onload = function () {
              canvas.width = width;
              canvas.height = height;
              drawer.drawImage(img, 0, 0, canvas.width, canvas.height);
              // 返回base64
              imgResult = canvas.toDataURL("image/png", quality);
              resolve(imgResult);
            }
          };
          reader.onerror = function(error) {
            reject(error);
          };
        });
    }
  }
}
</script>

<style lang="less" scoped>
#blogsub{
  .titleimg{
    >div{
      display: flex;
      margin-top: 2em;
      align-items: center;
      margin-left: 2em;
      .tag{
          width: 20%;
      }
      >span{
        display: inline-block;
        width: 7%;
        color: rgb(228, 138, 28);
        font-weight: bolder;
        font-size: 1.5em;
      }
      .title{
        width: 30%;
      }
    }
    
  }
  .submit{
    h2{
      padding-left: 1.3em;
      color: rgb(228, 138, 28);
        font-weight: bolder;
        font-size: 1.5em;
        margin-bottom: 1em;
        margin-top: 1.5em;
    }
    .upload{
    margin-top: 20px;
    margin-left: 50%;
    transform: translate(-50%);
  }
  }
 
}

</style>>

</style>
