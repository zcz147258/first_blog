<template>
  <div id="playvideo">
    <video ref="check" muted autoplay controls ></video>
  </div>
</template>

<script>
import { PlayVideo } from "@/utils/api.js";
//import { mapState } from 'vuex'
export default {
  data() {
    return {
      url: ""
    };
  },
  mounted() {
    var basesrc =
      "http://www.zczsylm.top:9200/playvideo?name=" + this.$route.params.name;
    //this.url = basesrc;
    //添加请求头信息
    console.log(this.$store.state.token)
    let token = this.$store.state.token; 
    var img = this.$refs.check
    //注意含有特殊字符注意转码
    var url = encodeURI(basesrc);
    var request = new XMLHttpRequest();
    request.responseType = 'blob';
    request.open('get', url, true);
    request.setRequestHeader('Authorization', token);
    request.onreadystatechange = e => {
                if (request.readyState == XMLHttpRequest.DONE && request.status == 200) {
                    img.src = URL.createObjectURL(request.response);
                    img.onload = () => {
                        URL.revokeObjectURL(img.src);
                    }
                }
            };
    request.send(null);
  },
  computed:{
    //...mapState(['token'])
  }
};
</script>

<style lang="less" scoped>
#playvideo {
  width: 100%;
  //height: 100vh;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-image: url('~@/assets/bg/5.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  video {
    position: absolute;
    width: 50%;
    height: auto;
    left: 50%;
    top: 40%;
    transform: translate(-50%,-50%);
  }
}
@media screen and (max-width: 475px) {
  #playvideo{
    video{
       width: 80%;
    }
  }
}
</style>