<template>
    <div style="position: relative; width: 300px">
        <router-link to="/products">to products</router-link>
        <order-form :order-attr="order"></order-form>
        <order-row v-for="order in this.$store.state.orders"
                     :key="order.id"
                     :order="order"
        ></order-row>
    </div>
</template>

<script>
    import OrderRow from "./OrderRow.vue";
    import OrderForm from "./OrderForm.vue";

    export default {
        name: "OrderList",
        components: {
            OrderForm,
            OrderRow
        },
        data(){
            return{
                order: null
            }
        },
    }
</script>

<style scoped>

</style>