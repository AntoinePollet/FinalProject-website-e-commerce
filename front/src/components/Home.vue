<template>
	<v-app class="application">
		<v-main>
			<div :style="{ width: '90%', margin: 'auto' }">
				<v-col>
					<v-row class="d-flex flex-wrap justify-center">
						<div
							v-for="item in items"
							:key="item.id"
							class="col-3 mx-4 mb-5 pa-0"
						>
							<div class="d-flex justify-end mb-2">
								<v-icon
									class="mr-1"
									:color="item.favorites ? 'red' : 'red lighten-4'"
									@click="addToFav(item)"
									>mdi-heart</v-icon
								>
							</div>
							<div
								:style="{ cursor: 'pointer' }"
								@click="$router.push(`article/${item.id}`)"
							>
								<v-img :src="item.pictures[1]" contain />
								<v-card-title class="mb-0">{{ item.name }}</v-card-title>
								<v-card-text
									class="d-flex align-center pb-0"
									:style="{ height: '50px' }"
								>
									<p class="mb-0" :style="{ textAlign: 'left' }">
										{{ item.description }}
									</p></v-card-text
								>
								<h4 class="text-left pl-4 font-weight-black">
									{{ item.price }} €
								</h4>

								<v-divider class="mx-4 mt-5 d-flex align-center"></v-divider>
							</div>
						</div>
					</v-row>
				</v-col>
			</div>
		</v-main>
	</v-app>
</template>

<script>
import { mapState } from 'vuex';

export default {
	name: 'Home',
	data() {
		return {
			search: '',
			numberOfFavoritesArticles: 4,
			favorites: []
		};
	},
	computed: {
		...mapState({
			items: state => state.cart.articles
		})

		// retourner les 4 articles qui ont le plus gros rating
		// Si des articles ont le même rating, sélectionner celui qui a le plus de comm
		// Si comm équivalent, random
	},
	beforeRouteEnter(to, from, next) {
		next(async vm => {
			if (vm.$store.state.cart.articles.length <= 1) {
				await vm.$store.dispatch('cart/getItems');
			}
		});
	},
	methods: {
		addToFav(item) {
			this.$store.dispatch('favorites/addToFav', item);
		},
		favoriteItems(number) {
			let listItems = _.cloneDeep(this.items);
			let array = [];
			for (let j = 0; j < number; j++) {
				let i = 0;
				let item;
				let higherRating = 0;
				while (i < listItems.length) {
					if (higherRating < listItems[i]?.rating) {
						higherRating = listItems[i]?.rating;
						item = listItems[i];
					}
					i++;
				}
				array.push(item);
				listItems.splice(listItems.indexOf(item), 1);
			}
			return array;
		}
	}
};
</script>

<style lang="scss" scoped>
</style>