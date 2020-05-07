<template>
    <v-container fill-height fluid>
        <v-layout justify-center wrap>
            <v-flex xs12 md6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">My Orders</v-btn>
                <v-container fluid>
                    <order-row v-for="order in myOrders"
                               :key="order.id"
                               :order="order"
                    ></order-row>
                </v-container>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapState, mapGetters} from 'vuex'
    import OrderRow from "../order/OrderRow.vue";

    export default {
        name: 'Profile',
        components:{
            OrderRow,
        },
        computed: {
            ...mapState(['profile', 'orders']),
            ...mapGetters(['myOrders'])
        },
    }
</script>

<style scoped>

</style>