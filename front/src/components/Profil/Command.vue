<template>
  <v-main>
    <div>
      <h2>Commande numéro {{ $route.params.id }}</h2>
      {{ command }}
      <div v-for="item in command.articles" :key="item.id">
        <p>{{ items(item.idArticle) }}</p>
      </div>
    </div>
  </v-main>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'command',
  beforeRouteEnter(to, from, next) {
    next(async vm => {
      if (vm.$store.state.user.commands) {
      } else {
        await vm.$store.dispatch('user/getCommand', this.$route.params.id);
      }
    });
  },
  computed: {
    ...mapGetters({
      getCommand: 'user/getCommand',
      getArticle: 'cart/getArticle'
    }),
    command() {
      return this.getCommand(this.$route.params.id);
    },
    items(id) {
      return this.getArticle(id);
    }
  }
};
</script>

<style lang="scss" scoped></style>
