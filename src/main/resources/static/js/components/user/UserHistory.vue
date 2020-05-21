<template>
    <v-container fill-height fluid>
        <v-layout justify-center wrap>
            <v-flex xs12 md6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">My History</v-btn>
                <order-row
                        v-for="order in historyOrders"
                        :key="order.id"
                        :order="order"
                        :history="true"
                >
                </order-row>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapState, mapGetters} from 'vuex'
    import OrderRow from "../order/OrderRow.vue";

    export default {
        name: 'UserHistory',
        components: {
            OrderRow,
        },
        computed: {
            ...mapState(['profile', 'orders']),
            ...mapGetters(['historyOrders'])
        },
    }
</script>

<style scoped>

</style>