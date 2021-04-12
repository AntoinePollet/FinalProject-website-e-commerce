import axios from 'axios'
const url = 'http://localhost:8088/api/auth'

const user = {
  namespaced: true,
  state: {
    isAuth: false,
    role: [],
    token: '',
    username: ''
  },
  getters: {},
  mutations: {
    SIGNUP (state, payload) {},
    SIGNIN (state, payload) {
      if (payload.saveInfo) {
        const user = {
          token: payload.accessToken,
          username: payload.username,
          email: payload.email
        }
        localStorage.setItem('user', JSON.stringify(user))
      }
      state.token = payload.accessToken
      state.role = payload.roles
      state.username = payload.username
      state.isAuth = true
    },
    LOGOUT (state) {
      localStorage.clear()
      state.isAuth = false
      state.username = ''
      state.role = []
    }
  },
  actions: {
    async signup ({ commit }, payload) {
      try {
        const response = await axios
          .post(`${url}/signup`, payload)
          .then(res => {})
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
            const infos = {
              ...res.data,
              saveInfo: payload.saveInfos
            }
            commit('SIGNIN', infos)
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
