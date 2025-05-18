<template>

    <div class="container top">
        <div class="card">
            <div class="card-body">
                <slot></slot>
            </div>
        </div>
    </div>

</template>

<script>

export default{
    name:"CardBase",
}
</script>


<style scoped>
.top{
    margin-top: 20px;
}

</style>