<template>
  <div id="notoall">
    <div class="container">
      <div v-for="(item, index) in book" :key="index" @click="todetails(item.path)">
        <img src="http://t7.baidu.com/it/u=4159033034,904447401&fm=193" alt="" />
        <div class="title">{{ item.name }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { GetNote } from '@/utils/api'
export default {
  data() {
    return {
      book: []
    };
  },
  created(){
    GetNote().then((res)=>{
      console.log(res)
      this.book = res.noteinfo;
    })
  },
  computed:{
  },
  methods:{
      todetails(path){
          //加载
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          setTimeout(() => {
            this.$router.push({path:'/notebook',query:{data:path}})
            loading.close();
          }, 2000);
          //加载完毕
          // console.log(title)
      }
  }
};
</script>

<style lang="less" scoped>
#notoall {
  .container {
    width: 60%;
    margin: auto;
    margin-top: 3em;
    min-height: 100vh;
    display: flex;
    flex-wrap: wrap;
    > div {
      width: 30%;
      text-align: center;
      background: rgb(223, 211, 223);
      border-radius: 7px;
      height: 12em;
      padding: 0.3em;
      margin-right: 1em;
      padding-top: 1em;
      margin-bottom: 15px;
      cursor: pointer;
      img {
        width: 90%;
        height: 8em;
      }
      .title {
        margin-top: 1em;
        text-align: center;
      }
    }
    >div:nth-child(3n){
        margin-right: 0em;
    }
  }
}
</style>
