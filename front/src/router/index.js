import Vue from 'vue';
import Router from 'vue-router';

import Home from '../views/Home.vue';
import Cart from '../views/Cart.vue';
import Livraison from '../views/Livraison.vue';
import Search from '../views/Search.vue';
import Article from '../views/Article.vue';
import Contact from '../views/Contact.vue';
import AboutUs from '../views/AboutUs.vue';
import Copyright from '../views/Copyright.vue';
import Favoris from '../views/Favoris.vue';
import Paiement from '../views/Paiement.vue';
import Success from '../views/Success.vue';
import Cancel from '../views/Cancel.vue';
import Profil from '../views/Profil/Profil.vue';
import Commandes from '../views/Profil/Commandes.vue';
import Command from '../views/Profil/Command.vue';
import Informations from '../views/Profil/Informations.vue';
import MotDePasse from '../views/Profil/MotDePasse.vue';
import CartesPaiement from '../views/Profil/CartesPaiement.vue';
import Admin from '../views/Profil/Admin.vue';

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

router.beforeEach(async (to, from, next) => {
  const logged = store.state.user.isAuth;
  if (!logged) {
    if (localStorage.getItem('user')) {
      let user = JSON.parse(localStorage.getItem('user'));
      await store.dispatch('user/autologin', user.token);
      await store.dispatch('user/getCommands', user.username);
    }
  }
  if (to.matched.some(record => record.meta.requiresAuth)) {
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
