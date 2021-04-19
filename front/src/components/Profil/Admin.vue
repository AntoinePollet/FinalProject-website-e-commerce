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
                    Ajouter article
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span>New item</span>
                  </v-card-title>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="editemItem.name"
                          label="Nom de l'article"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          append-icon="€"
                          v-model="editemItem.price"
                          label="Prix"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="editemItem.description"
                          label="Description"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="editemItem.color"
                          label="Couleurs"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="editemItem.pictures"
                          label="Lien images"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small @click="deleteItem(item)">
              mdi-delete
            </v-icon>
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
      dialog: false,
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
      ],
      editemItem: {
        name: '',
        price: 0,
        description: '',
        color: [],
        pictures: []
      }
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
