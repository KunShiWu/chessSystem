<template>

  <div class="background">
    <div class="card container " style="margin-top: 10px;">
       <NavBar/>
      </div>
       <router-view/>
  </div>


</template>


<script>
import "bootstrap/dist/js/bootstrap"
import "bootstrap/dist/css/bootstrap.css"
import NavBar from '@/components/NavBar' 


export default{
  components:{
    NavBar,

  },
  setup(){


  }


}
</script>
<style scoped>

.background{
  width: 100%;
  height: 100%;
  position: absolute;
  background-image: url("https://cdn.acwing.com/media/article/image/2022/07/07/1_59b8f162fd-background.png");
  background-size: cover;
  background-repeat: no-repeat;
}

</style>
