import axios from 'axios'
const url = 'https://kyokyubackend.herokuapp.com/api/v1/'

const user = {
  namespaced: true,
  state: {
    isAuth: false,
    commands: [],
    role: [],
    token: '',
    username: '',
    user: {},
    commentArticle: []
  },
  getters: {
    hasOrdered: state => id => {
      const orders = state.commands
        .reduce((acc, item) => {
          acc = acc.concat(item.articles)
          return acc
        }, [])
        .reduce((acc, item) => {
          if (acc[item.idArticle]) {
            acc[item.idArticle] = acc[item.idArticle] + item.quantity
          } else {
            acc[item.idArticle] = item.quantity
          }
          return acc
        }, {})

      return orders.hasOwnProperty(id)
    },
    hasCommented: state => {
      console.log(state.username)
      if (state.commentArticle.length) {
        return (
          state.commentArticle.find(
            item => item.username === state.username
          ) !== undefined
        )
      } else {
        return false
      }
    }
  },
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
      state.user['username'] = payload.username
      state.user['email'] = payload.email
      state.isAuth = true
    },
    LOGOUT (state) {
      localStorage.clear()
      state.isAuth = false
      state.username = ''
      state.role = []
    },
    GET_COMMAND (state, res) {
      for (let i in res) {
        state.commands.push(res[i])
      }
    },
    GET_COMMENTS (state, response) {
      state.commentArticle = []
      for (let i in response) {
        state.commentArticle.push(response[i])
      }
    },
    ADD_COMMENT (state, response) {
      state.commentArticle.push(response)
    }
  },
  actions: {
    async signup ({ commit }, payload) {
      try {
        const response = await axios.post(`${url}auth/signup`, payload)
        commit('SIGNUP', response.data)
      } catch (error) {
        throw error
      }
    },
    async signin ({ commit }, payload) {
      try {
        const response = await axios.post(`${url}auth/signin`, payload)
        const infos = {
          ...response.data,
          saveInfo: payload.saveInfos
        }
        commit('SIGNIN', infos)
      } catch (error) {
        throw error
      }
    },
    logout ({ commit }) {
      commit('LOGOUT')
    },
    async getCommand ({ commit }, user) {
      try {
        const response = await axios.get(`${url}command/findByUsername/${user}`)
        commit('GET_COMMAND', response.data)
      } catch (error) {
        console.log(error)
      }
    },
    async getComments ({ commit }, id) {
      try {
        const response = await axios.get(`${url}comments/findByIdArticle/${id}`)
        commit('GET_COMMENTS', response.data)
      } catch (error) {}
    },
    async changePassword ({ commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .put(`${url}auth/changePassword`, body)
      } catch (error) {
        console.log(error)
      }
    },
    async postComment ({ commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .post(`${url}comments/add`, body)
        commit('ADD_COMMENT', response.data)
      } catch (error) {
        console.log(error)
      }
    }
  }
}

export default user
