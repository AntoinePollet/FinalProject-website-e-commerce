const favoris = {
  namespaced: true,
  state: {
    favoris: []
  },
  getters: {},
  mutations: {
    ADD_TO_FAV (state, item) {
      state.favoris.push(item)
    },
    REMOVE_FAV (state, item) {
      const index = state.favoris.findIndex(fav => fav.id === item.id)
      state.favoris.splice(index, 1)
    }
  },
  actions: {
    addToFav ({ commit, state }, item) {
      const el = state.favoris.findIndex(fav => fav.id === item.id)
      if (el !== -1) {
        item.favoris = false
        commit('REMOVE_FAV', item)
      } else {
        item.favoris = true
        commit('ADD_TO_FAV', item)
      }
    },
    updateFav ({ commit }, item) {
      commit('UPDATE_FAV', item)
    },
    removeFav ({ rootState, commit }, item) {
      const cart = rootState.cart.articles
      const cartEl = cart.findIndex(cartItem => cartItem.id === item.id)
      cart[cartEl].favoris = false
      commit('REMOVE_FAV', item)
    }
  }
}

export default favoris
