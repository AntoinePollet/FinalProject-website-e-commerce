<template>
  <v-main>
    <div class="col-6" ref="card"></div>
    <v-btn @click="submit">Purchase</v-btn>
  </v-main>
</template>

<script>
import { mapGetters, mapState } from 'vuex';

let elements = undefined;
let card = undefined;
let stripe = undefined;

export default {
  name: 'Paiement',
  data() {
    return {
      array: []
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
        const result = await stripe.createToken(card);
        const body = {
          token: result.token.id,
          username: this.username,
          array: this.cart,
          amount: this.totalPrice
        };
        try {
          await this.$store.dispatch('cart/payment', body);
          await this.$store.dispatch(
            'user/getCommands',
            this.$store.state.user.username
          );
        } catch (error) {}
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
