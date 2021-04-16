<template>
	<v-main>
		<div class="col-6" ref="card"></div>
		<button @click="submit">Purchase</button>
	</v-main>
</template>


<script >
import { mapGetters, mapState } from 'vuex';
let stripe = Stripe(
		`pk_test_51IWroJKFVA2XgNwJPjTGtIpYfCJpICDhr6LnvLbONuRVTLRUqvdlKF6bznanAj8Vhfyg6jNMC2m6JTNPz9RsoRJN00lMyNvLYb`
	),
	elements = stripe.elements(),
	card = undefined;

export default {
	name: 'Paiement',
	computed: {
		...mapGetters({
			totalPrice: 'cart/getTotalPrice'
		}),
		...mapState({
			username: state => state.user.username,
			cart: state => state.cart.cart
		})
	},
	mounted: function() {
		card = elements.create('card');
		card.mount(this.$refs.card);
	},

	methods: {
		async submit() {
			await stripe.createToken(card).then(function(result) {
				const body = {
					...result.token,
					username: username,
					array: cart,
					price: totalPrice
				};
				try {
					this.payment(body);
					console.log(result.token);
				} catch (error) {}

				if (result.error) {
				}
				// Access the token with result.token
			});
		},
		async payment(body) {
			await this.$store.dispatch('cart/payment', body);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>