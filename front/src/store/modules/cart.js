import axios from 'axios'
import _ from 'lodash'
const url = 'http://localhost:8088/api/articles'

const cart = {
  namespaced: true,
  state: {
    cart: [],
    articles: [],
    fav: []
  },
  getters: {
    getCart: state => {
      return state.cart
    },
    getArticle: state => id => {
      return state.articles.find(item => item.id === id)
    },
    autocompleteList: state => {
      return state.articles.reduce((acc, item) => {
        acc.push(item.name)
        return acc
      }, [])
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
    GET_ITEMS (state, items) {
      state.articles = []
      items.map(item => {
        state.articles.push(item)
      })
      let listItems = _.cloneDeep(items)
      for (let j = 0; j < 4; j++) {
        let i = 0
        let item = {}
        let higherRating = 0
        while (i < listItems.length) {
          if (higherRating < listItems[i]?.rating) {
            higherRating = listItems[i]?.rating
            item = listItems[i]
          }
          i++
        }
        state.fav.push(item)
        listItems.splice(listItems.indexOf(item), 1)
      }
    },
    GET_ITEM (state, item) {
      if (state.articles.find(article => article.id === item.id)) {
        state.articles.splice(
          state.articles.findIndex(article => article.id === item.id),
          1,
          item
        )
      } else {
        state.articles.push(item)
      }
    },
    ADD_TO_CURRENT_ARTICLE (state, item) {
      state.currentArticle = item
    },
    REMOVE_CART_ITEM (state, index) {
      state.cart.splice(index, 1)
    },
    GETFAV (state) {}
  },
  actions: {
    getFav ({ commit, state }) {
      commit('GETFAV')
    },
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
    async getItems ({ commit }) {
      const response = await axios
        .get(`${url}/all`)
        .then(res => {
          return res.data
        })
        .catch(error => {
          console.log(error)
        })
      commit('GET_ITEMS', response)
    },
    async getItem ({ commit }, id) {
      const response = await axios
        .get(`${url}/find/${id}`)
        .then(res => {
          return res.data
        })
        .catch(error => {
          console.log(error)
        })
      commit('GET_ITEM', response)
    },
    removeCartItem ({ commit }, index) {
      commit('REMOVE_CART_ITEM', index)
    }
  }
}
export default cart
