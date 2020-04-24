<template>
    <div style="position: relative; width: 300px">
        <product-form :productAttr="product"></product-form>
        <product-row v-for="product in sortedProducts"
                     :key="product.id"
                     :product="product"
                     :editProduct="editProduct"
        ></product-row>
    </div>
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
        }
    }
</script>

<style scoped>

</style>