<template>
    <div style="position: relative; width: 300px">
        <product-form :productAttr="product"></product-form>
        <product-row v-for="product in products"
                     :key="product.id"
                     :product="product"
                     :editProduct="editProduct"
                     :deleteProduct="deleteProduct"
        ></product-row>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import ProductRow from "./ProductRow.vue";
    import ProductForm from "./ProductForm.vue";
    import productApi from "../api/product";

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
            },
            deleteProduct(product) {
                productApi.remove(product.id).then(result => {
                    if (result.ok) {
                        this.products.splice(this.products.indexOf(this.product), 1)
                        location.href = location.href;
                    }
                })
            }

        }
    }
</script>

<style scoped>

</style>