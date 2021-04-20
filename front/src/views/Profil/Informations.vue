<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
    <v-container class="container col-5">
      <v-card class="px-5" flat>
        <h2 class="mb-3 pt-5 title-info">Mes informations</h2>
        <v-form ref="form" v-model="valid" :lazy-validation="true">
          <p class="text-left mb-1">Nom</p>
          <v-text-field v-model="lastname" :rules="nameRule" outlined dense />
          <p class="text-left mb-1">Prenom</p>
          <div class="d-flex">
            <v-text-field
              v-model="firstname"
              :rules="lastNameRule"
              outlined
              dense
            />
            <div class="d-flex">
              <v-checkbox
                label="Mr"
                v-model="civiliteMr"
                :disabled="civiliteMme"
                class="ml-5 mt-0"
              />
              <v-checkbox
                label="Mme"
                v-model="civiliteMme"
                :disabled="civiliteMr"
                class="ml-5 mt-0"
              />
            </div>
          </div>
          <p class="text-left mb-1">Email</p>
          <v-text-field
            type="email"
            v-model="mail"
            :rules="emailRule"
            disabled
            outlined
            dense
          />
          <p class="text-left mb-1">Adresse</p>
          <v-text-field v-model="address" :rules="addressRule" outlined dense />
          <div class="d-flex">
            <div class="col-6 pa-0 pr-2">
              <p class="text-left mb-1">Ville</p>
              <v-text-field v-model="city" :rules="cityRule" outlined dense />
            </div>
            <div class="col-6 pa-0 pl-2">
              <p class="text-left mb-1">Code postal</p>
              <v-text-field
                v-model="postalCode"
                :rules="postalRule"
                outlined
                dense
              />
            </div>
          </div>
          <v-card-actions class="d-flex justify-end"
            ><v-btn
              color="pink lighten-2 white--text"
              @click="send()"
              :disabled="!valid"
              >Enregistrer</v-btn
            ></v-card-actions
          >
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import ProfilNavigation from '../../components/Profil/ProfilNavigation.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { mapState } from 'vuex';
export default {
  name: 'Informations',
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
          text: 'Informations'
        }
      ],
      valid: true,
      lastname: '',
      firstname: '',
      civiliteMr: false,
      civiliteMme: false,
      address: '',
      city: '',
      postalCode: '',
      nameRule: [
        v => !!v || 'Name est obligatoire',
        v => /^[a-zA-Z]+$/.test(v) || 'Name incorrect'
      ],
      lastNameRule: [
        v => !!v || 'Last name est obligatoire',
        v => /^[a-zA-Z]+$/.test(v) || 'Prénom incorrect'
      ],
      emailRule: [
        v => !!v || 'Email est obligatoire',
        v =>
          /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) ||
          'format email is not valid'
      ],
      addressRule: [v => !!v || 'Adresse est obligatoire'],
      cityRule: [v => !!v || 'Ville est obligatoire'],
      postalRule: [v => !!v || 'Code postal est obligatoire']
    };
  },
  beforeRouteEnter(to, from, next) {
    next(async vm => {
      try {
        await this.$store.dispatch('user/getInformations');
      } catch (error) {}
    });
  },
  computed: {
    ...mapState({
      userInfos: state => state.user.userInfos
    }),
    path() {
      return this.$route.path;
    },
    mail() {
      return this.$store.state.user.user.email;
    },
    sexe() {
      if (this.civiliteMr) return 'H';
      else return 'F';
    },
    adressObject() {
      const address = {
        city: this.city,
        postalCode: this.postalCode,
        street: this.address
      };
      return address;
    }
  },
  methods: {
    async send() {
      if (this.$refs.form.validate()) {
        try {
          const body = {
            firstname: this.firstname,
            lastname: this.lastname,
            sexe: this.sexe,
            address: this.adressObject
          };
          await this.$store.dispatch('user/userInformations', body);
        } catch (error) {}
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.title-info {
  color: rgb(0, 0, 0, 0.87);
}
</style>
