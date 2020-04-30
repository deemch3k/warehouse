<template>
    <div>
        <div >
            {{productQuantity.id}}
            {{productQuantity.qty}}
            {{productQuantity.orderedProduct.name}}
        </div>
    </div>
</template>

<script>
    import product from "../../api/product";

    export default {
        name: "OrderItem",
        props: ["productQuantity"],
        methods: {

        }
    }
</script>

<style scoped>

</style>