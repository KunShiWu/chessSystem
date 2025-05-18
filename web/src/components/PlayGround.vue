<template>
        <div  class="playground">
                <GameMap />
        </div>
        
</template>

<script>

import GameMap from '@/components/GameMap'

export default{
    components:{
        GameMap,
    }
}

</script>


<style scoped>
div.playground{
    width: 70vw;
    height: 80vh;
    margin: 40px auto;
    box-sizing: border-box;
    background-color: rgba(111, 246, 77, 0.5)  ;
}


</style>