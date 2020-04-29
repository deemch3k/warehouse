<template>
    <div>
        <h3>Product</h3>
        <i>ID: {{ product.id }}</i>
        <i>NAME: {{ product.name }}</i>
        <i>PRICE: {{ product.price }}</i>
        <i>Total Amount: {{ product.totalAmount }}</i>
        <i>Description: {{ product.description }}</i>
        <input type="button" value="Edit" @click="edit">
        <input type="button" value="X" @click="del">

    </div>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "ProductRow",
        props: ['product', 'editProduct'],
        methods: {
            ...mapActions(['removeProductAction']),
            edit() {
                this.editProduct(this.product);
            },
            del() {
                this.removeProductAction(this.product);
            }
        }
    }
</script>

<style scoped>

</style>