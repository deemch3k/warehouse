import Vue from 'vue'

const orders = Vue.resource('/order{/id}')

export default {
    add: orderedProducts => orders.save({}, orderedProducts),
    update: order => orders.update({id: order.id}, order),
    remove: id => orders.remove({id}),
}