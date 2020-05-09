<template>
    <v-container fluid>
        <v-card>
            <v-list-item>
                <v-list-item-content>
                    <div class="overline mb-4">ID:{{order.id}}</div>
                    <div class="overline mb-4">status:{{order.status}}</div>
                    <div v-if="order.user !== null" class="overline mb-4">User:{{order.user.username}}</div>
                    <v-list-item-title class="headline mb-1">Order #{{order.id}}</v-list-item-title>
                    <v-list-item-subtitle
                            v-for="pq in order.productQuantities"
                            :key="pq.id"
                    >
                        {{ pq.qty }} X {{pq.orderedProduct.name}}
                    </v-list-item-subtitle>
                    <v-card-actions class="row">
                        <v-flex class="text-xs-right">
                            <v-btn
                                    v-if="profile.role === 'ADMIN'"
                                    color="primary"
                                    dark
                                    small
                                    @click="del"
                            >
                                Delete order
                            </v-btn>
                            <order-choose
                                    :order="order"
                            ></order-choose>
                        </v-flex>
                    </v-card-actions>
                </v-list-item-content>
            </v-list-item>
        </v-card>
    </v-container>
</template>

<script>
    import {mapGetters, mapActions, mapState} from "vuex";
    import OrderChoose from "./OrderChoose.vue"

    export default {
        name: "OrderRow",
        props: ['order'],
        components: {
            OrderChoose
        },
        data() {
            return {
                product: null,
                status: 'PENDING',
                reasonForCancellation: ''
            }
        },
        methods: {
            ...mapActions(['removeOrderAction', 'updateOrderAction']),
            del() {
                this.removeOrderAction(this.order)
            },
        },
        computed: {
            ...mapGetters(['sortedProducts']),
            ...mapState(['profile'])
        },
    }
</script>

<style scoped>

</style>