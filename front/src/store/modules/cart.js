import axios from 'axios';
import _ from 'lodash';
const url = 'https://kyokyubackend.herokuapp.com/api/v1/';

const cart = {
  namespaced: true,
  state: {
    cart: [],
    articles: [],
    fav: []
  },
  getters: {
    getCart: state => {
      return state.cart;
    },
    getArticle: state => id => {
      return state.articles.find(item => item.id === id);
    },
    autocompleteList: state => {
      return state.articles.reduce((acc, item) => {
        acc.push(item.name);
        return acc;
      }, []);
    },
    getTotalPrice: state => {
      return state.cart.reduce((acc, item) => {
        acc = acc + item.price * item.quantity;
        return acc;
      }, 0);
    }
  },
  mutations: {
    ADD_TO_CART(state, item) {
      state.cart.push(item);
    },
    UPDATE_CART(state, item) {
      state.cart.splice(
        state.cart.findIndex(article => article.id === item.id),
        1,
        item
      );
    },
    GET_ITEMS(state, items) {
      state.fav = [];
      state.articles = [];
      items.map(item => {
        state.articles.push(item);
      });
      let listItems = _.cloneDeep(items);
      for (let j = 0; j < 4; j++) {
        let i = 0;
        let item = {};
        let higherRating = 0;
        while (i < listItems.length) {
          if (higherRating < listItems[i].rating) {
            higherRating = listItems[i].rating;
            item = listItems[i];
          }
          i++;
        }
        state.fav.push(item);
        listItems.splice(listItems.indexOf(item), 1);
      }
    },
    GET_ITEM(state, item) {
      if (state.articles.find(article => article.id === item.id)) {
        state.articles.splice(
          state.articles.findIndex(article => article.id === item.id),
          1,
          item
        );
      } else {
        state.articles.push(item);
      }
    },

    ADD_TO_CURRENT_ARTICLE(state, item) {
      state.currentArticle = item;
    },
    REMOVE_CART_ITEM(state, index) {
      state.cart.splice(index, 1);
    },
    GETFAV(state) {},
    PAYMENT(state) {
      state.cart = [];
    }
  },
  actions: {
    getFav({ commit, state }) {
      commit('GETFAV');
    },
    async addToCart({ commit, state }, item) {
      const el = state.cart.findIndex(article => article.id === item.id);
      if (el !== -1) {
        const newQuantity = item.quantity + state.cart[el].quantity;
        const newItem = {
          ...item,
          quantity: newQuantity <= 10 ? newQuantity : 10
        };
        commit('UPDATE_CART', newItem);
        /*
        const response = await axios.put(
          `${url}shoppingCart/update/${item.id}`,
          newItem
        );
        */
        if (newQuantity > 10) {
          return {
            type: 'warning',
            message: 'quantité max atteinte'
          };
        }
        return {
          type: 'success',
          message: `${item.name} mis à jour`
        };
      } else {
        /*
        const response = await axios.put(
          `${url}shoppingCart/update/${item.id}`,
          item
        );
        */
        commit('ADD_TO_CART', item);
        return {
          type: 'success',
          message: `${item.name} ajouté au panier x${item.quantity}`
        };
      }
    },
    async getItems({ commit }) {
      try {
        const response = await axios.get(`${url}articles/all`);
        commit('GET_ITEMS', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async getItem({ commit }, id) {
      try {
        const response = await axios.get(`${url}articles/find/${id}`);
        commit('GET_ITEM', response.data);
      } catch (error) {
        console.log(error);
      }
    },
    removeCartItem({ commit }, index) {
      commit('REMOVE_CART_ITEM', index);
    },
    async payment({ rootState, commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + rootState.user.token
            }
          })
          .post(`${url}stripe/payment`, body);
        commit('PAYMENT', response.data);
      } catch (error) {
        throw error;
      }
    },
    async addArticle({ rootState, commit, state }, body) {
      try {
        const response = await axios
          .create({
            baseURL: this.url,
            headers: {
              'Content-Type': 'application/json',
              Authorization: 'Bearer ' + rootState.user.token
            }
          })
          .post(`${url}articles/add`, body);
      } catch (error) {
        console.log(error);
      }
    }
  }
};
export default cart;
