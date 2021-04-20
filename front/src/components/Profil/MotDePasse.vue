<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
    <v-container class="col-lg-5 mt-8">
      <v-card flat>
        <h2 class="pt-5">Modifier mon mot de passe</h2>
        <v-form ref="form" v-model="validForm" class="pt-5">
          <p class="text-left mb-1">Nouveau mot de passe</p>
          <v-text-field
            :append-icon="mdiPassword"
            @click:append="showPassword"
            :type="passwordType"
            v-model="newPassword"
            :rules="newPasswordRule"
            outlined
            dense
          />
          <p class="text-left mb-1">Réécrire nouveau mot de passe</p>
          <v-text-field
            :append-icon="mdiNewPassword"
            @click:append="showNewPassword"
            :type="newPasswordType"
            v-model="retypePassword"
            :rules="retypePasswordRule"
            outlined
            dense
          />

          <v-card-actions class="justify-end"
            ><v-btn class="pink lighten-2 white--text" @click="save"
              >Modifier mot de passe</v-btn
            ></v-card-actions
          >
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
import Breadcrumb from '../Breadcrumb.vue';
import { mapState } from 'vuex';
export default {
  name: 'MotDePasse',
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
          text: 'Mot de passe'
        }
      ],
      validForm: true,
      newPassword: '',
      retypePassword: '',
      newPasswordRule: [v => !!v || 'Le champ ne peut pas être vide'],
      retypePasswordRule: [v => !!v || 'Le champ ne peut pas être vide'],
      mdiPassword: 'mdi-eye',
      mdiNewPassword: 'mdi-eye',
      mdiPasswordBool: true,
      passwordType: 'password',
      newPasswordType: 'password',
      mdiNewPasswordBool: true
    };
  },
  computed: {
    ...mapState({
      username: state => state.user.username
    })
  },
  methods: {
    showPassword() {
      this.mdiPasswordBool = !this.mdiPasswordBool;
      if (this.mdiPasswordBool) {
        this.mdiPassword = 'mdi-eye';
        this.passwordType = 'password';
      } else {
        this.mdiPassword = 'mdi-eye-off';
        this.passwordType = 'text';
      }
    },
    showNewPassword() {
      this.mdiNewPasswordBool = !this.mdiNewPasswordBool;
      if (this.mdiNewPasswordBool) {
        this.mdiNewPassword = 'mdi-eye';
        this.newPasswordType = 'password';
      } else {
        this.mdiNewPassword = 'mdi-eye-off';
        this.newPasswordType = 'text';
      }
    },
    async save() {
      if (this.$refs.form.validate()) {
        if (this.newPassword.localeCompare(this.retypePassword) === 0) {
          const body = {
            username: this.username,
            password: this.newPassword
          };
          try {
            await this.$store.dispatch('user/changePassword', body);
            this.$refs.form.reset();
          } catch (error) {}
        } else {
          this.$snotify.error('Les mots de passe ne sont pas identiques');
        }
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
