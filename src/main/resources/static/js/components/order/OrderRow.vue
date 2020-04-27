<template>
    <div>
    <h3>Order</h3>
    <strong>ID: {{ order.id }}</strong>
    <order-item
            v-for="productQuantity in order.products"
            :key="productQuantity.id"
            :productQuantity="productQuantity"
    ></order-item>
        <input type="button" value="Edit" @click="edit">
        <input type="button" value="X" @click="del">
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import OrderItem from "./OrderItem.vue";

    export default {
        name: "OrderRow",
        props: ['order', "editOrder"],
        components: {
            OrderItem
        },
        data(){
            return {
                product: null
            }
        },
        methods: {
            edit() {
                this.editOrder(this.order);
            },
            del(){
                //todo remove from store.
            }
        },
        computed: mapGetters(['sortedProducts']),

    }
</script>

<style scoped>

</style>