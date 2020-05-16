import Vue from 'vue'

const orders = Vue.resource('/order{/id}')

export default {
    add: orderDto => orders.save({}, orderDto),
    update: order => orders.update({id: order.id}, order),
    remove: id => orders.remove({id}),
    cancelOrder: dto => Vue.http.post('/order/cancel', dto),


}