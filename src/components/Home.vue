<template>
	<v-app>
		<v-main>
			<v-container>
				<v-text-field
					v-model="search"
					placeholder="Search"
					clearable
				></v-text-field>
				<v-slider
					max="400"
					min="1"
					v-model="selectedPrice"
					thumb-label="always"
					thumb-color="purple"
				></v-slider>
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
							<v-card-text>{{ item.description }}</v-card-text>

							<v-img :src="item.images" width="300px" contain />
						</v-card>
					</v-row>
				</v-col>
			</v-container>
		</v-main>
	</v-app>
</template>

<script>
import items from '../data/items.json';

export default {
	name: 'Home',
	data() {
		return {
			items: items,
			search: '',
			selectedPrice: 400
		};
	},
	computed: {
		filteredItems() {
			let filtered = this.items;
			if (this.search && this.search.length) {
				filtered = filtered.filter(item => item.name.includes(this.search));
			}
			if (this.selectedPrice < 400) {
				filtered = filtered.filter(item => item.price <= this.selectedPrice);
			}
			return filtered;
		}
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.$store.dispatch('cart/initCart', items);
		});
	}
};
</script>

<style lang="scss" scoped>
</style>