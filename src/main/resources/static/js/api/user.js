import Vue from 'vue'

const users = Vue.resource('/users{/id}')

export default {
    add: user => users.save({}, user),
    update: user => users.update({id: user.id}, user),
    remove: id => users.remove({id}),
}