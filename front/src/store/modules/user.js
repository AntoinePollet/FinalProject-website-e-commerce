import axios from 'axios'
const url = 'http://localhost:8088/api/auth'

const user = {
  namespaced: true,
  state: {
    isAuth: true,
    role: ['ROLE_ADMIN'],
    token: '',
    username: 'Antoine'
  },
  getters: {},
  mutations: {
    SIGNUP (state, payload) {},
    SIGNIN (state, payload) {
      const user = {
        token: payload.accessToken,
        username: payload.username,
        email: payload.email
      }
      localStorage.setItem('user', JSON.stringify(user))
      state.token = payload.accessToken
      state.role = payload.roles
      state.username = payload.username
      state.isAuth = true
    },
    LOGOUT (state) {
      localStorage.clear()
      state.isAuth = false
      state.role = []
    }
  },
  actions: {
    async signup ({ commit }, payload) {
      try {
        const response = await axios
          .post(`${url}/signup`, payload)
          .then(res => {
            console.log(res)
          })
        commit('SIGNUP', response)
      } catch (error) {
        throw error
      }
    },
    async signin ({ commit }, payload) {
      try {
        const response = await axios
          .post(`${url}/signin`, payload)
          .then(res => {
            console.log(res)
            commit('SIGNIN', res.data)
          })
      } catch (error) {
        throw error
      }
    },
    logout ({ commit }) {
      commit('LOGOUT')
    }
  }
}

export default user
