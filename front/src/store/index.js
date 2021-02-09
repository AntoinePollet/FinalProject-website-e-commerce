import Vue from 'vue'
import Vuex from 'vuex'
import cart from './modules/cart'
import user from './modules/user'
import favoris from './modules/favoris'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    cart,
    user,
    favoris
  }
})
