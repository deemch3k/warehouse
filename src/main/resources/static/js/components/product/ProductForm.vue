<template>
    <div>
        <input type="text" placeholder="name" v-model="name"/>
        <input type="text" placeholder="price" v-model="price"/>
        <input type="text" placeholder="qty" v-model="qty"/>
        <textarea placeholder="description" v-model="description"/>
        <input type="button" value="Save" @click="save">
    </div>
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
                qty: '',
                id: '',
                description: ''
            }
        },
        watch: {
            productAttr(newVal, oldVal) {
                this.name = newVal.name;
                this.qty = newVal.qty;
                this.price = newVal.price;
                this.id = newVal.id;
                this.description = newVal.description;
            }
        },
        methods: {
            ...mapActions(['addProductAction', 'updateProductAction']),
            save() {
                const product = {name: this.name, qty: this.qty, price: this.price, description: this.description};

                if (this.id) {
                    this.updateProductAction(product)
                } else {
                    this.addProductAction(product)
                }
                this.name = '';
                this.price = '';
                this.qty = '';
                this.description = '';
                this.id = '';
            }
        }
    }
</script>

<style scoped>

</style>