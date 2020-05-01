<template>
    <form>
        <v-text-field
                v-model="name"
                :counter="50"
                label="Name"
                required
        ></v-text-field>
        <v-text-field
                v-model="price"
                label="price"
                required
        ></v-text-field>
        <v-text-field
                v-model="totalAmount"
                label="Total Amount"
                required
        ></v-text-field>
        <v-textarea
                v-model="description"
                :counter="255"
                label="Descrption"
                required
        ></v-textarea>

        <v-btn class="mr-4" @click="save">Save</v-btn>
        <v-btn @click="clear" >clear</v-btn>
    </form>
</template>

<script>

    import {mapActions} from 'vuex'

    export default {
        name: "ProductForm",
        props: ['productAttr'],
        data: function () {
            return {
                name: '',
                price: '',
                totalAmount: '',
                id: '',
                description: ''
            }
        },
        watch: {
            productAttr(newVal, oldVal) {
                this.name = newVal.name;
                this.totalAmount = newVal.totalAmount;
                this.price = newVal.price;
                this.id = newVal.id;
                this.description = newVal.description;
            }
        },
        methods: {
            ...mapActions(['addProductAction', 'updateProductAction']),
            save() {
                const product = {id: this.id,  name: this.name, description: this.description, totalAmount: this.totalAmount, price: this.price};

                if (this.id) {
                    this.updateProductAction(product)
                } else {
                    this.addProductAction(product)
                }
                this.clear()
                this.id = '';
            },
            clear () {
                this.name = ''
                this.price = ''
                this.description = ''
                this.totalAmount = ''
            },
        },
    }
</script>

<style scoped>

</style>