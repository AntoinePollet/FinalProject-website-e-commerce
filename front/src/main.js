import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import store from './store/index.js'
import vuetify from './plugins/vuetify'
import snotify from './plugins/snotify'


new Vue({
  router,
  store,
  vuetify,
  snotify,

  el: '#app',
  render: h => h(App)
})
