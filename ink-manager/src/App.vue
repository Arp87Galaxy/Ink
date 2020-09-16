<template>
  <div id="app">
    <router-view name='login'></router-view>
    <router-view name='navbar'></router-view>
    <router-view name='sidebar'></router-view>
    <router-view name='main'></router-view>  
  </div>
</template>

<script>
  

export default {
  name: 'App',
  components: {

  }
}
</script>     

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
