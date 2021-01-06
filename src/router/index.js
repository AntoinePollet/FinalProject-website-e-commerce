import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import Cart from '../components/Cart.vue'
import Search from '../components/Search.vue'
import Article from '../components/Article.vue'
import Contact from '../components/Contact.vue'
import SignIn from '../components/SignIn.vue'
import Profil from '../components/Profil/Profil.vue'
import Commandes from '../components/Profil/Commandes.vue'
import Informations from '../components/Profil/Informations.vue'
import MotDePasse from '../components/Profil/MotDePasse.vue'
import CartesPaiement from '../components/Profil/CartesPaiement.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/cart',
      name: 'cart',
      component: Cart
    },
    {
      path: '/search',
      name: 'search',
      component: Search
    },
    {
      path: '/article/:id',
      name: 'article',
      component: Article
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    },
    {
      path: '/profil',
      name: 'profil',
      component: Profil
    },
    {
      path: '/signin',
      name: 'signin',
      component: SignIn
    },
    {
      path: '/profil/informations',
      name: 'informations',
      component: Informations
    },
    {
      path: '/profil/cartesdepaiement',
      name: 'cartesdepaiement',
      component: CartesPaiement
    },
    {
      path: '/profil/motdepasse',
      name: 'MotDePasse',
      component: MotDePasse
    },
    {
      path: '/profil/commandes',
      name: 'commandes',
      component: Commandes
    }
  ]
})
