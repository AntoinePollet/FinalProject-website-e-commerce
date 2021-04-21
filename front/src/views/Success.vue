<template>
  <v-main
    ><h1>Paiement validé</h1>
    <div>
      <v-btn to="/" class="pink lighten-2 font-weight-bold white--text"
        >Retour au magasin</v-btn
      >
    </div></v-main
  >
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'Success',
  computed: {
    ...mapState({
      cart: state => state.cart.cart
    })
  }
};
</script>

<style lang="scss" scoped></style>
