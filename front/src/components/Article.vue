<template>
	<v-main>
		<div v-if="!isLoading">
			<v-container class="d-flex justify-center col-8">
				<v-col class="col-2">
					<v-row
						class="justify-end py-3"
						v-for="picture in product.pictures"
						:key="picture"
					>
						<v-img
							:src="picture"
							max-width="100"
							@mouseover="changeImage(picture)"
						/>
					</v-row>
				</v-col>
				<v-col class="col-6"> <v-img :src="img" /></v-col>
				<v-col>
					<v-row>
						<v-card-title>{{ product.name }}</v-card-title>
					</v-row>
					<v-row
						><v-card-subtitle>{{ product.description }}</v-card-subtitle></v-row
					>
					<div class="my-3">
						<v-rating
							v-model="product.rating"
							color="pink lighten-2"
							size="20"
							readonly
							dense
							half-increments
							background-color="blue-grey lighten-2"
						/>
					</div>
					<v-row class="d-flex align-center">
						<v-avatar><img :src="product.pictures[0]" /></v-avatar>
						<p class="font-weight-bold mb-0 ml-2">
							{{ firstLetterUppercase(product.color[0]) }}
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
						<h1>{{ product.price }} €</h1>
					</v-row>
					<v-row>
						<p v-if="product.inStock">In stock !!</p>
						<p v-else class="text-decoration-line-through">Out of stock</p>
					</v-row>
					<v-row>
						<v-card-actions class="d-flex justify-end"
							><v-btn
								class="white--text pink lighten-2"
								:disabled="!product.inStock"
								@click="addToCart(product)"
								>Add to cart</v-btn
							></v-card-actions
						>
					</v-row>
				</v-col>
			</v-container>
			<div class="d-flex justify-center">
				<Comments class="pt-10 col-8" :idArticle="$route.params.id" />
			</div>
		</div>
		<div v-else>Loading ...</div>
	</v-main>
</template>

<script>
import Comments from './Comments.vue';
import colors from '../data/colors.json';
import { mapGetters } from 'vuex';
export default {
	name: 'Article',
	components: { Comments },
	data() {
		return {
			isLoading: true,
			quantity: 1,
			rating: 0,
			colors: colors,
			img: null
		};
	},
	computed: {
		...mapGetters({
			article: 'cart/getArticle'
		}),
		product() {
			return this.article(this.$route.params.id) ?? null;
		},
		cart() {
			return this.$store.state.cart.cart;
		},
		image() {
			this.img = this.product.pictures[0];
			return this.img;
		}
	},
	watch: {
		changeImage() {}
	},
	beforeRouteEnter(to, from, next) {
		next(async vm => {
			if (vm.product) {
				vm.img = vm.product.pictures[0];
				vm.isLoading = false;
			} else {
				await vm.$store.dispatch('cart/getItem', vm.$route.params.id);
				vm.img = vm.product.pictures[0];
				vm.isLoading = false;
			}
			await vm.$store.dispatch('user/getComments', vm.$route.params.id);
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