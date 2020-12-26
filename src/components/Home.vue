<template>
	<v-app>
		<v-main>
			<v-container>
				<v-col>
					<v-row class="d-flex flex-wrap justify-center">
						<v-card
							v-for="item in items"
							:key="item.id"
							class="col-lg-2 col-md-3 col-sm-4 ml-10 mb-5"
						>
							<v-card-title>{{ item.name }}</v-card-title>
							<v-card-text>{{ item.description }}</v-card-text>

							<v-img :src="item.images" width="300px" contain />
							<v-card-subtitle>Price : {{ item.price }} €</v-card-subtitle>
							<v-card-actions class="justify-end">
								<v-btn @click="addToCart(item)">Ajouter</v-btn>
							</v-card-actions>
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
			items: items
		};
	},
	methods: {
		addToCart(item) {
			this.$store.dispatch('cart/addToCart', item);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>