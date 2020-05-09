<template>
    <v-container fluid>
        <v-switch
                v-if="profile.role === 'ADMIN'"
                v-model="isAvailableOrders"
                :label="`Only available orders: ${isAvailableOrders.toString()}`"
        ></v-switch>
        <v-select
                v-if="profile.role === 'ADMIN' && !isAvailableOrders"
                v-model="selectedStatus"
                :items="['COMPLETED', 'PENDING', 'CANCELED']"
                label="Select Status"
                multiple
        >
        </v-select>
        <order-row
                v-for="order in selectedOrdersGetter(selectedStatus, isAvailableOrders)"
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
            ...mapGetters(['availableOrders', 'selectedOrdersGetter']),

        },
        data() {
            return {
                selectedStatus: ['PENDING'],
                isAvailableOrders: true
            }
        },
    }
</script>

<style scoped>

</style>