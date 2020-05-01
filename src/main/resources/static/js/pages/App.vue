<template>
    <v-app>
        <v-app-bar app
                color="#1976D2"
                dark
        >
            <v-toolbar-title>Dyplom</v-toolbar-title>
            <v-spacer></v-spacer>
            <template v-slot:extension>
                <v-tabs align-with-title class="accent-2" centered>
                    <v-tab to="/orders" exact>Orders</v-tab>
                    <v-tab to="/products" exact>Products</v-tab>
                </v-tabs>
            </template>
            <v-label color="#263238">{{this.$store.state.profile.username}}</v-label>
            <v-btn icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
            <v-btn
                    v-scroll="onScroll"
                    v-show="fab"
                    fab
                    dark
                    fixed
                    bottom
                    right
                    color="primary"
                    @click="toTop"
            >
                <v-icon>keyboard_arrow_up</v-icon>
            </v-btn>
        </v-content>
    </v-app>
</template>

<script>
    import ProductList from "../components/product/ProductList.vue";
    import OrderList from "../components/order/OrderList.vue";

    export default {
        components: {
            OrderList,
            ProductList
        },
        data: () => ({
            fab: false
        }),
        methods: {
            onScroll (e) {
                if (typeof window === 'undefined') return
                const top = window.pageYOffset ||   e.target.scrollTop || 0
                this.fab = top > 20
            },
            toTop () {
                this.$vuetify.goTo(0)
            }
        }

    }
</script>

<style>


</style>