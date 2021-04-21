<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
    <v-container class="col-8">
      <div v-if="commands.length !== 0">
        <h1 class="pb-5">Vos commandes</h1>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  Id
                </th>
                <th class="text-left">
                  Date
                </th>
                <th class="text-left">
                  Status
                </th>
                <th class="text-left">
                  Détails
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in commands" :key="item.name">
                <td>{{ item.id }}</td>
                <td>{{ date(item.date) }}</td>
                <td>{{ status(item.date.substring(0, 10)) }}</td>
                <td>
                  <v-btn
                    @click="
                      $router.push({
                        name: 'command',
                        params: { id: item.id }
                      })"
                    >+</v-btn
                  >
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>
      <div v-else>Pas de commandes</div>
    </v-container>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ProfilNavigation from '../../components/Profil/ProfilNavigation.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
export default {
  name: 'Commandes',
  components: { ProfilNavigation, Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/home'
        },
        {
          text: 'Profil',
          disabled: true
        },
        {
          text: 'Commandes',
          disabled: true
        }
      ]
    };
  },
  computed: {
    ...mapState({
      username: state => state.user.username,
      commands: state => state.user.commands
    })
  },
  methods: {
    date(date) {
      return date.substring(0, 10)
    },
    status(date) {
      let livraison = new Date(date);
      let twoWeeksLater = new Date();
      twoWeeksLater.setDate(livraison.getDate() + 2 * 7);
      if (livraison.getTime() < twoWeeksLater.getTime())
        return 'Livraison en cours';
      else return 'Livré';
    }
  }
};
</script>

<style lang="scss" scoped></style>
