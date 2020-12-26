const cart = {
  namespaced: true,
  state: {
    cart: []
  },
  getters: {
    getCart: state => {
      return state.cart
    }
  },
  mutations: {
    ADD_TO_CART (state, item) {
      state.cart.push(item)
    },
    REMOVE_CART_ITEM (state, index) {
      state.cart.splice(index, 1)
    }
  },
  actions: {
    addToCart ({ commit }, item) {
      commit('ADD_TO_CART', item)
    },
    removeCartItem ({ commit }, index) {
      commit('REMOVE_CART_ITEM', index)
    }
  }
}
export default cart
