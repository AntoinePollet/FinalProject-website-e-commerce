<template>
	<div class="navigation d-flex">
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
					:items="item"
					:search="search"
                    dense
				></v-data-table>
			</v-card>
		</v-container>
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
import items from '../../data/items.json';
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
				{ text: 'Catégorie', value: 'categorie' },
				{ text: 'Couleur', value: 'couleur' },
				{ text: 'Price', value: 'price' }
			]
		};
	},
	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.$store.dispatch('cart/initCart', items);
		});
	},
	computed: {
		...mapState({
			allItems: state => state.cart.articles
		}),
		item() {
			return this.allItems;
		}
	}
};
</script>

<style lang="scss" scoped>
.navigation {
	height: 100%;
}
</style>