<template>
  <v-app class="application">
    <Breadcrumb :items="links" />
    <v-main>
      <div :style="{ width: '90%', margin: 'auto' }">
        <PopularArticles :items="fav" />
        <h1 class="mb-5">Tous nos articles</h1>
        <ArticlesComponent :items="items" />
      </div>
    </v-main>
  </v-app>
</template>

<script>
import { mapState } from 'vuex';
import ArticlesComponent from './ArticlesComponent.vue';
import PopularArticles from './PopularArticles.vue';
import Breadcrumb from './Breadcrumb.vue';
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
      items: state => state.cart.articles,
      fav: state => state.cart.fav
    })
  },

  beforeRouteEnter(to, from, next) {
    next(async vm => {
      if (vm.$store.state.cart.articles.length <= 1) {
        await vm.$store.dispatch('cart/getItems');
        await vm.$store.dispatch('cart/getFav');
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
