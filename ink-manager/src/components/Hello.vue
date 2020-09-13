<template>
  <div class="hello">
    {{ one }}在
    {{ count }}在
    {{ countAlias }}在

    {{ hello }}在

    {{ two }}在

  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'Hello',
  props: {
    msg: String
  },

  computed: {
    one(){
        return Date.now()
      },
    ...mapState({
      count: state => state.count+1,
      countAlias: 'count',
    })
  }

    
}


</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
