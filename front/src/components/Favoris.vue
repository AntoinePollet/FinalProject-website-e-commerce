<template>
	<v-main>
		<p v-if="items.length === 0">Vous n'avez pas encore de favoris</p>
		<v-container v-else>
			<v-card v-for="item in items" :key="item.id">
				<v-card-title>{{ item.name }}</v-card-title>
				<v-card-subtitle>{{ item.price }}</v-card-subtitle>
				<v-card-actions
					><v-btn @click="removeFromFav(item)">supprimer</v-btn></v-card-actions
				>
			</v-card>
		</v-container>
	</v-main>
</template>

<script>
import { mapState } from 'vuex';
export default {
	name: 'Favoris',
	computed: {
		...mapState({
			items: state => state.favorites.favoris
		})
	},
	methods: {
		removeFromFav(item) {
			this.$store.dispatch('favorites/removeFav', item);
		}
	}
};
</script>

<style lang="scss" scoped>
</style>