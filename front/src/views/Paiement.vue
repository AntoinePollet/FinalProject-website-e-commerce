<template>
  <div>
    <Breadcrumb :items="links" />
    <v-main>
      <h2 class="mb-10">Paiement {{ totalPrice }} €</h2>
      <div class="col-4 mb-10" style="margin: auto" ref="card"></div>
      <v-btn
        class="pink lighten-2 white--text font-weight-bold"
        @click="submit"
        :disabled="disabled"
        >Valider</v-btn
      >
    </v-main>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
import Breadcrumb from '../components/Breadcrumb.vue';

let elements = undefined;
let card = undefined;
let stripe = undefined;

export default {
  name: 'Paiement',
  components: { Breadcrumb },
  data() {
    return {
      array: [],
      links: [
        {
          link: true,
          text: 'Home',
          to: '/home'
        },
        {
          link: false,
          text: 'Paiement',
          disabled: true
        }
      ],
      disabled: false
    };
  },
  computed: {
    ...mapGetters({
      totalPrice: 'cart/getTotalPrice'
    }),
    ...mapState({
      username: state => state.user.username,
      cart: state => state.cart.cart
    })
  },
  mounted() {
    card = undefined;
    stripe = Stripe(
      `pk_test_51IWroJKFVA2XgNwJPjTGtIpYfCJpICDhr6LnvLbONuRVTLRUqvdlKF6bznanAj8Vhfyg6jNMC2m6JTNPz9RsoRJN00lMyNvLYb`
    );
    elements = stripe.elements();
    card = elements.create('card');
    card.mount(this.$refs.card);
  },

  methods: {
    async submit() {
      try {
        this.disabled = true;
        const result = await stripe.createToken(card);
        const body = {
          token: result.token.id,
          username: this.username,
          array: this.cart,
          amount: this.totalPrice * 100
        };
        try {
          await this.$store.dispatch('cart/payment', body);
          await this.$store.dispatch(
            'user/getCommands',
            this.$store.state.user.username
          );

          this.$router.push({ name: 'success' });
          this.disabled = false;
          this.$snotify.success('Commande validé avec succès');
        } catch (error) {
          this.$snotify.error('Erreur lors de la commande');
          this.$router.push({ name: 'cancel' });
        }
      } catch (error) {
        this.$snotify.error('Les champs sont obligatoires');
        console.log(error);
        this.disabled = false;
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
