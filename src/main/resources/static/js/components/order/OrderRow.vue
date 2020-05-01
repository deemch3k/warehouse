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
                            <v-btn small @click="del">Delete order</v-btn>
                        </v-layout>
                    </v-list-item>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>

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
        data() {
            return {
                product: null
            }
        },
        methods: {
            ...mapActions(['removeOrderAction']),
            del() {
                this.removeOrderAction(this.order)
            }
        },
        computed: mapGetters(['sortedProducts']),

    }
</script>

<style scoped>

</style>