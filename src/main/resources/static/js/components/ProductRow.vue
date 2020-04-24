<template>
    <div>
        <h3>Product</h3>
        <i>ID: {{ product.id }}</i>
        <i>NAME: {{ product.name }}</i>
        <i>PRICE: {{ product.price }}</i>
        <i>Quantity: {{ product.qty }}</i>
        <i>Description: {{ product.description }}</i>
        <input type="button" value="Edit" @click="edit">
        <input type="button" value="X" @click="del">

        </div>
</template>

<script>
    export default {
        name: "ProductRow",
        props: ['product', 'editProduct','deleteProduct', 'products'],
        methods: {
            edit() {
                this.editProduct(this.product);
            },
            del() {
                this.deleteProduct(this.product);
            }
        }
    }
</script>

<style scoped>

</style>