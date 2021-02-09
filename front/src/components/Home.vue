<template>
	<v-app>
		<v-main>
			<v-container>
				<v-col>
					<v-row class="d-flex flex-wrap justify-center">
						<v-card
							v-for="item in filteredItems"
							:key="item.id"
							class="col-lg-3 col-md-5 col-sm-4 ml-10 mb-5"
						>
							<v-card-title
								>{{ item.name }}<v-spacer></v-spacer>
								<v-icon
									class="mr-1"
									:color="item.favoris ? 'red' : 'red lighten-4'"
									@click="addToFav(item)"
									>mdi-heart</v-icon
								>
								{{ item.price }} €</v-card-title
							>
							<v-divider class="mx-4"></v-divider>
							<v-card-text>{{ item.description }}</v-card-text>
							<v-img
								:src="item.images[0]"
								contain
								@click="$router.push(`article/${item.id}`)"
							/>
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
			imageBackground: require('../public/pink_sakura.jpg'),
			search: ''
		};
	},
	computed: {
		filteredItems() {
			let filtered = this.items;
			const arr = [];
			filtered.reduce((acc, item) => {
				arr.push(item.note);
			}, []);
			return filtered;
		}
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.$store.dispatch('cart/initCart', items);
		});
	},
	methods: {
		addToFav(item) {
			this.$store.dispatch('favoris/addToFav', item);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>