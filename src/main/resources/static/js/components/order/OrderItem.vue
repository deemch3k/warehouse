<template>
    <div>
        <div >
            {{productQuantity.product.id}}
            {{productQuantity.qty}}
            {{productQuantity.product.name}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "OrderItem",
        props: ["productQuantity"]
    }
</script>

<style scoped>

</style>