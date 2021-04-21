<template>
  <v-app class="application">
    <Breadcrumb :items="links" />
    <div :style="{ width: '90%', margin: 'auto' }">
      <!--
      <PopularArticles :items="items" />
      -->
      <h1 class="mb-5">Tous nos articles</h1>
      <ArticlesComponent :items="items" />
    </div>
  </v-app>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
import ArticlesComponent from '../components/ArticlesComponent.vue';
import PopularArticles from '../components/PopularArticles.vue';
import Breadcrumb from '../components/Breadcrumb.vue';
export default {
  name: 'Home',
  data() {
    return {
      links: [
        {
          text: 'Home',
          disabled: true
        }
      ]
    };
  },
  components: { ArticlesComponent, PopularArticles, Breadcrumb },
  computed: {
    ...mapState({
      items: state => state.cart.articles
    }),
    ...mapGetters({
      getPopularArticles: 'cart/getPopularArticles'
    }),
    pop() {
      return this.getPopularArticles(this.items);
    }
  },

  beforeRouteEnter(to, from, next) {
    next(async vm => {
      if (vm.$store.state.cart.articles.length <= 1) {
        await vm.$store.dispatch('cart/getItems');
      }
    });
  },
  methods: {
    addToFav(item) {
      this.$store.dispatch('favorites/addToFav', item);
    }
  }
};
</script>

<style lang="scss" scoped></style>
