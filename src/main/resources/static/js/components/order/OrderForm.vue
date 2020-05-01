<template>
    <v-container class="my-5">
        <v-btn large right color="primary" class="float-right ml-10 pa-2" @click="createOrder">Create order</v-btn>
        <v-layout row align-center wrap justify-center>
            <v-flex xs6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Products</v-btn>
                <v-flex xs12>
                    <v-card
                            class="d-flex flex-row mb-6 pa-2 ma-4"
                            color="#B2EBF2"
                            flat
                            tile
                            v-for="product in sortedProducts" @click="select(product)"
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
            <v-flex xs6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Selected Products</v-btn>
                <v-flex xs12>
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
                            <v-btn text @click="deleteProduct(orderDto.orderedProduct)">-</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-flex>
        </v-layout>



        <!--        <label>-->
        <!--            <h2>Products</h2>-->
        <!--            <select multiple>-->
        <!--                <option v-for="product in sortedProducts" @click="select(product)">-->
        <!--                    {{product.name}}-->
        <!--                </option>-->
        <!--            </select>-->

        <!--            <h2>Selected Products</h2>-->
        <!--            <select v-if="getSelectedProducts" multiple>-->
        <!--                <option v-for="orderDto in getSelectedProducts()" @click="deleteProduct(orderDto.product)">-->
        <!--                    {{orderDto.qty}} : {{orderDto.orderedProduct.name}}-->
        <!--                </option>-->
        <!--            </select>-->
        <!--        </label>-->

    </v-container>
</template>

<script>
    import {mapGetters, mapActions} from "vuex";

    export default {
        name: "OrderForm",
        data() {
            return {
                selectedProducts: [],
            }
        },
        computed: mapGetters(['sortedProducts']),
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
                    this.selectedProducts[index].orderedProduct = product
                    this.selectedProducts[index].qty += 1
                } else {
                    orderDto.orderedProduct = product
                    this.selectedProducts.push(orderDto)
                    orderDto.qty = 1
                }

            },
            getSelectedProducts() {
                return this.selectedProducts
            },
            deleteProduct(product) {
                const index = this.selectedProducts.findIndex(t => t.orderedProduct.id === product.id)
                if (index > -1) {
                    if (this.selectedProducts[index].qty > 1) {
                        this.selectedProducts[index].qty -= 1
                    } else if (this.selectedProducts[index].qty <= 1) {
                        this.selectedProducts.splice(index, 1)
                    }
                }

            },
            createOrder() {
                this.addOrderAction(this.selectedProducts)
                this.selectedProducts = []
            }
        }
    }
</script>

<style scoped>

</style>