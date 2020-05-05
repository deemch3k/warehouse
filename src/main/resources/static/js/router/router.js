import Vue from 'vue'
import VueRouter from 'vue-router'
import OrderList from "../components/order/OrderList.vue";
import ProductList from "../components/product/ProductList.vue";
import UserCreation from "../components/user/UserCreation.vue";
import UserList from "../components/user/UserList.vue";
import Profile from "../components/profile/Profile.vue";

Vue.use(VueRouter)

const routes = [
    {path:'/orders', component: OrderList},
    {path:'/products', component: ProductList},
    {path:'/create-user', component: UserCreation},
    {path:'/user-list', component: UserList},
    {path:'/profile', component: Profile},
    {path:'*', component: OrderList}
]

export default new VueRouter({
    mode: 'history',
    routes
})