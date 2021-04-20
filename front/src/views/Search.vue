<template>
  <v-app>
    <Breadcrumb :items="links" />
      <h1>Vos recherches</h1>
      <div class="d-flex mt-9">
        <v-col class="filter col-3 ml-4">
          <h3>Filtres</h3>
          <v-select :items="getCategories" v-model="selectedCategorie" />
          <v-slider
            class="mt-5"
            :max="getHigherPriceArticle"
            min="1"
            v-model="selectedPrice"
            hint="Price"
            color="black"
            persistent-hint
            thumb-label="always"
            thumb-color="pink lighten-2"
          />
          <v-select
            :items="getColors"
            v-model="selectedColor"
            placeholder="Couleur"
            chips
            multiple
          />
          <v-select
            :items="filter"
            v-model="selectedFilter"
            placeholder="filtrer par"
          />
          <v-btn class="pink lighten-2 white--text" @click="resetFilter()"
            >réinitialiser filtre(s)</v-btn
          >
        </v-col>

        <v-col class="col-9">
          <ArticlesComponent :items="filteredItems" />
        </v-col>
      </div>
  </v-app>
</template>

<script>
import { mapState } from 'vuex';
import ArticlesComponent from '../components/ArticlesComponent.vue';
import Breadcrumb from '../components/Breadcrumb.vue';
export default {
  name: 'Search',
  components: { ArticlesComponent, Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Rechercher',
          disabled: true
        }
      ],
      search: '',
      selectedPrice: 400,
      selectedColor: [],
      selectedCategorie: 'Tous',
      selectedFilter: '',
      filter: [
        'Prix croissant',
        'Prix décroissant',
        'Nom (A à Z)',
        'Note Client'
      ]
    };
  },
  computed: {
    ...mapState({
      items: state => state.cart.articles
    }),
    filteredItems() {
      let filtered = this.items;
      if (this.selectedCategorie !== 'Tous') {
        filtered = filtered.filter(
          item => item.category === this.selectedCategorie
        );
      }
      if (this.search && this.search.length) {
        filtered = filtered.filter(item =>
          item.name.toLowerCase().includes(this.search.toLowerCase())
        );
      }
      if (this.selectedPrice < 400) {
        filtered = filtered.filter(item => item.price <= this.selectedPrice);
      }
      if (this.selectedColor) {
        filtered = filtered.filter(item => {
          const index = this.selectedColor.indexOf(item.color[0]);
          for (var color of this.selectedColor) {
            return item.color[0].includes(this.selectedColor[index]);
          }
          return filtered;
        });
      }
      if (this.selectedFilter === 'Prix croissant') {
        filtered = filtered.sort((a, b) => a.price - b.price);
      }
      if (this.selectedFilter === 'Prix décroissant') {
        filtered = filtered.sort((a, b) => a.price - b.price);
        filtered.reverse();
      }
      if (this.selectedFilter === 'Nom (A à Z)') {
        filtered = filtered.sort((a, b) => {
          if (a.name < b.name) {
            return -1;
          }
          if (a.name > b.name) {
            return 1;
          }
          return 0;
        });
      }
      if (this.selectedFilter === 'Note Client') {
        filtered = filtered.sort((a, b) => a.note - b.note).reverse();
      }
      return filtered;
    },
    getColors() {
      const arr = [];
      return this.items.reduce((acc, item) => {
        if (arr.includes(item.color[0])) {
        } else {
          arr.push(item.color[0]);
        }
        return arr;
      }, []);
    },
    getCategories() {
      const arr = [];
      arr.push('Tous');
      return this.items.reduce((acc, item) => {
        arr.push(item.category);
        return arr;
      }, []);
    },
    getHigherPriceArticle() {
      return this.items.reduce((acc, item) => {
        if (acc < item.price) {
          return item.price;
        } else {
          return acc;
        }
      }, 0);
    }
  },
  watch: {
    '$route.query.name': {
      handler: function(nv, ov) {
        this.search = nv;
      },
      deep: true,
      immediate: true
    }
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (vm.$store.state.cart.articles.length <= 1) {
        vm.$store.dispatch('cart/getItems');
        vm.search = vm.$route.query.name;
        vm.selectedPrice = 400;
      }
    });
  },
  methods: {
    resetFilter() {
      (this.selectedPrice = 400),
        (this.selectedColor = []),
        (this.selectedFilter = ''),
        (this.seselectedCategorie = '');
    },
    addToFav(item) {
      this.$store.dispatch('favorites/addToFav', item);
    }
  }
};
</script>

<style lang="scss">
.filter {
  height: fit-content;
  position: sticky;
  top: 100px;
}
</style>
