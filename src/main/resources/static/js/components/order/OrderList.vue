<template>
    <v-container fluid>
        <order-row v-for="order in this.availableOrders"
                   :key="order.id"
                   :order="order"
        ></order-row>
    </v-container>
</template>

<script>
    import OrderRow from "./OrderRow.vue";
    import {mapState, mapGetters} from 'vuex'

    export default {
        name: "OrderList",
        components: {
            OrderRow,
        },
        computed: {
            ...mapState(['profile', 'orders']),
            ...mapGetters(['availableOrders'])
        },
        data() {
            return {
                order: null,
            }
        },
    }
</script>

<style scoped>

</style>