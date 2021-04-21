<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
    <v-container class="container col-5">
      <v-card class="px-5" flat>
        <h2 class="mb-3 pt-5 title-info">Mes informations</h2>
        <v-form ref="form" v-model="valid" :lazy-validation="true">
          <p class="text-left mb-1">Nom</p>
          <v-text-field
            v-model="lastname"
            :rules="lastnameRule"
            outlined
            dense
          />
          <p class="text-left mb-1">Prenom</p>
          <div class="d-flex">
            <v-text-field
              v-model="firstname"
              :rules="firstnameRule"
              outlined
              dense
            />
            <div class="d-flex">
              <v-checkbox
                color="pink lighten-2"
                label="Mr"
                v-model="civiliteMr"
                :disabled="civiliteMme"
                class="ml-5 mt-0"
              />
              <v-checkbox
                color="pink lighten-2"
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
          <v-text-field v-model="street" :rules="addressRule" outlined dense />
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
          to: '/home'
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
      civiliteMr: false,
      civiliteMme: false,
      firstnameRule: [
        v => !!v || 'Name est obligatoire',
        v => /^[a-zA-Z]+$/.test(v) || 'Name incorrect'
      ],
      lastnameRule: [
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
      postalRule: [
        v => !!v || 'Code postal est obligatoire',
        v => (v && v.lenght !== 5) || 'CodePostal doit être égal à 5'
      ]
    };
  },
  beforeRouteEnter(to, from, next) {
    next(async vm => {
      try {
        await vm.$store.dispatch(
          'user/getInformations',
          vm.$store.state.user.id
        );
      } catch (error) {
        console.log(error);
      }
    });
  },
  watch: {
    userInfos() {
      if (this.userInfos.sexe === 'H') this.civiliteMr = true;
      else this.civiliteMme = true;
    }
  },
  computed: {
    ...mapState({
      userInfos: state => state.user.userInfos
    }),
    firstname: {
      get() {
        return this.userInfos?.firstname || '';
      },
      set(nv) {
        this.userInfos.firstname = nv;
      }
    },
    lastname: {
      get() {
        return this.userInfos?.lastname || '';
      },
      set(nv) {
        this.userInfos.lastname = nv;
      }
    },
    street: {
      get() {
        return this.userInfos?.address?.street || '';
      },
      set(nv) {
        this.userInfos.address.street = nv;
      }
    },
    city: {
      get() {
        return this.userInfos?.address?.city || '';
      },
      set(nv) {
        this.userInfos.address.city = nv;
      }
    },
    postalCode: {
      get() {
        return this.userInfos?.address?.postalCode || '';
      },
      set(nv) {
        this.userInfos.address.postalCode = nv;
      }
    },
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
        street: this.street
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
