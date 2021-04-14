<template>
	<v-app>
		<v-main>
			<h1>Vos recherches</h1>
			<div class="d-flex mt-9">
				<v-col class="filter col-3 ml-4">
					<h3>Filtres</h3>
					<v-select
						:items="getCategories"
						v-model="selectedCategorie"
						placeholder="Categories"
					/>
					<v-slider
						class="mt-5"
						:max="getHigherPriceArticle"
						min="1"
						v-model="selectedPrice"
						hint="Price"
						color="black"
						persistent-hint
						thumb-label="always"
						thumb-color="pink lighten-2"
					/>
					<v-select
						:items="getColors"
						v-model="selectedColor"
						placeholder="Color"
						chips
						multiple
					/>
					<v-select
						:items="filter"
						v-model="selectedFilter"
						placeholder="filtrer par"
					/>
					<v-btn class="pink lighten-2 white--text" @click="resetFilter()"
						>reset filter</v-btn
					>
				</v-col>

				<v-col class="col-9">
					<v-row class="d-flex justify-center ma-0">
						<div
							v-for="item in filteredItems"
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
									:style="{ height: '50px', maxHeight: '75px' }"
								>
									<p class="mb-0" :style="{ textAlign: 'left' }">
										{{ item.description }}
									</p></v-card-text
								>
								<div class="pl-3 d-flex justify-start">
									<v-rating
										v-model="item.rating"
										color="pink lighten-2"
										size="20"
										readonly
										dense
										half-increments
										background-color="blue-grey lighten-2"
									/>
								</div>
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
	name: 'Search',
	data() {
		return {
			search: '',
			selectedPrice: 400,
			selectedColor: [],
			selectedCategorie: 'All',
			selectedFilter: '',
			filter: [
				'Prix croissant',
				'Prix décroissant',
				'Nom (A à Z)',
				'Note Client'
			]
		};
	},
	computed: {
		...mapState({
			items: state => state.cart.articles
		}),
		filteredItems() {
			let filtered = this.items;
			if (this.selectedCategorie !== 'All') {
				filtered = filtered.filter(
					item => item.category === this.selectedCategorie
				);
			}
			if (this.search && this.search.length) {
				filtered = filtered.filter(item =>
					item.name.toLowerCase().includes(this.search.toLowerCase())
				);
			}
			if (this.selectedPrice < 400) {
				filtered = filtered.filter(item => item.price <= this.selectedPrice);
			}
			if (this.selectedColor) {
				filtered = filtered.filter(item => {
					const index = this.selectedColor.indexOf(item.color[0]);
					for (var color of this.selectedColor) {
						return item.color[0].includes(this.selectedColor[index]);
					}
					return filtered;
				});
			}
			if (this.selectedFilter === 'Prix croissant') {
				filtered = filtered.sort((a, b) => a.price - b.price);
			}
			if (this.selectedFilter === 'Prix décroissant') {
				filtered = filtered.sort((a, b) => a.price - b.price);
				filtered.reverse();
			}
			if (this.selectedFilter === 'Nom (A à Z)') {
				filtered = filtered.sort((a, b) => {
					if (a.name < b.name) {
						return -1;
					}
					if (a.name > b.name) {
						return 1;
					}
					return 0;
				});
			}
			if (this.selectedFilter === 'Note Client') {
				filtered = filtered.sort((a, b) => a.note - b.note).reverse();
			}
			return filtered;
		},
		getColors() {
			const arr = [];
			return this.items.reduce((acc, item) => {
				if (arr.includes(item.color[0])) {
				} else {
					arr.push(item.color[0]);
				}
				return arr;
			}, []);
		},
		getCategories() {
			const arr = [];
			arr.push('All');
			return this.items.reduce((acc, item) => {
				arr.push(item.category);
				return arr;
			}, []);
		},
		getHigherPriceArticle() {
			return this.items.reduce((acc, item) => {
				if (acc < item.price) {
					return item.price;
				} else {
					return acc;
				}
			}, 0);
		}
	},
	watch: {
		'$route.query.name': {
			handler: function(nv, ov) {
				this.search = nv;
			},
			deep: true,
			immediate: true
		}
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (vm.$store.state.cart.articles.length <= 1) {
				vm.$store.dispatch('cart/getItems');
				vm.search = vm.$route.query.name;
				vm.selectedPrice = 400;
			}
		});
	},
	methods: {
		resetFilter() {
			(this.selectedPrice = 400),
				(this.selectedColor = []),
				(this.selectedFilter = ''),
				(this.seselectedCategorie = '');
		},
		addToFav(item) {
			this.$store.dispatch('favorites/addToFav', item);
		}
	}
};
</script>

<style lang="scss">
.filter {
	height: fit-content;
	position: sticky;
	top: 100px;
}
</style>