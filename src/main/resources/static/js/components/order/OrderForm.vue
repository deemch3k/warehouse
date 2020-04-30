<template>
    <div>
        <label>
            <h2>Products</h2>
            <select multiple>
                <option v-for="product in sortedProducts" @click="select(product)">
                    {{product.name}}
                </option>
            </select>

            <h2>Selected Products</h2>
            <select v-if="getSelectedProducts" multiple >
                <option v-for="orderDto in getSelectedProducts()" @click="deleteProduct(orderDto.product)">
                    {{orderDto.qty}} : {{orderDto.orderedProduct.name}}
                </option>
            </select>
        </label>
        <input type="button" value="Create Order" @click="createOrder">

    </div>

    <!--    todo send product to method
        todo check if qty > 0
 -->
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
                    qty:1,
                    orderedProduct: null
                }

                if(index > -1){
                    this.selectedProducts[index].orderedProduct = product
                    this.selectedProducts[index].qty += 1
                } else {
                    orderDto.orderedProduct = product
                    this.selectedProducts.push(orderDto)
                    orderDto.qty = 1
                }

            },
            getSelectedProducts(){
                return this.selectedProducts
            },
            deleteProduct(product){
                const index = this.selectedProducts.findIndex(t => t.orderedProduct.id === product.id)
                if (index > -1) {
                    if(this.selectedProducts[index].qty > 1 ){
                        this.selectedProducts[index].qty -=1
                    } else if (this.selectedProducts[index].qty <=1 ){
                        this.selectedProducts.splice(index,1)
                    }
                }

            },
            createOrder(){
                this.addOrderAction(this.selectedProducts)
                this.selectedProducts = []
            }
        }
    }
</script>

<style scoped>

</style>