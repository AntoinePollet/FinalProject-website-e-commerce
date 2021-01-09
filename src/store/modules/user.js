const user = {
  namespaced: true,
  state: {
    isAuth: false
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
    logIn ({ commit }) {
      commit('LOG_IN')
    },
    logOut ({ commit }) {
      commit('LOG_OUT')
    }
  }
}

export default user
