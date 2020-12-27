const cart = {
  namespaced: true,
  state: {
    cart: [],
    articles: []
  },
  getters: {
    getCart: state => {
      return state.cart
    },
    getArticle: state => id => {
      return state.articles.find(item => item.id === id)
    }
  },
  mutations: {
    ADD_TO_CART (state, item) {
      state.cart.push(item)
    },
    UPDATE_CART (state, item) {
      state.cart.splice(
        state.cart.findIndex(article => article.id === item.id),
        1,
        item
      )
    },
    INIT_CART (state, items) {
      state.articles = []
      items.map(item => {
        state.articles.push(item)
      })
    },
    ADD_TO_CURRENT_ARTICLE (state, item) {
      state.currentArticle = item
    },
    REMOVE_CART_ITEM (state, index) {
      state.cart.splice(index, 1)
    }
  },
  actions: {
    addToCart ({ commit, state }, item) {
      const el = state.cart.findIndex(article => article.id === item.id)
      if (el !== -1) {
        const newQuantity = item.quantity + state.cart[el].quantity
        const newItem = {
          ...item,
          quantity: newQuantity <= 10 ? newQuantity : 10
        }
        commit('UPDATE_CART', newItem)
        if (newQuantity > 10) {
          return {
            type: 'warning',
            message: 'quantité set à 10'
          }
        }
        return {
          type: 'success',
          message: 'item updated'
        }
      } else {
        commit('ADD_TO_CART', item)
        return {
          type: 'success',
          message: 'item added'
        }
      }
    },
    initCart ({ commit }, items) {
      commit('INIT_CART', items)
    },
    removeCartItem ({ commit }, index) {
      commit('REMOVE_CART_ITEM', index)
    }
  }
}
export default cart
