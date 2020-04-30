<template>
    <v-app>
        <v-toolbar-title>Dyplom</v-toolbar-title>
        <v-tabs>
            <v-tab to="/orders" exact>Orders</v-tab>
            <v-tab to="/products" exact>Products</v-tab>
        </v-tabs>
        <v-content>
            <div>{{this.$store.state.profile.username}}&nbsp;<a href="/logout">Выйти</a></div>
            <router-view></router-view>
        </v-content>
    </v-app>
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