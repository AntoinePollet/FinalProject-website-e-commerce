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
							@click="$router.push(`article/${item.id}`)"
						>
							<v-card-title
								>{{ item.name }}<v-spacer></v-spacer>
								{{ item.price }} €</v-card-title
							>
							<v-divider class="mx-4"></v-divider>
							<v-card-text>{{ item.description }}</v-card-text>

							<v-img :src="item.images" contain />
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
	}
};
</script>

<style lang="scss" scoped>
</style>