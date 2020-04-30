import Vue from 'vue'
import VueRouter from 'vue-router'
import OrderList from "../components/order/OrderList.vue";
import ProductList from "../components/product/ProductList.vue";

Vue.use(VueRouter)

const routes = [
    {path:'/orders', component: OrderList},
    {path:'/products', component: ProductList},
    {path:'*', component: OrderList}
]

export default new VueRouter({
    routes
})