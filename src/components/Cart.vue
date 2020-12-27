<template>
	<div>
		<div v-if="cart.length != 0">
			<v-col>
				<v-row class="ma-5 d-flex justify-end">
					<h2>
						Total price : <strong>{{ totalPrice }}</strong> €
					</h2>
				</v-row>
				<v-row>
					<v-container class="d-flex flex-column justify-end align-end">
						<v-card
							v-for="(item, index) in cart"
							:key="index"
							class="col-5 mb-10"
						>
							<v-card-title
								>{{ item.name }} <v-spacer></v-spacer
								>{{ item.price }} €</v-card-title
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
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
	name: 'Cart',
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
		}
	}
};
</script>

<style lang="scss" scoped>
</style>