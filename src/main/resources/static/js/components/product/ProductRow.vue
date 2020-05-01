<template>
    <v-container>
        <v-card
                class="d-flex flex-row mb-6"
                color="#B2EBF2"
                flat
                tile
        >
                <v-list-item three-line>
                    <v-list-item-content>
                        <div class="overline mb-4">ID: {{ product.id }}</div>
                        <v-list-item-title class="headline mb-1">{{product.name}}</v-list-item-title>
                        <v-list-item-subtitle>PRICE: {{ product.price }}</v-list-item-subtitle>
                        <v-list-item-subtitle>Total Amount: {{ product.totalAmount }}</v-list-item-subtitle>
                        <v-list-item-subtitle>Description: {{ product.description }}</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-card-actions>
                    <v-btn text @click="edit">Edit</v-btn>
                    <v-btn text @click="del">Delete</v-btn>
                </v-card-actions>
        </v-card>
        <v-divider/>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: "ProductRow",
        props: ['product', 'editProduct'],
        methods: {
            ...mapActions(['removeProductAction']),
            edit() {
                this.editProduct(this.product);
            },
            del() {
                this.removeProductAction(this.product);
            }
        }
    }
</script>

<style scoped>

</style>