<template>
    <product-list/>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import ProductList from "../components/ProductList.vue";

    export default {
        components: {
            ProductList
        },

    }
</script>

<style>


</style>