<template>
  <v-app-bar app class="navbar pink lighten-4">
    <v-toolbar-title
      ><router-link to="/" class="navigation-home white--text"
        ><v-img
          :src="image"
          contain
          max-height="45"
          max-width="45"
        ></v-img></router-link
    ></v-toolbar-title>
    <v-spacer></v-spacer>
    <v-autocomplete
      class="colorInput"
      v-model="searchInput"
      placeholder="Search"
      clearable
      @keyup.enter="searchItem(search)"
      hide-details
      outlined
      flat
      dense
      :items="items"
      hide-no-data
      :search-input.sync="search"
      solo
      color="white"
    />
    <v-spacer></v-spacer>
    <v-btn
      v-if="!isAuth"
      class="navigation-list-items white--text"
      to="/signin"
      text
      >Sign in
    </v-btn>

    <v-menu>
      <template v-slot:activator="{ on, attrs }">
        <p
          v-on="on"
          v-bind="attrs"
          class="navigation-list-items white--text"
          to="/profil"
          text
        >
          Profil
        </p>
      </template>
      <v-list class="content-class">
        <v-list-item
          v-for="item in profil"
          :key="item.name"
          @click="$router.push(item.path)"
        >
          <v-icon>{{ item.mdi }}</v-icon>
          <v-list-item-title>{{ item.name }} </v-list-item-title>
        </v-list-item>
        <v-list-item v-if="isAdmin" @click="$router.push('/profil/admin')">
          <v-icon>mdi-account</v-icon>
          <v-list-item-title>Admin</v-list-item-title>
        </v-list-item>
        <v-list-item @click="logout">
          <v-icon>mdi-logout</v-icon>
          <v-list-item-title>Déconnexion</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-btn to="/favoris" class="navigation-list-items white--text" text>
      Favoris({{ favorites.length }})</v-btn
    >
    <v-btn to="/commande" class="navigation-list-items white--text" text>
      Cart({{ totalItems }})
    </v-btn>
  </v-app-bar>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import items from '../data/items.json';
export default {
  name: 'NavBar',
  items: items,
  data() {
    return {
      search: '',
      image: require('../public/KyoKyu.png'),
      searchInput: '',
      items: [],
      profil: [
        {
          name: 'Mes commandes',
          path: '/profil/commandes',
          mdi: 'mdi-book',
        },
        {
          name: 'Mes informations',
          path: '/profil/informations',
          mdi: 'mdi-book-information-variant',
        },
        {
          name: 'Mot de passe',
          path: '/profil/motdepasse',
          mdi: 'mdi-lock',
        },
        {
          name: 'Mes cartes bancaires',
          path: '/profil/cartesdepaiement',
          mdi: 'mdi-bank',
        },
      ],
    };
  },
  watch: {
    search(val) {
      if (val && val.length >= 2) {
        this.changeList(val);
      } else {
        this.items = [];
      }
    },
  },
  computed: {
    ...mapState({
      cart: (state) => state.cart.cart,
      isAuth: (state) => state.user.isAuth,
      favorites: (state) => state.favorites.favoris,
      role: (state) => state.user.role,
    }),
    ...mapGetters({
      autocompleteList: 'cart/autocompleteList',
    }),
    isAdmin() {
      if (this.role.includes('admin')) {
        return true;
      } else return false;
    },
    totalItems() {
      return this.cart.reduce((acc, item) => {
        acc = acc + item.quantity;
        return acc;
      }, 0);
    },
  },
  methods: {
    searchItem(item) {
      if (this.$route.query.name !== item) {
        this.$router.replace({ path: '/search', query: { name: item } });
      }
    },
    changeList(item) {
      this.items = this.autocompleteList
        .filter((e) => {
          return (
            (e || '').toLowerCase().indexOf((item || '').toLowerCase()) > -1
          );
        })
        .slice(0, 5);
    },
    logout() {
      if (this.$store.state.user.isAuth) {
        this.$store.dispatch('user/logout');
        this.$router.push('/');
      }
    },
  },
};
</script>

<style lang="scss">
.navbar {
  list-style: none;
  background-color: #d38fac;
  .v-select__selection,
  .v-select__selection--comma,
  .v-select.v-text-field input {
    color: black !important;
  }
  .navigation-home {
    text-decoration: none;
    font-weight: bold;
  }
  .navigation-list-items {
    text-decoration: none;
    color: white;
    margin-right: 15px;
    padding-bottom: 3px;
  }
  .navigation-list-items:hover {
    border-bottom: 2px solid white;
  }
}
</style>
