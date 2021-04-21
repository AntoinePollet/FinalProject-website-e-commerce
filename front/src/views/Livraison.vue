<template>
  <div>
    <Breadcrumb :items="links" />
    <v-main>
      <v-container class="col-lg-8" v-if="isAuth">
        <v-card flat>
          <v-card-title>Livraison</v-card-title>
          <v-form v-model="valid" ref="form">
            <v-col class="d-flex justify-center flex-column">
              <v-text-field
                placeholder="Nom et Prénom"
                v-model="firstLast"
                :rules="firstLastRules"
                outlined
                dense
              />
              <v-text-field
                placeholder="Adresse"
                v-model="address"
                :rules="addressRules"
                outlined
                dense
              />
              <v-text-field
                placeholder="Code postal"
                v-model="postalCode"
                :rules="postalCodeRules"
                outlined
                dense
              />
              <v-text-field
                placeholder="Ville"
                v-model="city"
                :rules="cityRules"
                outlined
                dense
              />
              <v-text-field
                placeholder="Numéro de téléphone"
                outlined
                dense
                hint="Peut être utilisé pour faciliter le livraison"
                persistent-hint
                v-model="number"
                :rules="numberRules"
              />
            </v-col>
            <v-card-actions class="d-flex justify-center"
              ><v-btn @click="submit" class="pink lighten-2 white--text"
                >suivant</v-btn
              >
            </v-card-actions>
          </v-form>
        </v-card>
      </v-container>
      <div v-else>
        <SigninComponent />
      </div>
    </v-main>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import SigninComponent from '../components/SigninComponent.vue';
import Breadcrumb from '../components/Breadcrumb.vue';
export default {
  name: 'livraison',
  components: { SigninComponent, Breadcrumb },
  data() {
    return {
      firstLast: '',
      address: '',
      postalCode: '',
      city: '',
      number: '',
      firstLastRules: [v => !!v || 'Nom et Prénom requis'],
      addressRules: [v => !!v || 'Adresse requis'],
      postalCodeRules: [
        v => !!v || 'Code Postal requis',
        v => v.length <= 5 || 'Maximum 5 digits',
        v => /\b\d{5}\b/.test(v) || 'Seulement des chiffres'
      ],
      cityRules: [v => !!v || 'Ville est requise'],
      numberRules: [
        v => !!v || 'Numéro est requis',
        v => v.length <= 10 || 'Maximum 10 digits',
        v =>
          /^(?:(?:(?:\+|00)33[ ]?(?:\(0\)[ ]?)?)|0){1}[1-9]{1}([ .-]?)(?:\d{2}\1?){3}\d{2}$/.test(
            v
          ) || 'Ne match pas le pattern'
      ],
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Livraison',
          disabled: true
        }
      ],
      valid: true,
      defaultAdress: false
    };
  },
  computed: {
    ...mapState({
      isAuth: state => state.user.isAuth
    })
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.$router.push({ name: 'paiement' });
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
