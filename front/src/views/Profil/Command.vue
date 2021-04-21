<template>
  <div>
    <Breadcrumb :items="links" />
    <div>
      <div v-if="command.articles && command.articles.length">
        <v-container>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">
                    items
                  </th>
                  <th class="text-left">
                    Nom
                  </th>
                  <th class="text-left">
                    Id
                  </th>
                  <th class="text-left">
                    Description
                  </th>
                  <th class="text-left">
                    Prix
                  </th>
                  <th class="text-left">
                    Quantité
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in command.articles" :key="item.id">
                  <td>
                    <v-img
                      :src="item.pictures[0]"
                      contain
                      :style="{ maxWidth: '75px' }"
                    ></v-img>
                  </td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.id }}</td>
                  <td>{{ item.description }}</td>
                  <td>{{ item.price }}</td>
                  <td>{{ item.quantity }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-container>
      </div>
      <div v-else>
        Erreur lors de la récupération des articles
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Breadcrumb from '../../components/Breadcrumb.vue';
export default {
  name: 'command',
  components: { Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Commandes',
          to: '/profil/commandes'
        },
        {
          text: `${this.$route.params.id}`,
          disabled: true
        }
      ]
    };
  },
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
