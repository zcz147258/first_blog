<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import { initDynamicRoutes } from '@/router'

export default {
  name: 'App',
  created(){
    initDynamicRoutes()
  }
}
</script>
<style>
html{
  height: 100%;
  width: 100%;
  padding: 0;
  margin: 0;
}
 body{
    margin: 0;
    padding:0
  }
</style>
