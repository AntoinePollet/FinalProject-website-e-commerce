<template>
	<v-app class="application">
		<v-main>
			<v-container>
				<v-col>
					<v-row class="d-flex flex-wrap justify-center">
						<v-card
							v-for="item in items"
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
								:src="item.pictures[0]"
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
import { mapState } from 'vuex';

export default {
	name: 'Home',
	data() {
		return {
			search: ''
		};
	},
	computed: {
		...mapState({
			items: state => state.cart.articles
		})
		/*
		filteredItems() {
			let filtered = this.items;
			const arr = [];
			filtered.reduce((acc, item) => {
				arr.push(item.note);
			}, []);
			return filtered;
		}
		*/
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.$store.dispatch('cart/getItems');
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
.card-text {
	height: 170px;
}
</style>