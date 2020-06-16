<template>
  <div id="photo">
    <waterfall
      :col="col"
      :data="data" 
      @finish="finish"
    >
      <template>
        <div class="cell-item" v-for="(item, index) in data" v-bind:key="index">
          <img :src="item.img" style="width:320px;height:auto;margin-top:1em" />
          <div class="item-body">
            <div class="item-desc">{{ item.title }}</div>
            <div class="item-footer">
              <div class="name">{{ item.user }}</div>
              <div class="like" :class="item.liked ? 'active' : ''">
                <i></i>
                <div class="like-total">{{ item.liked }}</div>
              </div>
            </div>
          </div>
        </div>
      </template>
    </waterfall>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [
        {
          img:'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3238317745,514710292&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3340877733,3221894234&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3008142408,2229729459&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2853553659,1775735885&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3238317745,514710292&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3340877733,3221894234&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3008142408,2229729459&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2853553659,1775735885&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3238317745,514710292&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3340877733,3221894234&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3008142408,2229729459&fm=26&gp=0.jpg',
          title:'熊猫',
        },
        {
          img:'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2853553659,1775735885&fm=26&gp=0.jpg',
          title:'熊猫',
        },
      ],
      col: 3
    };
  },
  methods: {
    // mix() {
    //   this.$waterfall.mix();
    // },
    loadmore(index) {
      this.data = this.data.concat(this.data);
    },
    finish() {
      console.log("finish");
    }
  },
  computed: {
    
  }
};
</script>

<style lang="less" scoped>
#photo {
  width: 80%;
  margin: auto;
   min-height: 100vh;
  .cell-item{
    background: white;
    margin: 1em;
    border-radius: 10px;
    text-align: center;
    padding-bottom: 0.5em;
  }
}
</style>
