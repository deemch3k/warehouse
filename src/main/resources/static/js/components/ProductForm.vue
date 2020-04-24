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

    import productApi from "../api/product";

    export default {
        name: "ProductForm",
        props: ['products', 'productAttr'],
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
            save() {
                const product = {name: this.name, qty: this.qty, price: this.price, description: this.description};
                if (this.id) {
                   productApi.update(product).then(result => {
                        result.json().then(data => {
                            const index = this.products.findIndex(item => item.id === data.id)
                            this.products.splice(index, 1, data);
                        })
                    })
                } else {
                    productApi.add(product).then(result => {
                        result.json().then(data => {
                            //data это то что вернул сервер(продукт с айди), нужно установить айдишник на фронте в коллекцию продукты
                            this.products.push(data);
                        })
                    })
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