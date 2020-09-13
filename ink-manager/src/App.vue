<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
        <!-- <router-view name='hello'></router-view>   -->
    <router-view name='helloworld'></router-view>
    <router-view name='hello'></router-view>  
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
