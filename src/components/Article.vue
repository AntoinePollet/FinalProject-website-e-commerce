<template>
	<v-container class="d-flex justify-center">
		<v-card class="col-lg-6 col-md-8 mb-10">
			<v-card-title
				>{{ getArticle.name }}<v-spacer></v-spacer> {{ getArticle.price }} €
			</v-card-title>
			<v-divider class="mb-4"></v-divider>
			<div class="d-flex">
				<v-img :src="getArticle.images" width="200px" />
				<div>
					<v-card-text>{{ getArticle.description }}</v-card-text>
					<div class="d-flex justify-end align-center">
						<v-btn :class="getColor(getArticle.couleur)"></v-btn>
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
			<v-rating
				v-model="getArticle.note"
				color="teal"
				readonly
				half-increments
				background-color="blue-grey lighten-2"
			/>
			<v-card-actions class="d-flex justify-end"
				><v-btn class="white--text teal" @click="addToCart(getArticle)"
					>Add to cart</v-btn
				></v-card-actions
			>
		</v-card>
	</v-container>
</template>

<script>
import articles from '../data/items.json';
import colors from '../data/colors.json';
export default {
	name: 'Article',
	data() {
		return {
			quantity: 1,
			rating: 0,
			colors: colors
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
		},
		getColor(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		}
	}
};
</script>

<style lang="scss" scoped>
</style>