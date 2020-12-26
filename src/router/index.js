import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import Cart from '../components/Cart.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/cart',
      name: 'cart',
      component: Cart
    }
  ]
})
