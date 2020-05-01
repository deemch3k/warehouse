<template>
    <v-container>
        <v-list-item-content>
            <v-list-item class="headline mb-1">{{ productQuantity.qty }} X {{productQuantity.orderedProduct.name}}</v-list-item>
        </v-list-item-content>
    </v-container>
</template>

<script>
    import product from "../../api/product";

    export default {
        name: "OrderItem",
        props: ["productQuantity"],
        methods: {

        }
    }
</script>

<style scoped>

</style>