<template>
    <v-container>
        <v-layout align-content-space-around justify-start column>
            <div style="position: relative; width: 300px">
                <router-link to="/orders">To orders</router-link>
                <product-form :productAttr="product"></product-form>
                <product-row v-for="product in sortedProducts"
                             :key="product.id"
                             :product="product"
                             :editProduct="editProduct"
                ></product-row>
            </div>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import ProductRow from "./ProductRow.vue";
    import ProductForm from "./ProductForm.vue";

    export default {
        name: "ProductList",
        components: {
            ProductRow,
            ProductForm
        },
        computed: mapGetters(['sortedProducts']),
        data() {
            return {
                product: null
            }
        },
        methods: {
            editProduct(product) {
                this.product = product;
            }
        },
    }
</script>

<style scoped>

</style>