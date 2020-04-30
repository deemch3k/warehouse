import Vue from 'vue'
import 'api/resource'
import '@babel/polyfill'
import store from "./store/store";
import router from 'router/router'
import App from 'pages/App.vue'

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App),

})
