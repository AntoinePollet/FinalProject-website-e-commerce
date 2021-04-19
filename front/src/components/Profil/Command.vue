<template>
  <v-main>
    <div>
      <h2>Commande numéro {{ $route.params.id }}</h2>
      <div v-if="command.articles && command.articles.length">
        <div
          v-for="item in command.articles"
          :key="item.idArticle"
          class="d-flex justify-content-between col-8"
          :style="{ margin: 'auto' }"
        >
          <div>
            <v-img
              :src="item.pictures[0]"
              contain
              :style="{ maxWidth: '75px' }"
            ></v-img>
          </div>
          <div class="col-8 d-flex justify-center flex-column pl-3">
            <div>{{ item.name }}</div>
            <div>{{ item.description }}</div>
          </div>
          <div class="col-4 d-flex justify-center flex-column pl-3">
            <div>Quantité : {{ item.quantity }}</div>
            <div>Prix : {{ item.price }} €</div>
          </div>
        </div>
      </div>
      <div v-else>
        Erreur lors de la récupération des articles
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
    }
  }
};
</script>

<style lang="scss" scoped></style>
