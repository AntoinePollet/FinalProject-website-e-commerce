import axios from 'axios';
const url = 'https://kyokyubackend.herokuapp.com/api/v1/';

const user = {
  namespaced: true,
  state: {
    isAuth: false,
    commands: [],
    role: [],
    token: '',
    username: '',
    id: '',
    user: {},
    userInfos: {},
    commentArticle: []
  },
  getters: {
    getCommand: state => id => {
      return state.commands.find(item => item.id === id);
    },
    ratingArticle: state => {
      return state.commentArticle.reduce((total, item) => {
        total = total + item.rating;
        return total;
      }, 0);
    },
    hasOrdered: state => id => {
      const orders = state.commands
        .reduce((acc, item) => {
          acc = acc.concat(item.articles);
          return acc;
        }, [])
        .reduce((acc, item) => {
          if (acc[item.id]) {
            acc[item.id] = acc[item.id] + item.quantity;
          } else {
            acc[item.id] = item.quantity;
          }
          return acc;
        }, {});

      return orders.hasOwnProperty(id);
    },
    hasCommented: state => {
      if (state.commentArticle.length) {
        return (
          state.commentArticle.find(
            item => item.username === state.username
          ) !== undefined
        );
      } else {
        return false;
      }
    }
  },
  mutations: {
    SIGNUP(state, payload) {},
    SIGNIN(state, payload) {
      state.token = payload.token;
      state.role = payload.roles;
      state.username = payload.username;
      state.id = payload.id;
      state.user['username'] = payload.username;
      state.user['email'] = payload.email;
      state.isAuth = true;
    },
    LOGOUT(state) {
      localStorage.clear();
      state.isAuth = false;
      state.username = '';
      state.role = [];
    },
    GET_COMMANDS(state, res) {
      state.commands = [];
      for (let i in res) {
        state.commands.push(res[i]);
      }
    },
    GET_COMMAND(state, res) {
      state.commands.push(res);
    },
    GET_COMMENTS(state, response) {
      state.commentArticle = [];
      for (let i in response) {
        state.commentArticle.push(response[i]);
      }
    },
    ADD_COMMENT(state, response) {
      state.commentArticle.push(response);
    },
    USER_INFOS(state, response) {
    },
    GET_INFORMATIONS(state, payload) {
      state.userInfos = payload;
    }
  },
  actions: {
    async signup({ commit }, payload) {
      try {
        const response = await axios.post(`${url}auth/signup`, payload);
        commit('SIGNUP', response.data);
      } catch (error) {
        throw error;
      }
    },
    async signin({ commit }, payload) {
      try {
        const response = await axios.post(`${url}auth/signin`, payload);
        const infos = {
          ...response.data,
          saveInfo: payload.saveInfos
        };
        const user = {
          token: infos.accessToken,
          username: infos.username,
          email: infos.email,
          roles: infos.roles,
          id: infos.id
        };
        localStorage.setItem('user', JSON.stringify(user));
        commit('SIGNIN', user);
      } catch (error) {
        throw error;
      }
    },
    async autologin({ commit }, token) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + token
            }
          })
          .post(`${url}auth/session`, { token });
        const user = {
          token,
          username: response.data.username,
          email: response.data.email,
          roles: response.data.roles,
          id: response.data.id
        };
        commit('SIGNIN', user);
      } catch (error) {}
    },
    logout({ rootState, commit }) {
      rootState.cart.cart = [];
      commit('LOGOUT');
    },
    async getCommands({ commit }, user) {
      try {
        const response = await axios.get(
          `${url}command/findByUsername/${user}`
        );
        commit('GET_COMMANDS', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async getCommand({ commit }, id) {
      try {
        const response = await axios.get(`${url}command/findById/${id}`);
        commit('GET_COMMAND', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async getComments({ commit }, id) {
      try {
        const response = await axios.get(
          `${url}comments/findByIdArticle/${id}`
        );
        commit('GET_COMMENTS', response.data);
      } catch (error) {}
    },
    async changePassword({ commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .put(`${url}auth/changePassword`, body);
      } catch (error) {
        console.log(error);
      }
    },
    async postComment({ commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .post(`${url}comments/add`, body);
        commit('ADD_COMMENT', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async userInformations({ commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .put(`${url}user/updateInfos/${state.id}`, body);
        commit('USER_INFOS', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async getInformations({ commit, state }, id) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + state.token
            }
          })
          .get(`${url}user/find/${id}`);
        commit('GET_INFORMATIONS', response.data);
      } catch (error) {
        console.log(error);
      }
    }
  }
};

export default user;
