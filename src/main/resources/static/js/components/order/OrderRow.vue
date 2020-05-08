<template>
    <v-container fluid>
        <v-layout column>
            <v-flex xs4>
                <v-card
                        class="d-flex flex-row mb-6 pa-2 ma-4"
                        max-width="344"
                        outlined
                >
                    <v-list-item three-line>
                        <v-layout row>
                            <v-flex>Order ID: {{order.id}}</v-flex>
                            <order-item
                                    v-for="productQuantity in order.productQuantities"
                                    :key="productQuantity.id"
                                    :productQuantity="productQuantity"
                            ></order-item>
                            <v-btn v-if="profile.role === 'ADMIN'" small @click="del">Delete order</v-btn>
                            <v-row justify="center">
                                <v-dialog v-model="dialog" persistent max-width="600px">
                                    <template v-slot:activator="{ on }">
                                        <v-btn color="primary" dark v-on="on">Choose order</v-btn>
                                    </template>
                                    <order-details
                                            :order="order"
                                    ></order-details>
                                </v-dialog>
                            </v-row>
                        </v-layout>
                    </v-list-item>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>

</template>

<script>
    import {mapGetters, mapActions, mapState} from "vuex";
    import OrderItem from "./OrderItem.vue";
    import OrderDetails from "./OrderDetails.vue"

    export default {
        name: "OrderRow",
        props: ['order'],
        components: {
            OrderItem,
            OrderDetails
        },
        data() {
            return {
                product: null,
                dialog: false,
                status: 'PENDING',
                reasonForCancellation: ''
            }
        },
        methods: {
            ...mapActions(['removeOrderAction', 'updateOrderAction']),
            del() {
                this.removeOrderAction(this.order)
            }
        },
        computed: {
            ...mapGetters(['sortedProducts']),
            ...mapState(['profile'])
        },
    }
</script>

<style scoped>

</style>