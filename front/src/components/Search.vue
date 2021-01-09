<template>
	<v-app>
		<v-main>
			<h1>Vos recherches</h1>
			<div class="d-flex mt-9">
				<v-col class="col-3 ml-4">
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
						thumb-color="#2a9d8f"
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
					<v-btn class="teal white--text" @click="resetFilter()"
						>reset filter</v-btn
					>
				</v-col>
				<v-container>
					<v-col>
						<v-row class="d-flex flex-wrap justify-center">
							<v-card
								v-for="item in filteredItems"
								:key="item.id"
								class="col-lg-3 col-md-5 col-sm-4 ml-10 mb-5"
								@click="$router.push(`article/${item.id}`)"
							>
								<v-card-title
									>{{ item.name }}<v-spacer></v-spacer>
									{{ item.price }} €</v-card-title
								>
								<v-divider class="ma-4"></v-divider>
								<v-btn
									:class="colorItem(item.couleur)"
									elevation="2"
									fab
								></v-btn>
								<v-card-text>{{ item.description }}</v-card-text>

								<v-img :src="item.images" width="300px" contain />
								<v-rating
									v-model="item.note"
									readonly
									dense
									color="teal"
									half-increments
									background-color="blue-grey lighten-2"
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
import items from '../data/items.json';
import colors from '../data/colors.json';

export default {
	name: 'Search',
	data() {
		return {
			items: items,
			colors: colors,
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
		filteredItems() {
			let filtered = this.items;
			if (this.selectedCategorie !== 'All') {
				filtered = filtered.filter(
					item => item.categorie === this.selectedCategorie.toLowerCase()
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
					const index = this.selectedColor.indexOf(item.couleur);
					for (var color of this.selectedColor) {
						return item.couleur.includes(this.selectedColor[index]);
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
				filtered = filtered.sort((a, b) => a.note - b.note);
			}
			return filtered;
		},
		getColors() {
			const arr = [];
			return this.items.reduce((acc, item) => {
				arr.push(item.couleur);
				return arr;
			}, []);
		},
		getCategories() {
			const arr = [];
			arr.push('All');
			return this.items.reduce((acc, item) => {
				arr.push(item.categorie);
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
			vm.$store.dispatch('cart/initCart', items);
			vm.search = vm.$route.query.name;
		});
	},
	methods: {
		colorItem(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		},
		resetFilter() {
			(this.selectedPrice = 400),
				(this.selectedColor = []),
				(this.selectedFilter = ''),
				(this.seselectedCategorie = '');
		}
	}
};
</script>

<style lang="scss">
</style>