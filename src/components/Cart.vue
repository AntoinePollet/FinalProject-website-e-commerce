<template>
	<v-main>
		<div v-if="cart.length != 0">
			<v-col>
				<v-row class="ma-5 d-flex flex-column align-content-end">
					<h2>
						Total price : <strong>{{ totalPrice }}</strong> €
					</h2>
					<v-btn class="teal" @click="nextStep" text color="white"
						>Suivant</v-btn
					>
				</v-row>
				<v-row>
					<v-container class="d-flex flex-column justify-end align-end">
						<v-card
							v-for="(item, index) in cart"
							:key="index"
							class="col-lg-7 col-sm-12 mb-10"
						>
							<v-card-title
								>{{ item.name }}
								<v-btn
									:class="colorItem(item.couleur)"
									class="ml-5"
									rounded
								></v-btn
								><v-spacer></v-spacer>{{ item.price }} €</v-card-title
							>
							<div class="d-flex">
								<v-img :src="item.images" width="100px" />

								<v-card-text>{{ item.description }}</v-card-text>
								<v-select
									:items="[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
									persistent-hint
									hint="Quantity"
									v-model="item.quantity"
								/>
							</div>
							<v-card-actions class="justify-end"
								><v-btn @click="removeItem(item, index)"
									>Supprimer</v-btn
								></v-card-actions
							>
						</v-card>
					</v-container>
				</v-row>
			</v-col>
		</div>

		<div v-else>Pas d'item dans le Panier</div>
	</v-main>
</template>

<script>
import { mapGetters } from 'vuex';
import colors from '../data/colors.json';
export default {
	name: 'Cart',
	data() {
		return {
			colors: colors
		};
	},
	computed: {
		...mapGetters({
			cart: 'cart/getCart'
		}),
		totalPrice() {
			return this.cart.reduce((acc, item) => {
				acc = acc + item.price * item.quantity;
				return acc;
			}, 0);
		}
	},
	methods: {
		removeItem(item, index) {
			this.$store.dispatch('cart/removeCartItem', index);
		},
		priceItem(item) {
			return item.price * item.quantity;
		},
		colorItem(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		},
		nextStep() {
			this.$router.push('/commande/livraison');
		}
	}
};
</script>

<style lang="scss" scoped>
</style>