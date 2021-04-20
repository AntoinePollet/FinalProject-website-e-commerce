import Vue from 'vue';
import Router from 'vue-router';

import Home from '../components/Home.vue';
import Cart from '../components/Cart.vue';
import Livraison from '../components/Livraison.vue';
import Search from '../components/Search.vue';
import Article from '../components/Article.vue';
import Contact from '../components/Contact.vue';
import AboutUs from '../components/AboutUs.vue';
import Copyright from '../components/Copyright.vue';
import Favoris from '../components/Favoris.vue';
import Paiement from '../components/Paiement.vue';
import Success from '../components/Success.vue';
import Cancel from '../components/Cancel.vue';
import Profil from '../components/Profil/Profil.vue';
import Commandes from '../components/Profil/Commandes.vue';
import Command from '../components/Profil/Command.vue';
import Informations from '../components/Profil/Informations.vue';
import MotDePasse from '../components/Profil/MotDePasse.vue';
import CartesPaiement from '../components/Profil/CartesPaiement.vue';
import Admin from '../components/Profil/Admin.vue';

import store from '../store/index.js';

Vue.use(Router);

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/commande',
      name: 'cart',
      component: Cart
    },
    {
      path: '/commande/livraison',
      name: 'livraison',
      component: Livraison,
      meta: {
        requiresAuth: true
      }
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
      path: '/aboutus',
      name: 'aboutus',
      component: AboutUs
    },
    {
      path: '/copyright',
      name: 'copyright',
      component: Copyright
    },
    {
      path: '/profil',
      name: 'profil',
      component: Profil,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/favoris',
      name: 'favoris',
      component: Favoris
    },
    {
      path: '/commande/paiement',
      name: 'paiement',
      component: Paiement,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/success',
      name: 'success',
      component: Success,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/cancel',
      name: 'cancel',
      component: Cancel,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/commandes',
      name: 'commandes',
      component: Commandes,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/commandes/:id',
      name: 'command',
      component: Command,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/informations',
      name: 'informations',
      component: Informations,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/cartesdepaiement',
      name: 'cartesdepaiement',
      component: CartesPaiement,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/motdepasse',
      name: 'MotDePasse',
      component: MotDePasse,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profil/admin',
      name: 'Admin',
      component: Admin,
      meta: {
        requiresAuth: true,
        role: 'ROLE_ADMIN'
      }
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    const logged = store.state.user.isAuth;
    if (!logged) {
      next({
        path: '/',
        query: { redirect: to.fullPath }
      });
    } else {
      next();
    }
  } else {
    next();
  }

  if (to.matched.some(record => record.meta.role)) {
    const logged = store.state.user.isAuth;
    const role = store.state.user.role;
    if (!role.includes('ROLE_ADMIN') && !logged) {
      next({
        path: '/',
        query: { redirect: to.fullPath }
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
