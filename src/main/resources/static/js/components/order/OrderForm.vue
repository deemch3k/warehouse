<template>
    <v-container class="my-5">
        <v-btn large right color="primary" class="float-right ml-10 pa-2" @click="createOrder">Create order</v-btn>
        <v-layout row align-center wrap justify-center>
            <v-flex xs4 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Products</v-btn>
                <v-flex>
                    <v-card
                            class="d-flex flex-row mb-6 pa-2 ma-4"
                            color="#B2EBF2"
                            flat
                            tile
                            v-for="product in list" @click="select(product)"
                            :key="product.id"
                    >
                        <v-list-item three-line>
                            <v-list-item-content>
                                <div class="overline mb-4">ID: {{ product.id }}</div>
                                <v-list-item-title class="headline mb-1">{{product.name}}</v-list-item-title>
                                <v-list-item-subtitle>PRICE: {{ product.price }}</v-list-item-subtitle>
                                <v-list-item-subtitle>Total Amount: {{ product.totalAmount }}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </v-flex>
            </v-flex>
            <v-flex xs4 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Selected Products</v-btn>
                <v-flex>
                    <v-card
                            class="d-flex flex-row mb-6 pa-2 ma-4"
                            color="#B2EBF2"
                            flat
                            tile
                            v-if="getSelectedProducts"
                            v-for="orderDto in getSelectedProducts()"
                            :key="orderDto.orderedProduct.id"
                    >
                        <v-list-item three-line>
                            <v-list-item-content>
                                <v-list-item-title class="headline mb-1">{{orderDto.orderedProduct.name}}
                                </v-list-item-title>
                                <v-list-item-subtitle>QTY: {{orderDto.qty}}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                        <v-card-actions>
                            <v-btn class="font-weight-bold" text @click="deleteProduct(orderDto.orderedProduct)">undo
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-flex>
            <v-flex xs4 align-self-start>
                <v-btn v-if="!clientInfo" block top class="primary pa-2 ma-5">Client Info Form</v-btn>
                <v-flex>
                    <client-info-form
                            v-if="!clientInfo"
                            @clientInfo="setClientInfo"
                    >
                    </client-info-form>
                    <v-alert
                            :value="clientInfo != null"
                            class="ma-4"
                            dense
                            text
                            type="success"
                    >
                        Client Info has been added <strong>successfully</strong>.
                    </v-alert>
                </v-flex>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters, mapActions} from "vuex";
    import ClientInfoForm from "../clientInfo/ClientInfoForm.vue";

    export default {
        name: "OrderForm",
        components: {ClientInfoForm},
        data() {
            return {
                selectedProducts: [],
                loading: false,
                list: null,
                clientInfo: null
            }
        },
        computed: mapGetters(['sortedProducts']),
        beforeMount() {
            this.list = this.sortedProducts
        },
        methods: {
            ...mapActions(['addOrderAction']),
            save() {

            },
            select(product) {
                const index = this.selectedProducts.findIndex(item => item.orderedProduct.id === product.id)

                const orderDto = {
                    qty: 1,
                    orderedProduct: null
                }

                if (index > -1) {
                    if (product.totalAmount > 0) {
                        this.selectedProducts[index].qty += 1
                        product.totalAmount -= 1
                    }
                } else {
                    if (product.totalAmount > 0) {
                        orderDto.orderedProduct = product
                        this.selectedProducts.push(orderDto)
                        product.totalAmount -= 1
                    }
                }
            },
            getSelectedProducts() {
                return this.selectedProducts
            },
            deleteProduct(product) {
                const indexSelected = this.selectedProducts.findIndex(t => t.orderedProduct.id === product.id)
                const indexList = this.list.findIndex(t => t.id === product.id)

                if (indexSelected > -1) {
                    if (this.selectedProducts[indexSelected].qty > 1) {
                        this.selectedProducts[indexSelected].qty -= 1
                    } else if (this.selectedProducts[indexSelected].qty <= 1) {
                        this.selectedProducts.splice(indexSelected, 1)
                    }
                    this.list[indexList].totalAmount += 1
                }

            },
            createOrder() {
                const orderDto ={
                    orderedProducts: this.selectedProducts,
                    clientInfo: this.clientInfo
                }
                this.addOrderAction(orderDto)
                this.selectedProducts = []
                this.clientInfo = null
            },
            setClientInfo(clientInfo) {
                this.clientInfo = clientInfo
            }
        }
    }
</script>

<style scoped>

</style>