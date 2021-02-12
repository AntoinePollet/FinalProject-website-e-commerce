<template>
	<v-main>
		<v-container class="d-flex justify-center col-8">
			<v-col class="col-2">
				<v-row
					class="justify-end py-3"
					v-for="image in article.images"
					:key="image"
				>
					<v-img :src="image" max-width="100" @mouseover="changeImage(image)" />
				</v-row>
			</v-col>
			<v-col> <v-img :src="img" /></v-col>
			<v-col>
				<v-row>
					<v-card-title>{{ article.name }}</v-card-title>
				</v-row>
				<v-row
					><v-card-subtitle>{{ article.description }}</v-card-subtitle></v-row
				>
				<div class="my-3">
					<v-rating
						v-model="article.note"
						color="teal"
						size="20"
						readonly
						dense
						half-increments
						background-color="blue-grey lighten-2"
					/>
				</div>
				<v-row class="d-flex align-center">
					<v-avatar><img :src="article.images[0]" /></v-avatar>
					<p class="font-weight-bold mb-0 ml-2">
						{{ firstLetterUppercase(article.couleur) }}
					</p>
				</v-row>
				<v-row class="py-3">
					<v-select
						:items="[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
						v-model="quantity"
						:menu-props="{ bottom: true, offsetY: true }"
						persistent-hint
						hint="Quantity"
					/>
				</v-row>
				<v-row class="py-3">
					<h1>{{ article.price }} €</h1>
				</v-row>
				<v-row>
					<p v-if="article.inStock">In stock !!</p>
					<p v-else>Out of stock</p>
				</v-row>
				<v-row>
					<v-card-actions class="d-flex justify-end"
						><v-btn
							class="white--text teal"
							:disabled="!article.inStock"
							@click="addToCart(article)"
							>Add to cart</v-btn
						></v-card-actions
					>
				</v-row>
			</v-col>
		</v-container>
	</v-main>
</template>

<script>
import colors from '../data/colors.json';
import { mapState } from 'vuex';
export default {
	name: 'Article',
	data() {
		return {
			quantity: 1,
			rating: 0,
			colors: colors,
			img: ''
		};
	},
	computed: {
		...mapState({
			article: state => state.cart.currentItem
		}),
		cart() {
			return this.$store.state.cart.cart;
		},
		defaultImage() {
			this.img = this.article.images[0];
			return this.article.images[0];
		}
	},
	watch: {
		defaultImage() {}
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.$store.dispatch('cart/getItem', vm.$route.params.id);
		});
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
		},
		firstLetterUppercase(string) {
			return string.charAt(0).toUpperCase() + string.slice(1);
		},
		changeImage(img) {
			this.img = img;
			return img;
		}
	}
};
</script>

<style lang="scss" scoped>
</style>