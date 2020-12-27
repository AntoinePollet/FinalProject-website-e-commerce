<template>
	<v-container>
		<v-card class="col-lg-5 col-md-8 mb-10">
			<v-card-title
				>{{ getArticle.name }}<v-spacer></v-spacer>
				{{ getArticle.price }} €</v-card-title
			>
			<div class="d-flex">
				<v-img :src="getArticle.images" width="200px" />
				<div>
					<v-card-text>{{ getArticle.description }}</v-card-text>
					<div class="d-flex justify-end">
						<v-select
							:items="[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
							v-model="quantity"
							persistent-hint
							hint="Quantity"
							class="col-4"
						/>
					</div>
				</div>
			</div>
			<v-card-actions class="d-flex justify-end"
				><v-btn @click="addToCart(getArticle)"
					>Add to cart</v-btn
				></v-card-actions
			>
		</v-card>
	</v-container>
</template>

<script>
import articles from '../data/items.json';
export default {
	name: 'Article',
	data() {
		return {
			quantity: 1
		};
	},
	computed: {
		getArticle() {
			return articles.find(item => item.id == this.$route.params.id);
		},
		cart() {
			return this.$store.state.cart.cart;
		}
	},
	methods: {
		async addToCart(item) {
			const ele = {
				...item,
				quantity: this.quantity
			};
			const result = await this.$store.dispatch('cart/addToCart', ele);
			this.$snotify[result.type](result.message);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>