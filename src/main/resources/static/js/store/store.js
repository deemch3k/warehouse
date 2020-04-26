import Vue from "vue"
import Vuex from "vuex"
import productApi from "../api/product"

Vue.use(Vuex)


export default new Vuex.Store({
    state: {
        products: frontendData.products,
        profile: frontendData.profile
    },
    getters: {
        sortedProducts: state => state.products.sort((a, b) => -(a.id - b.id))
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
    },
    actions: {
        async addProductAction({commit, state}, product) {
            const result = await productApi.add(product)
            const data = await result.json()
            const index = state.products.findIndex(item => item.id === data.id)

            if(index > -1){
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
    }
})