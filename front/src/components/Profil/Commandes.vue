<template>
  <div class="navigation">
    <ProfilNavigation />
    <v-container>
      <v-breadcrumbs>Home</v-breadcrumbs>
      <div v-if="commands.length !== 0">
        <h1 class="pb-5">Vos commandes</h1>
        <div
          v-for="item in commands"
          :key="item.id"
          class="d-flex justify-content-between"
        >
          <div>
            <p
              @click="
                $router.push({ name: 'command', params: { id: item.id } })"
            >
              Numéro de commande : {{ item.id }}
            </p>
          </div>
          <div :style="{ flexGrow: 1 }">Date : {{ item.date }}</div>
        </div>
      </div>
      <div v-else>Pas de commandes</div>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import ProfilNavigation from './ProfilNavigation.vue';
export default {
  name: 'Commandes',
  components: { ProfilNavigation },
  computed: {
    ...mapState({
      username: state => state.user.username,
      commands: state => state.user.commands
    })
  }
};
</script>

<style lang="scss" scoped></style>
