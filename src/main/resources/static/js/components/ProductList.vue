<template>
    <div style="position: relative; width: 300px">
        <product-form :products="products" :productAttr="product"></product-form>
        <product-row v-for="product in products"
                     :key="product.id"
                     :product="product"
                     :editProduct="editProduct"
                     :deleteProduct="deleteProduct"
                     :products="products"></product-row>
        </div>
</template>

<script>

    import ProductRow from "./ProductRow.vue";
    import ProductForm from "./ProductForm.vue";

    export default {
        name: "ProductList",
        components: {
            ProductRow,
            ProductForm
        },
        props: ['products'],
        data() {
            return {
                product: null
            }
        },
        methods: {
            editProduct(product) {
                this.product = product;
            },
            deleteProduct(product){
                this.$resource('/product{/id}').remove({id: product.id}).then(result => {
                    if(result.ok){
                        this.products.splice(this.products.indexOf(this.product), 1)
                        location.href=location.href;
                    }
                })
            }

        }
    }
</script>

<style scoped>

</style>