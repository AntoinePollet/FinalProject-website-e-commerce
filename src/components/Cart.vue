<template>
	<div>
		<div v-if="cart.length != 0">
			<v-container class="d-flex flex-column justify-end align-end">
				<v-card
					v-for="(item, index) in cart"
					:key="index"
					width="300px"
					class="col-5 mb-10"
				>
					<v-card-title>{{ item.name }}</v-card-title>
					<v-card-subtitle>{{ item.description }}</v-card-subtitle>
					<v-img :src="item.images" />
					<v-card-actions class="justify-end"
						><v-btn @click="removeItem(item, index)"
							>Supprimer</v-btn
						></v-card-actions
					>
				</v-card>
				<p>Total price : {{ price }} €</p>
			</v-container>
		</div>
		<div v-else>Pas d'item dans le Panier</div>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
	name: 'Cart',
	data() {
		return {
			price: 0
		};
	},
	computed: {
		...mapGetters({
			cart: 'cart/getCart'
		}),
		totalPrice() {
			this.cart.map(item => {
				this.price += item.price;
				return item;
			});
		}
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			cart: vm.$store.getters['cart/getCart'];
			vm.totalPrice;
		});
	},
	methods: {
		removeItem(item, index) {
			this.$store.dispatch('cart/removeCartItem', index);
			this.price -= item.price;
		}
	}
};
</script>

<style lang="scss" scoped>
</style>