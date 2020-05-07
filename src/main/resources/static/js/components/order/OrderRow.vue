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
                                    <v-card>
                                        <v-card-title>
                                            <span class="headline">Order: {{order.id}}</span>
                                        </v-card-title>
                                        <v-card-text>
                                            <v-container>
                                                <v-row>
                                                    <v-col cols="12" sm="6" md="4" v-for="pq in order.productQuantities"
                                                           :key="pq.id">
                                                        <v-list>
                                                            <v-list-item-title>Product</v-list-item-title>
                                                            <v-list-item-subtitle>Quantity: {{pq.qty}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Quantity: {{pq.qty}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Name: {{pq.orderedProduct.name}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Placement: {{pq.orderedProduct.placement}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Expiration Date: {{pq.orderedProduct.expirationDate}}</v-list-item-subtitle>
                                                        </v-list>
                                                    </v-col>
                                                    <v-spacer></v-spacer>
                                                    <v-col cols="12">
                                                        <v-list>
                                                            <v-list-item-title>Client Info</v-list-item-title>
                                                            <v-list-item-subtitle>Name: {{order.clientInfo.name}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Surname: {{order.clientInfo.surname}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Address: {{order.clientInfo.address}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Postcode: {{order.clientInfo.postcode}}</v-list-item-subtitle>
                                                            <v-list-item-subtitle>Number: {{order.clientInfo.number}}</v-list-item-subtitle>
                                                        </v-list>
                                                    </v-col>
                                                    <v-col cols="12" sm="6">
                                                        <v-select
                                                                :items="['Completed', 'Pending', 'Canceled']"
                                                                v-model="status"
                                                                required
                                                        ></v-select>
                                                        <v-select
                                                                v-if="status === 'Canceled'"
                                                                :items="['item1', 'item2', 'item3']"
                                                                v-model="reasonForCancellation"
                                                                label="Reason for Cancellation"
                                                                required
                                                        ></v-select>
                                                    </v-col>
                                                </v-row>
                                            </v-container>
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                                            <v-btn color="blue darken-1" text @click="bindOrder(order)">Save</v-btn>
                                        </v-card-actions>
                                    </v-card>
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

    export default {
        name: "OrderRow",
        props: ['order'],
        components: {
            OrderItem
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
            },
            bindOrder() {
                this.order.status = this.status
                if(this.status === 'Canceled'){
                    alert(this.reasonForCancellation)
                }
                this.order.user = this.profile
                console.log(this.order)
                this.updateOrderAction(this.order)
                this.dialog = false
                this.updateOrders()
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