<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
    <v-container>
      <v-card>
        <v-data-table :headers="headers" :items="items" :search="search" dense>
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title>My CRUD</v-toolbar-title>
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialog" max-width="700px">
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
                  <v-card-title>
                    <v-text-field
                      v-model="search"
                      append-icon="mdi-magnify"
                      label="Search"
                      single-line
                      hide-details
                    ></v-text-field>
                  </v-card-title>
                </template>

                <v-card>
                  <v-card-title>
                    <span>New item</span>
                  </v-card-title>
                  <v-divider></v-divider>
                  <v-card-text>
                    <v-container>
                      <v-form ref="form" v-model="valid">
                        <v-row>
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field
                              v-model="editedItem.name"
                              label="Nom de l'article"
                              :rules="nameRules"
                            ></v-text-field>
                          </v-col>
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field
                              append-icon="€"
                              v-model="editedItem.price"
                              label="Prix"
                              :rules="priceRules"
                            ></v-text-field>
                          </v-col>
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field
                              v-model="editedItem.quantity"
                              label="Quantity"
                            ></v-text-field>
                          </v-col>
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field
                              v-model="editedItem.category"
                              label="Catégorie"
                              :rules="categoryRules"
                            ></v-text-field>
                          </v-col>
                          <v-col cols="12" sm="12" md="12">
                            <v-textarea
                              v-model="editedItem.description"
                              label="Description"
                              :rules="descriptionRules"
                            ></v-textarea>
                          </v-col>
                          <v-col cols="12" sm="12" md="12">
                            <div
                              v-for="(item, index) in editedItem.color"
                              :key="item.name"
                              class="d-flex"
                            >
                              <v-text-field
                                v-model="editedItem.color[index]"
                                label="Couleurs"
                                :rules="colorRules"
                              ></v-text-field>
                              <div
                                v-if="index === 0"
                                class="d-flex align-center"
                              >
                                <v-btn @click="addColor"
                                  ><v-icon>mdi-plus</v-icon></v-btn
                                >
                              </div>
                              <div v-else class="d-flex align-center">
                                <v-btn @click="deleteColor(index)" class="error"
                                  ><v-icon>mdi-delete</v-icon></v-btn
                                >
                              </div>
                            </div>
                          </v-col>
                          <v-col cols="12" sm="12" md="12">
                            <div
                              v-for="(item, index) in editedItem.pictures"
                              :key="item.name"
                              class="d-flex"
                            >
                              <v-text-field
                                v-model="editedItem.pictures[index]"
                                label="Lien images"
                                :rules="picturesRules"
                              ></v-text-field>
                              <div
                                v-if="index === 0"
                                class="d-flex align-center"
                              >
                                <v-btn @click="addPictures(index)"
                                  ><v-icon>mdi-plus</v-icon></v-btn
                                >
                              </div>
                              <div v-else class="d-flex align-center">
                                <v-btn
                                  @click="deletePictures(index)"
                                  class="error"
                                  ><v-icon>mdi-delete</v-icon></v-btn
                                >
                              </div>
                            </div>
                          </v-col>
                        </v-row>
                      </v-form>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="close">
                      Annuler
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="save">
                      Sauvegarder
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDelete" max-width="500px">
                <v-card>
                  <v-card-title class="headline"
                    >Voulez vous supprimer cet élément?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDelete"
                      >Annuler</v-btn
                    >
                    <v-btn color="blue darken-1" text @click="deleteItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
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
import ProfilNavigation from '../../components/Profil/ProfilNavigation.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { mapState } from 'vuex';

export default {
  name: 'Admin',
  components: { ProfilNavigation, Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Profil',
          disabled: true
        },
        {
          text: 'Admin'
        }
      ],
      search: '',
      valid: false,
      dialog: false,
      dialogDelete: false,
      nameRules: [v => !!v || 'Nom est requis'],
      priceRules: [
        v => !!v || 'Prix est requis',
        v => /^\d+$/.test(v) || 'Prix est un nombre'
      ],
      categoryRules: [v => !!v || 'Catégorie est requis'],
      descriptionRules: [
        v => !!v || 'Description est requis',
        v =>
          v.length < 250 || 'La description ne peut pas dépasser 250 caractères'
      ],
      colorRules: [v => !!v || 'Couleur est requise'],
      picturesRules: [v => !!v || 'Lien image requise'],

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
      editedIndex: -1,
      editedItem: {
        name: '',
        price: 0,
        quantity: 0,
        description: '',
        category: '',
        color: [''],
        pictures: ['']
      },
      defaultItem: {
        name: '',
        price: 0,
        quantity: 0,
        description: '',
        category: '',
        color: [''],
        pictures: ['']
      }
    };
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    }
  },
  beforeRouteEnter(to, from, next) {
    next(async vm => {
      if (vm.$store.state.cart.articles.length <= 1) {
        await vm.$store.dispatch('cart/getItems');
      }
    });
  },
  computed: {
    ...mapState({
      items: state => state.cart.articles
    }),
    numberOfImageLink() {
      if (
        this.editedItem.pictures.length > 1 &&
        this.editedItem.pictures.length <= 4
      )
        return true;
      else return false;
    }
  },
  methods: {
    addColor() {
      this.editedItem.color.push('');
    },
    addPictures() {
      this.editedItem.pictures.push('');
    },
    deleteColor(index) {
      this.editedItem.color.splice(index, 1);
    },
    deletePictures(index) {
      this.editedItem.pictures.splice(index, 1);
    },
    editItem(item) {
      this.editedIndex = this.items.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.items.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    deleteItemConfirm() {
      this.items.splice(this.editedIndex, 1);
      // delete Base données
      this.closeDelete();
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    async save() {
      if (this.editedIndex > -1) {
        //Object.assign(this.desserts[this.editedIndex], this.editedItem);
        // Je remplace
      } else {
        try {
          if (this.$refs.form.validate()) {
            if (this.numberOfImageLink) {
              await this.$store.dispatch('cart/addArticle', this.editedItem);
              await this.$store.dispatch('cart/getItems');
              this.close();
            } else {
              this.$snotify.error('2 à 4 images requises');
            }
          }
        } catch (error) {}
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
