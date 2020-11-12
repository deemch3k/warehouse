import Vue from 'vue'
import VueRouter from 'vue-router'
import OrderList from "../components/order/OrderList.vue";
import ProductList from "../components/product/ProductList.vue";
import UserCreation from "../components/user/UserCreation.vue";
import UserList from "../components/user/UserList.vue";
import UserHistory from "../components/user/UserHistory.vue";
import Profile from "../components/profile/Profile.vue";
import OrderForm from "../components/order/OrderForm.vue";
import CancellationReportList from "../components/report/CancellationReportList.vue";
import ShippedOrders from "../components/report/ShippedOrders.vue";

Vue.use(VueRouter)

const routes = [
    {path:'/orders', component: OrderList},
    {path:'/order-form', component: OrderForm},
    {path:'/products', component: ProductList},
    {path:'/create-user', component: UserCreation},
    {path:'/user-list', component: UserList},
    {path:'/user-history', component: UserHistory},
    {path:'/profile', component: Profile},
    {path:'/cancellation-reports', component: CancellationReportList},
    {path:'/shipped-orders', component: ShippedOrders},
    {path:'*', component: OrderList}
]

export default new VueRouter({
    mode: 'history',
    routes
})