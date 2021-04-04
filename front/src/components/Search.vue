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
				<v-container>
					<v-col>
						<v-row class="d-flex flex-wrap justify-center">
							<v-card
								v-for="item in filteredItems"
								:key="item.id"
								class="col-lg-3 col-md-5 col-sm-4 ml-10 mb-5 pa-0"
							>
								<v-card-title
									>{{ item.name }}<v-spacer></v-spacer>
									<v-icon
										class="mr-1"
										:color="item.favorites ? 'red' : 'red lighten-4'"
										@click="addToFav(item)"
										>mdi-heart</v-icon
									>
									{{ item.price }} €</v-card-title
								>
								<v-divider class="mx-4 d-flex align-center"></v-divider>

								<v-card-text class="card-text d-flex align-center">
									<p class="mb-0">{{ item.description }}</p></v-card-text
								>
								<v-img
									src="https://picsum.photos/id/23/300"
									contain
									@click="$router.push(`article/${item.id}`)"
								/>
							</v-card>
						</v-row>
					</v-col>
				</v-container>
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
					item => item.category === this.selectedCategorie.toLowerCase()
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
		/*
		colorItem(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		},
		*/
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
.card-text {
	height: 170px;
}
</style>