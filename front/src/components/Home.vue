<template>
	<v-app class="application">
		<v-main>
			<div :style="{ width: '90%', margin: 'auto' }">
				<h1 class="mb-5">Nos articles les plus populaires</h1>
				<v-col>
					<v-row v-if="items.length !== 0" class="d-flex justify-center">
						<div
							v-for="item in fav"
							:key="item.id"
							class="col-3 mx-4 mb-5 pa-0"
						>
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
					<v-row v-else>Loading ...</v-row>
				</v-col>
				<h1 class="mb-5">Tous nos articles</h1>
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
			numberOfFavoritesArticles: 3,
			favorites: []
		};
	},
	computed: {
		...mapState({
			items: state => state.cart.articles,
			fav: state => state.cart.fav
		})
	},
	beforeRouteEnter(to, from, next) {
		next(async vm => {
			if (vm.$store.state.cart.articles.length <= 1) {
				await vm.$store.dispatch('cart/getItems');
				await vm.$store.dispatch('cart/getFav');
			}
		});
	},
	methods: {
		addToFav(item) {
			this.$store.dispatch('favorites/addToFav', item);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>