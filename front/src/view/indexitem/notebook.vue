<template>
  <div id="notebook">
    <mavon-editor
      :value="content"
      :subfield="prop.subfield"
      :defaultOpen="prop.defaultOpen"
      :toolbarsFlag="prop.toolbarsFlag"
      :editable="prop.editable"
      :ishljs="true"
      :scrollStyle="prop.scrollStyle"
    ></mavon-editor>
  </div>
</template>

<script>
import { ScanNote } from '@/utils/api'
import "highlight.js/styles/github.css";
import hljs from "highlight.js"; //导入代码高亮文件
async function highlighthandle() {
  await hljs;
  let highlight = document.querySelectorAll("pre");
  highlight.forEach(block => {
    hljs.highlightBlock(block);
  });
}
export default {
  data() {
    return {
      content: ""
    };
  },
  created(){
    ScanNote({
      path:this.$route.query.data
    }).then((res=>{
      // console.log(res)
      this.content = res
    }))
  },
  mounted() {
    highlighthandle();
  },
  computed: {
    prop() {
      let data = {
        subfield: false, // 单双栏模式
        defaultOpen: "preview", //edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false,
        toolbarsFlag: false,
        scrollStyle: true
      };
      return data;
    }
  }
};
</script>

<style lang="less" scoped>
#notebook {
  background: transparent !important;
   min-height: 100vh;
}
</style>
