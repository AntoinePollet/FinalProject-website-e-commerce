<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container>
			<v-card>
				<v-card-title>
					<v-text-field
						v-model="search"
						append-icon="mdi-magnify"
						label="Search"
						single-line
						hide-details
					></v-text-field>
				</v-card-title>
				<v-data-table
					:headers="headers"
					:items="items"
					:search="search"
					dense
				></v-data-table>
			</v-card>
		</v-container>
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
import { mapState } from 'vuex';

export default {
	name: 'Admin',
	components: { ProfilNavigation },
	data() {
		return {
			search: '',
			headers: [
				{
					text: 'Nom',
					align: 'start',
					filterable: true,
					value: 'name'
				},
				{ text: 'Catégorie', value: 'category' },
				{ text: 'Couleur', value: 'color' },
				{ text: 'Price', value: 'price' },
				{ text: 'Actions', value: 'actions', sortable: false },
			]
		};
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (vm.$store.state.cart.articles.length <= 1) {
				vm.$store.dispatch('cart/getItems');
			}
		});
	},
	computed: {
		...mapState({
			items: state => state.cart.articles
		})
	}
};
</script>

<style lang="scss" scoped>
</style>