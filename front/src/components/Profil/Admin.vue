<template>
  <div class="navigation">
    <ProfilNavigation />
    <v-container>
      <v-card>
        <v-card-title>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-card-title>
        <v-data-table :headers="headers" :items="items" :search="search" dense>
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title>My CRUD</v-toolbar-title>
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialog" max-width="500px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary"
                    dark
                    class="mb-2"
                    v-on="on"
                    v-bind="attrs"
                  >
                  </v-btn>
                </template>
              </v-dialog>
            </v-toolbar>
          </template>
        </v-data-table>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
import { mapState } from 'vuex';

export default {
  name: 'Admin',
  components: { ProfilNavigation },
  data() {
    return {
      search: '',
      headers: [
        {
          text: 'Nom',
          align: 'start',
          filterable: true,
          value: 'name'
        },
        { text: 'Catégorie', value: 'category' },
        { text: 'Couleur', value: 'color' },
        { text: 'Price', value: 'price' },
        { text: 'Actions', value: 'actions', sortable: false }
      ]
    };
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (vm.$store.state.cart.articles.length <= 1) {
        vm.$store.dispatch('cart/getItems');
      }
    });
  },
  computed: {
    ...mapState({
      items: state => state.cart.articles
    })
  }
};
</script>

<style lang="scss" scoped></style>
