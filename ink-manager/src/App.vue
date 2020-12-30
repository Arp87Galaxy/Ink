<template>
  <div id="app" @onload="onload()">
     <router-view></router-view>
  </div>
</template>

<script>
  

export default {
  name: 'App',
  components: {

  },
  methods:{

  }
}
</script>     

<style>
* {
margin: 0px;
padding: 0px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
