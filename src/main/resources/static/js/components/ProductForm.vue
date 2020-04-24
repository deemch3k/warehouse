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

    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i
            }
        }

        return -1
    }

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
                    this.$resource('/product{/id}').update({id: this.id}, product).then(result => {
                        result.json().then(data => {
                            const index = getIndex(this.products, data.id);
                            this.products.splice(index, 1, data);
                            this.name = '';
                            this.price = '';
                            this.qty = '';
                            this.description = '';
                            this.id = '';
                        })
                    })
                } else {
                    this.$resource('/product{/id}').save({}, product).then(result => {
                        result.json().then(data => {
                            //data это то что вернул сервер(продукт с айди), нужно установить айдишник на фронте в коллекцию продукты
                            this.products.push(data);
                            this.name = '';
                        })
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>