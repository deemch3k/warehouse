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
                v-for="order in dynamicOrders"
                :key="order.id"
                :order="order"
        ></order-row>
    </v-container>
</template>

<script>
    import OrderRow from "./OrderRow.vue";
    import {mapState, mapGetters, mapMutations} from 'vuex'
    import {addHandler} from "../../util/ws";

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
                isAvailableOrders: true,
                dynamicOrders: []
            }
        },
        created() {
            addHandler(data => {
                let index = this.$store.state.orders.findIndex(o => o.id === data.id)
                console.log(data)
                if(index > -1){
                    this.$store.commit('updateOrderMutation', data)
                } else {
                    this.$store.commit('addOrderMutation', data)
                }
            })

        },
        methods: {
            ...mapMutations(['updateOrderMutation', 'addOrderMutation']),
            getOrders(){
                return this.selectedOrdersGetter(this.selectedStatus, this.isAvailableOrders)
            }
        },
        beforeMount() {
            this.dynamicOrders = this.getOrders()
            console.log(this.dynamicOrders)
        }
    }
</script>

<style scoped>

</style>