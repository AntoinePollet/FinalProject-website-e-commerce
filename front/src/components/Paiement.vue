<template>
	<v-main>
		<div class="product">
			<img
				src="https://i.imgur.com/EHyR2nP.png"
				alt="The cover of Stubborn Attachments"
			/>
			<div class="description">
				<h3>Stubborn Attachments</h3>
				<h5>$20.00</h5>
			</div>
		</div>
		<button id="checkout-button" @click="handlePaiment">Checkout</button>
	</v-main>
</template>

<script>
import axios from 'axios';
name: 'Paiement';
// Create an instance of the Stripe object with your publishable API key
var stripe = Stripe(
	'pk_test_51I93oFEAsAvLZg5lmZnc9kHGUoY4dIpt1Vy7ItTxxF0xiwjy4JESQvEljeXYRvfjzA6ZRhErHgosqWsJIt9Fn7yl00R95d8WBw'
);
export default {
	methods: {
		async handlePaiment() {
			await fetch('http://localhost:3000/create-checkout-session', {
				method: 'POST'
			})
				.then(function(response) {
					return response.json();
				})
				.then(function(session) {
					return stripe.redirectToCheckout({ sessionId: session.id });
				})
				.then(function(result) {
					// If redirectToCheckout fails due to a browser or network
					// error, you should display the localized error message to your
					// customer using error.message.
					if (result.error) {
						alert(result.error.message);
					}
				})
				.catch(function(error) {
					console.error('Error:', error);
				});
		}
	}
};
</script>

<style lang="scss" scoped>
</style>