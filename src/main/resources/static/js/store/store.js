import Vue from "vue"
import Vuex from "vuex"
import productApi from "../api/product"
import orderApi from '../api/order'
import userApi from '../api/user'

Vue.use(Vuex)


export default new Vuex.Store({
    state: {
        products: frontendData.products,
        profile: frontendData.profile,
        orders: frontendData.orders,
        users: frontendData.users,
        reports: frontendData.reports
    },
    getters: {
        sortedProducts: state => state.products.sort((a, b) => -(a.id - b.id)),
        availableOrders: state => state.orders.filter(o => o.user === null),
        myOrders: state => state.orders.filter(o => o.user !== null && o.user.username === state.profile.username),
        myPendingOrders: state => state.orders.filter(o =>
            o.user !== null &&
            o.user.username === state.profile.username &&
            o.status === 'PENDING'),
        historyOrders: state => state.orders.filter(o =>
            o.user !== null &&
            o.user.id === state.profile.id &&
            (o.status === 'COMPLETED' || o.status === 'CANCELED')
        ),
        selectedOrdersGetter: (state, getters) => (selectedStatus, isAvailableOrders) => {

            if (isAvailableOrders) {
                return getters.availableOrders
            } else {
                return state.orders.filter(o =>
                    o.user !== null &&
                    selectedStatus.includes(o.status))
            }
        },
        sortedReports: state => state.reports.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addProductMutation(state, product) {
            state.products = [
                ...state.products,
                product
            ]
        },
        updateProductMutation(state, product) {
            const index = state.products.findIndex(item => item.id === product.id)

            state.products = [
                ...state.products.slice(0, index),
                product,
                ...state.products.slice(index + 1)
            ]
        },
        removeProductMutation(state, product) {
            const index = state.products.findIndex(item => item.id === product.id)

            if (index > -1) {
                state.products = [
                    ...state.products.slice(0, index),
                    ...state.products.slice(index + 1)
                ]
            }
        },
        addOrderMutation(state, order) {
            state.orders = [
                ...state.orders,
                order
            ]
        },
        updateOrderMutation(state, order) {
            const index = state.orders.findIndex(item => item.id === order.id)
            state.orders = [
                ...state.orders.slice(0, index),
                ...order,
                ...state.orders.slice(index + 1)
            ]
        },
        removeOrderMutation(state, order) {
            const index = state.orders.findIndex(item => item.id === order.id)
            console.log(order)
            order.productQuantities.forEach(pq => {
                state.products.forEach(p => {
                    if (pq.orderedProduct.name === p.name) {
                        p.totalAmount += pq.qty
                    }
                })
            });
            if (index > -1) {
                state.orders = [
                    ...state.orders.slice(0, index),
                    ...state.orders.slice(index + 1)
                ]
            }
        },
        addUserMutation(state, user) {
            state.users = [
                ...state.users,
                user
            ]
        },
        cancellationOrderMutation(state, cancellationReport) {
            state.reports = [
                ...state.reports,
                cancellationReport
            ]
        }
    },
    actions: {
        async addProductAction({commit, state}, product) {
            const result = await productApi.add(product)
            const data = await result.json()
            const index = state.products.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateProductMutation', data)
            } else {
                commit('addProductMutation', data)
            }

        },
        async updateProductAction({commit}, product) {
            const result = await productApi.update(product)
            const data = await result.json()
            commit('updateProductMutation', data)

        },
        async removeProductAction({commit}, product) {
            const result = await productApi.remove(product.id)
            if (result.ok) {
                commit('removeProductMutation', product)
            }
        },
        async addOrderAction({commit, state}, orderDto) {
            const result = await orderApi.add(orderDto)
            const data = await result.json()
            const index = state.orders.findIndex(item => item.id === orderDto.orderedProducts.id)

            if (index > -1) {
                commit('updateOrderMutation', data)
            } else {
                commit('addOrderMutation', data)
            }

        },
        async updateOrderAction({commit}, order) {
            const result = await orderApi.update(order)
            const data = await result.json()
            commit('updateOrderMutation', data)
        },
        async removeOrderAction({commit}, order) {
            const result = await orderApi.remove(order.id)
            if (result.ok) {
                commit('removeOrderMutation', order)
            }
        },
        async addUserAction({commit}, user) {
            const result = await userApi.add(user)
            if (result.ok) {
                commit('addUserMutation', user)
                return 'OK'
            }
        },
        async cancellationOrderAction({commit}, cancellationReportDto) {
           const result = await orderApi.cancelOrder(cancellationReportDto)
            if(result.ok){
                commit('cancellationOrderMutation', cancellationReportDto)
            }
        }
    }
})