<template>
    <div>
        <div>{{this.$store.state.profile.username}}&nbsp;<a href="/logout">Выйти</a></div>
        <product-list/>
    </div>
</template>

<script>
    import ProductList from "../components/ProductList.vue";

    export default {
        components: {
            ProductList
        },

    }
</script>

<style>


</style>