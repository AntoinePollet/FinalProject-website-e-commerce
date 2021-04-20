<template>
  <div>
    <Breadcrumb :items="links" />
    <v-container class="mt-5">
      <h1 class="mb-5">Nous contacter</h1>
      <v-card class="pa-7" flat>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            placeholder="Name"
            v-model="name"
            :rules="nameRule"
          ></v-text-field>
          <v-text-field
            type="email"
            placeholder="Email"
            v-model="email"
            :rules="emailRule"
          ></v-text-field>
          <v-text-field
            placeholder="Sujet"
            v-model="subject"
            :rules="subjectRule"
          ></v-text-field>
          <v-textarea
            placeholder="Description"
            v-model="description"
            :rules="descriptionRule"
          ></v-textarea>
          <v-card-actions class="d-flex justify-end"
            ><v-btn
              color="pink lighten-2 white--text"
              @click="send()"
              :disabled="!valid"
              >Envoyer</v-btn
            ></v-card-actions
          >
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';
export default {
  name: 'Contact',
  components: { Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Contact',
          disabled: true
        }
      ],
      valid: true,
      name: '',
      email: '',
      subject: '',
      description: '',
      nameRule: [
        v => !!v || 'Name is required',
        v => /^[a-zA-Z]+$/.test(v) || 'Name incorrect'
      ],
      emailRule: [
        v => !!v || 'Email is required',
        v =>
          /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) ||
          'format email is not valid'
      ],
      subjectRule: [
        v => !!v || 'Subject is required',
        v => v.length <= 150 || 'Sujet ne peut pas dépasser 150 caractères'
      ],
      descriptionRule: [
        v => !!v || 'Description is required',
        v =>
          v.length <= 500 || 'Description ne peut pas dépasser 500 caractères'
      ]
    };
  },
  methods: {
    send() {
      if (this.$refs.form.validate()) {
        this.$refs.form.reset();
        this.$snotify.success('Formulaire envoyé');
      } else {
        this.$snotify.error("Echec lors de l'envoie du formulaire");
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
