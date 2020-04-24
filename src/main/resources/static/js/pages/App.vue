<template>
        <product-list :products="products"/>
</template>

<script>

    import ProductList from "../components/ProductList.vue";

    export default {
        components: {
            ProductList
        },
        data() {
            return {
                products: frontendData.products
            }
        },
    }
</script>

<style>


</style>