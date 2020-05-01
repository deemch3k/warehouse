<template>
    <v-container>
        <product-form :productAttr="product"></product-form>
        <v-divider class="ma-6"></v-divider>
        <div class="search-wrapper">
            <v-text-field label="Search" v-model="search"></v-text-field>
        </div>
        <v-layout align-content-space-around justify-start row>
            <product-row v-for="product in filteredList"
                         :key="product.id"
                         :product="product"
                         :editProduct="editProduct"
            ></product-row>
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
        computed: {
            ...mapGetters(['sortedProducts']),
            filteredList() {
                return this.sortedProducts.filter(prod => {
                    return prod.name.toLowerCase().includes(this.search.toLowerCase())
                })
            },
        },
        data() {
            return {
                product: null,
                search: ''
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