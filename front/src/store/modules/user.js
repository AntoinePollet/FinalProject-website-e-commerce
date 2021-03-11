const user = {
  namespaced: true,
  state: {
    isAuth: true,
    role: ['admin'] // admin contain one more route
  },
  getters: {},
  mutations: {
    LOG_IN (state) {
      state.isAuth = true
    },
    LOG_OUT (state) {
      state.isAuth = false
    }
  },
  actions: {
    login ({ commit }) {
      commit('LOG_IN')
    },
    logout ({ commit }) {
      commit('LOG_OUT')
    }
  }
}

export default user
