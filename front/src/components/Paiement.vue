<template>
	<v-main>
		<div class="col-6" ref="card"></div>
		<button @click="submit">Purchase</button>
	</v-main>
</template>


<script >
import { mapGetters, mapState } from 'vuex';

let elements = undefined;
let card = undefined;
let stripe = undefined;

export default {
	name: 'Paiement',
	computed: {
		...mapGetters({
			totalPrice: 'cart/getTotalPrice'
		}),
		...mapState({
			username: state => state.user.username,
			cart: state => state.cart.cart
		}),
		articlesId() {
			return this.cart.reduce((acc, item) => {
				acc.push(item.id);
				return acc;
			}, []);
		}
	},
	mounted: function() {
		stripe = Stripe(
			`pk_test_51IWroJKFVA2XgNwJPjTGtIpYfCJpICDhr6LnvLbONuRVTLRUqvdlKF6bznanAj8Vhfyg6jNMC2m6JTNPz9RsoRJN00lMyNvLYb`
		);
		elements = stripe.elements();
		card = undefined;
		card = elements.create('card');
		card.mount(this.$refs.card);
	},

	methods: {
		async submit() {
			const result = await stripe.createToken(card);
			const body = {
				token: result.token.id,
				username: this.username,
				array: this.articlesId,
				amount: this.totalPrice
			};
			try {
				this.payment(body);
			} catch (error) {}

			if (result.error) {
			}
			// Access the token with result.token
		},
		async payment(body) {
			await this.$store.dispatch('cart/payment', body);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>