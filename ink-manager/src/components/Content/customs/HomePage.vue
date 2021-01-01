<!--  -->
<template>
<div class='homepage'>
    homepage
</div>
</template>

<script>
//	这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//	例如：import 《组件名称》 from '《组件路径》';

export default {
    name:"HomePage",
//	import引入的组件需要注入到对象中才能使用
    created(){
        this.$http({
          url:'core/sys/article/list',
          method:'post',
          data:{
              page: '1',
              limit: '1'
          }
        }).then((data) => {
          console.log("Article data is:")
          console.log(data.data)
        
        }).catch({

        })
    }

}
</script>
<style scoped>


</style>