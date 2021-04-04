<template>
	<v-main>
		<p v-if="items.length === 0">Vous n'avez pas encore de favoris</p>
		<v-container v-else fluid class="col-9">
			<div class="d-flex mb-10">
				<v-col class="pa-0"><h2>Name</h2></v-col>
				<v-col class="pa-0"><h2>Price</h2></v-col>
				<v-col class="pa-0"></v-col>
			</div>
			<v-card v-for="item in items" :key="item.id" flat class="pb-10">
				<v-col class="d-flex">
					<v-row class="flex-column">
						<v-row class="justify-center mb-5">
							<v-img
								:src="item.pictures[0]"
								contain
								max-width="150"
								max-height="150"
							/>
						</v-row>
						<v-row class="justify-center">
							<v-card-title>{{ item.name }}</v-card-title>
						</v-row>
						<v-row class="justify-center">
							<v-card-subtitle>Color : {{ item.color }}</v-card-subtitle>
						</v-row>
					</v-row>

					<v-row class="justify-center">
						<v-card-title>{{ item.price }} €</v-card-title>
					</v-row>
					<v-row class="justify-center">
						<v-card-actions
							><v-btn @click="removeFromFav(item)" class="error"
								><v-icon class="text-white">clear</v-icon></v-btn
							></v-card-actions
						>
					</v-row>
				</v-col>
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