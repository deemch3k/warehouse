<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn color="primary" dark small v-on="on">Choose order</v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Order: {{order.id}}</span>
            </v-card-title>
            <v-card-subtitle>Status: {{order.status}}</v-card-subtitle>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="4" v-for="pq in order.productQuantities"
                               :key="pq.id">
                            <v-list>
                                <v-list-item-title>Product</v-list-item-title>
                                <v-list-item-subtitle>Name: {{pq.orderedProduct.name}}</v-list-item-subtitle>
                                <v-list-item-subtitle>Quantity: {{pq.qty}}</v-list-item-subtitle>
                                <v-list-item-subtitle>Placement: {{pq.orderedProduct.placement}}
                                </v-list-item-subtitle>
                                <v-list-item-subtitle>Expiration Date: {{pq.orderedProduct.expirationDate}}
                                </v-list-item-subtitle>
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
                        <v-col cols="12" sm="6" v-if="order.status === 'PENDING' ">
                            <v-select
                                    :items="['COMPLETED', 'PENDING', 'CANCELED']"
                                    v-model="status"
                                    required
                            ></v-select>
                            <v-select
                                    v-if="status === 'CANCELED'"
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
</template>

<script>

    import {mapActions, mapState} from 'vuex'

    export default {


        name: 'OrderChoose',
        props: ['order'],
        data() {
            return {
                status: this.order.status,
                reasonForCancellation: '',
                dialog: false
            }
        },
        computed: {
            ...mapState(['profile'])
        },
        methods: {
            ...mapActions(['updateOrderAction', 'cancellationOrderAction']),
            bindOrder() {
                this.order.status = this.status
                if (this.status === 'CANCELED') {
                    var cancellationReportDto = {
                        orderId: this.order.id,
                        reasonForCancellation: this.reasonForCancellation
                    }
                    this.cancellationOrderAction(cancellationReportDto)
                }
                this.order.user = this.profile
                this.updateOrderAction(this.order)
                this.dialog = false
            },
        }

    }

</script>

<style scoped>

</style>