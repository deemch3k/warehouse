<template>
    <div>
    <h3>Order</h3>
    <strong>ID: {{ order.id }}</strong>
    <order-item
            v-for="productQuantity in order.productQuantities"
            :key="productQuantity.id"
            :productQuantity="productQuantity"
    ></order-item>
        <input type="button" value="X" @click="del">
    </div>
</template>

<script>
    import {mapGetters, mapActions} from "vuex";
    import OrderItem from "./OrderItem.vue";

    export default {
        name: "OrderRow",
        props: ['order'],
        components: {
            OrderItem
        },
        data(){
            return {
                product: null
            }
        },
        methods: {
            ...mapActions(['removeOrderAction']),
            del(){
                this.removeOrderAction(this.order)
            }
        },
        computed: mapGetters(['sortedProducts']),

    }
</script>

<style scoped>

</style>