<template>
    <div>
        <div>{{this.$store.state.profile.username}}&nbsp;<a href="/logout">Выйти</a></div>
        <product-list/>
        <order-list/>
    </div>
</template>

<script>
    import ProductList from "../components/product/ProductList.vue";
    import OrderList from "../components/order/OrderList.vue";

    export default {
        components: {
            OrderList,
            ProductList
        },

    }
</script>

<style>


</style>