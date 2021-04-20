<template>
	<v-main>Paiement validé</v-main>
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

<style lang="scss" scoped>
</style>