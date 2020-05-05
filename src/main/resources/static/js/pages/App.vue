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
                    <v-tab to="/create-user" v-if="profile.role==='ADMIN'" exact>Create User</v-tab>
                    <v-tab to="/user-list" v-if="profile.role==='ADMIN'" exact>User List</v-tab>
                </v-tabs>
            </template>

            <v-menu offset-y>
                <template v-slot:activator="{ on }">
                    <v-btn
                            color="#311B92"
                            v-on="on"
                    >
                        <v-icon>person</v-icon>
                        Username: {{profile.username}}
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item @click="goToProfile()">
                        <v-list-item-title>Profile</v-list-item-title>
                    </v-list-item>
                    <v-list-item @click="goToHistory()">
                        <v-list-item-title>History</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
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

    import {mapState} from 'vuex'

    export default {
        name: 'App',
        computed: mapState(['profile']),
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
            },
            goToProfile(){
                this.$router.push("/profile")
            },
            goToHistory(){
                this.$router.push("/user-history")
            }
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }

    }
</script>

<style>


</style>